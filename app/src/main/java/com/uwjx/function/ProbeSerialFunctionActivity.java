package com.uwjx.function;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.uwjx.function.event.ProbeCmdEvent;
import com.uwjx.function.event.ProbeUpgradeEvent;
import com.uwjx.function.probe.ProbeCmdQueue;
import com.uwjx.function.probe.ProbeOpen24VCmd;
import com.uwjx.function.probe.ProbeOpenRelayCmd;
import com.uwjx.function.probe.ProbePreUpgradeCmd;
import com.uwjx.function.probe.ProbeQueryLiquidLevelCmd;
import com.uwjx.function.probe.ProbeQuerySnCmd;
import com.uwjx.function.probe.ProbeQuerySoftwareVersionCmd;
import com.uwjx.function.probe.ProbeResetCmd;
import com.uwjx.function.probe.ProbeUpgradeCmd;
import com.uwjx.function.util.ByteUtils;
import com.uwjx.function.util.DateUtil;
import com.uwjx.function.util.FileReadUtils;
import com.uwjx.serial.Device;
import com.uwjx.serial.SerialPortManager;
import com.uwjx.serial.listener.OnOpenSerialPortListener;
import com.uwjx.serial.listener.OnSerialPortDataListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProbeSerialFunctionActivity extends Activity implements OnOpenSerialPortListener {

    public static final String DEVICE = "device-data";

    SerialPortManager mSerialPortManager;
    ProbeCmdQueue probeCmdQueue = ProbeCmdQueue.getInstance();

    @BindView(R.id.probe_device_info)
    TextView probe_device_info;
    @BindView(R.id.probe_send_cmd)
    TextView probe_send_cmd;
    @BindView(R.id.probe_receive_cmd)
    TextView probe_receive_cmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_probe_function);
        ButterKnife.bind(this);

        Device device = (Device) getIntent().getSerializableExtra(DEVICE);
        Log.i("hugh", "onCreate: device = " + device);
        if (null == device) {
            probe_device_info.setText("设备信息为空");
            return;
        }else {
            probe_device_info.setText(device.getName() + " ");
        }


        mSerialPortManager = new SerialPortManager();


        // 打开串口
        boolean openSerialPort = mSerialPortManager.setOnOpenSerialPortListener(this)
                .setOnSerialPortDataListener(serialPortDataListener)
                .openSerialPort(device.getFile(), 115200);

        Log.i("hugh", "onCreate: openSerialPort = " + openSerialPort);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void cmdEvent(ProbeCmdEvent probeCmdEvent){
        Log.e("hugh" , "eventbus 接收到 probe 指令 : " + probeCmdEvent.getCmd());
        String msg = DateUtil.getFormat() + " \t " + probeCmdEvent.getCmd() + "\n";

        String originMSg = "";
        if(probe_receive_cmd.getText() != null ){
            originMSg= probe_receive_cmd.getText().toString();
        }

        probe_receive_cmd.setText(msg + originMSg);

        //            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    probe_send_cmd.append(DateUtil.getFormat() + " \t " + ByteUtils.genHexStr(bytes) + "\n");
//                }
//            });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        probeCmdQueue.startProcess();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        probeCmdQueue.stopProcess();
    }

    OnSerialPortDataListener serialPortDataListener = new OnSerialPortDataListener() {
        @Override
        public void onDataReceived(byte[] bytes) {
//            Log.i(TAG, "onDataReceived [ byte[] ]: " + Arrays.toString(bytes));
//            Log.i(TAG, "onDataReceived [ String ]: " + new String(bytes));
            Log.e("hugh", "接收到来自设备的16进制数据 = " + ByteUtils.genHexStr(bytes));

            probeCmdQueue.add(ByteUtils.genHexStr(bytes));




//            cmdQueue.add(ByteUtils.genHexStr(bytes));
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    probe_receive_cmd.append(DateUtil.getFormat() + " \t " + ByteUtils.genHexStr(bytes) + "\n");
//                }
//            });
        }

        @Override
        public void onDataSent(byte[] bytes) {
//            Log.i(TAG, "onDataSent [ byte[] ]: " + Arrays.toString(bytes));
            Log.e("hugh", "数据下发到设备成功 [ String ]: " + ByteUtils.genHexStr(bytes));

//            probeCmdQueue.add(ByteUtils.genHexStr(bytes));

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String sentMsg = DateUtil.getFormat() + " \t " + ByteUtils.genHexStr(bytes) + "\n";
                    String originSentMSg = "";
                    if(probe_receive_cmd.getText() != null ){
                        originSentMSg= probe_send_cmd.getText().toString();
                    }

                    probe_send_cmd.setText(sentMsg + originSentMSg);
//                    probe_send_cmd.append();



                    String msg = "-----------------------------------------------------------------\n";

                    String originMSg = "";
                    if(probe_receive_cmd.getText() != null ){
                        originMSg= probe_receive_cmd.getText().toString();
                    }

                    probe_receive_cmd.setText(msg + originMSg);
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        if (null != mSerialPortManager) {
            mSerialPortManager.closeSerialPort();
            mSerialPortManager = null;
        }
        super.onDestroy();
    }

    /**
     * 串口打开成功
     *
     * @param device 串口
     */
    @Override
    public void onSuccess(File device) {
        Log.w("hugh" , "串口打开成功@@@@@@@@@@@@@@@@@@@");
        Toast.makeText(getApplicationContext(), String.format("串口 [%s] 打开成功", device.getPath()), Toast.LENGTH_SHORT).show();
        probe_device_info.setText(device.getName() + "\t串口打开成功");
        probe_device_info.setTextColor(getResources().getColor(R.color.colorGreen));
    }

    /**
     * 串口打开失败
     *
     * @param device 串口
     * @param status status
     */
    @Override
    public void onFail(File device, OnOpenSerialPortListener.Status status) {
        Log.w("hugh" , "串口打开失败 ###################");
        probe_device_info.setText(device.getName() + "\t串口打开失败");
        probe_device_info.setTextColor(getResources().getColor(R.color.colorFontRed));
        switch (status) {
            case NO_READ_WRITE_PERMISSION:
                showDialog(device.getPath(), "没有读写权限");
                break;
            case OPEN_FAIL:
            default:
                showDialog(device.getPath(), "串口打开失败");
                break;
        }
    }

    private void showDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    @OnClick(R.id.probe_query_liquid_level_1)
    public void probe_query_liquid_level_1(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(1);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位 1] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_query_liquid_level_2)
    public void probe_query_liquid_level_2(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(2);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位 2] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_query_liquid_level_3)
    public void probe_query_liquid_level_3(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(3);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位 3] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_query_liquid_level_4)
    public void probe_query_liquid_level_4(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(4);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位 4] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_query_liquid_level_all)
    public void probe_query_liquid_level(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(0);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位 all] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_query_software_version)
    public void probe_query_software_version(){
        ProbeQuerySoftwareVersionCmd querySoftwareVersionCmd = new ProbeQuerySoftwareVersionCmd();
        byte[] cmd = querySoftwareVersionCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发querySoftwareVersionCmd指令[查询软件版本号] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_query_sn)
    public void probe_query_sn(){
        ProbeQuerySnCmd querySnCmd = new ProbeQuerySnCmd();
        byte[] cmd = querySnCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发querySnCmd指令[查询序列号] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_open_24v)
    public void probe_open_24v(){
        ProbeOpen24VCmd open24VCmd = new ProbeOpen24VCmd(1);
        byte[] cmd = open24VCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发open24VCmd指令[开启 24V] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_open_relay)
    public void probe_open_relay(){
        ProbeOpenRelayCmd openRelayCmd = new ProbeOpenRelayCmd(1);
        byte[] cmd = openRelayCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发openRelayCmd指令[开启继电器] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_pre_upgrade)
    public void probe_pre_upgrade(){

        String file = "/storage/udisk/ATG.bin";

        byte [] lengthByte = new byte[2];
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Log.w("hugh" , "Bin 文件长度:" + inputStream.available());
            int size = inputStream.available();
            byte[] byteSize = ByteUtils.shortToByteArr((short) size);
            lengthByte[0] = byteSize[0];
            lengthByte[1] = byteSize[1];
            // 关闭输入流
            if(inputStream != null){
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




        ProbePreUpgradeCmd preUpgradeCmd = new ProbePreUpgradeCmd(lengthByte);
        byte[] cmd = preUpgradeCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[预升级] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_upgrade)
    public void probe_upgrade(){
        Log.e("hugh" , "点击下发 ProbeUpgradeEvent 指令 " );
        EventBus.getDefault().post(new ProbeUpgradeEvent());
    }

    @OnClick(R.id.probe_reset)
    public void probe_reset(){
        ProbeResetCmd resetCmd = new ProbeResetCmd();
        byte[] cmd = resetCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发resetCmd指令[复位指令] 到设备 = " + ByteUtils.genHexStr(cmd));
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void cmdEvent(ProbeUpgradeEvent probeUpgradeEvent){
        Log.e("hugh" , "eventbus 接收到 ProbeUpgradeEvent 指令 " );

        String file = "/storage/udisk/ATG.bin";

        try {
            FileInputStream inputStream = new FileInputStream(file);
            int total = inputStream.available();
            Log.w("hugh" , "Bin 文件总长度:" + total);

            short index = 0;
            while (inputStream.available() >= 1024){
                byte[] buffer = new byte[1024];
                inputStream.read(buffer);

                byte[] offsetByte = ByteUtils.shortToByteArr((short) (index * 1024));
                byte [] lengthByte = ByteUtils.shortToByteArr((short)1024);
                ProbeUpgradeCmd upgradeCmd = new ProbeUpgradeCmd(lengthByte , offsetByte,buffer );
                byte[] cmd = upgradeCmd.getSendCmd();
                mSerialPortManager.sendBytes(cmd);
                Log.i("hugh", "第 "+index+" 次下发  [升级数据] 到设备 = " + ByteUtils.genHexStr(cmd));

                String currentHex = ByteUtils.bytesToHexStr(buffer);
                Log.w("hugh" , "下发第"+index+"个的升级数据 " + currentHex);
                index++;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int lastSize = inputStream.available();
            byte[] buffer = new byte[lastSize];
            inputStream.read(buffer);

            byte[] offsetByte = ByteUtils.shortToByteArr((short) (index * 1024));
            byte [] lengthByte = ByteUtils.shortToByteArr((short)lastSize);
            ProbeUpgradeCmd upgradeCmd = new ProbeUpgradeCmd(lengthByte , offsetByte,buffer );
            byte[] cmd = upgradeCmd.getSendCmd();
            mSerialPortManager.sendBytes(cmd);
            Log.i("hugh", "下发30指令[升级数据] 到设备 = " + ByteUtils.genHexStr(cmd));

            String currentHex = ByteUtils.bytesToHexStr(buffer);
            Log.w("hugh" , "下发第"+index+"个的升级数据 " + currentHex);


            if(inputStream != null){
                inputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
