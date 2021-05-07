package com.uwjx.function;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.uwjx.function.event.ProbeCmdEvent;
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

public class PumpSerialFunctionActivity extends Activity implements OnOpenSerialPortListener {

    public static final String DEVICE = "device-data";

    SerialPortManager mSerialPortManager;
    ProbeCmdQueue probeCmdQueue = ProbeCmdQueue.getInstance();

    @BindView(R.id.pump_device_info)
    TextView pump_device_info;
    @BindView(R.id.pump_send_cmd)
    TextView pump_send_cmd;
    @BindView(R.id.pump_receive_cmd)
    TextView pump_receive_cmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_pump_function);
        ButterKnife.bind(this);

        Device device = (Device) getIntent().getSerializableExtra(DEVICE);
        Log.i("hugh", "onCreate: device = " + device);
        if (null == device) {
            pump_device_info.setText("设备信息为空");
            return;
        }else {
            pump_device_info.setText(device.getName() + " ");
        }

//        //监听otg插入 拔出
//        IntentFilter usbDeviceStateFilter = new IntentFilter();
//        usbDeviceStateFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
//        usbDeviceStateFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
//        registerReceiver(UDiskMountedReceiver, usbDeviceStateFilter);
//        //注册监听自定义广播
//        IntentFilter filter = new IntentFilter(Constant.ACTION_USB_PERMISSION);
//        registerReceiver(UDiskMountedReceiver, filter);


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
        if(pump_receive_cmd.getText() != null ){
            originMSg= pump_receive_cmd.getText().toString();
        }

        pump_receive_cmd.setText(msg + originMSg);

        //            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    pump_send_cmd.append(DateUtil.getFormat() + " \t " + ByteUtils.genHexStr(bytes) + "\n");
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
//                    pump_receive_cmd.append(DateUtil.getFormat() + " \t " + ByteUtils.genHexStr(bytes) + "\n");
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
                    if(pump_receive_cmd.getText() != null ){
                        originSentMSg= pump_send_cmd.getText().toString();
                    }

                    pump_send_cmd.setText(sentMsg + originSentMSg);
//                    pump_send_cmd.append();



                    String msg = "-----------------------------------------------------------------\n";

                    String originMSg = "";
                    if(pump_receive_cmd.getText() != null ){
                        originMSg= pump_receive_cmd.getText().toString();
                    }

                    pump_receive_cmd.setText(msg + originMSg);
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
        pump_device_info.setText(device.getName() + "\t串口打开成功");
        pump_device_info.setTextColor(getResources().getColor(R.color.colorGreen));
    }

    /**
     * 串口打开失败
     *
     * @param device 串口
     * @param status status
     */
    @Override
    public void onFail(File device, Status status) {
        Log.w("hugh" , "串口打开失败 ###################");
        pump_device_info.setText(device.getName() + "\t串口打开失败");
        pump_device_info.setTextColor(getResources().getColor(R.color.colorFontRed));
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

    @OnClick(R.id.pump_query_liquid_level_1)
    public void pump_query_liquid_level_1(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(1);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位 1] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_query_liquid_level_2)
    public void pump_query_liquid_level_2(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(2);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位 2] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_query_liquid_level_3)
    public void pump_query_liquid_level_3(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(3);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位 3] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_query_liquid_level_4)
    public void pump_query_liquid_level_4(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(4);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位 4] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_query_liquid_level_all)
    public void pump_query_liquid_level(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(0);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位 all] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_query_software_version)
    public void pump_query_software_version(){
        ProbeQuerySoftwareVersionCmd querySoftwareVersionCmd = new ProbeQuerySoftwareVersionCmd();
        byte[] cmd = querySoftwareVersionCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发querySoftwareVersionCmd指令[查询软件版本号] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_query_sn)
    public void pump_query_sn(){
        ProbeQuerySnCmd querySnCmd = new ProbeQuerySnCmd();
        byte[] cmd = querySnCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发querySnCmd指令[查询序列号] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_open_24v)
    public void pump_open_24v(){
        ProbeOpen24VCmd open24VCmd = new ProbeOpen24VCmd(1);
        byte[] cmd = open24VCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发open24VCmd指令[开启 24V] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_open_relay)
    public void pump_open_relay(){
        ProbeOpenRelayCmd openRelayCmd = new ProbeOpenRelayCmd(1);
        byte[] cmd = openRelayCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发openRelayCmd指令[开启继电器] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_pre_upgrade)
    public void pump_pre_upgrade(){

        byte [] lengthByte = new byte[2];
        lengthByte[0] = 0x21;
        lengthByte[1] = 0x21;

        ProbePreUpgradeCmd preUpgradeCmd = new ProbePreUpgradeCmd(lengthByte);
        byte[] cmd = preUpgradeCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[预升级] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_upgrade)
    public void pump_upgrade(){
        Log.w("wanghuan" , "Pump 预升级 处理");
        byte [] lengthByte = new byte[2];
        lengthByte[0] = 0x21;
        lengthByte[1] = 0x21;

        byte [] offsetByte = new byte[2];
        offsetByte[0] = 0x21;
        offsetByte[1] = 0x21;

        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("PUMP.bin");
            FileInputStream input = (FileInputStream)inputStream;
            Log.w("hugh" , "Bin 文件长度:" + input.available());
            // 关闭输入流
            if(input != null){
                input.close();
            }
            if(inputStream != null){
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        byte[] data = FileReadUtils.getByteStream("");

        byte [] dataByte = new byte[2];
        offsetByte[0] = 0x21;
        offsetByte[1] = 0x21;

//        ProbeUpgradeCmd upgradeCmd = new ProbeUpgradeCmd(lengthByte , offsetByte,dataByte );
//        byte[] cmd = upgradeCmd.getSendCmd();
//        mSerialPortManager.sendBytes(cmd);
//        Log.i("hugh", "下发30指令[升级] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_reset)
    public void pump_reset(){
        ProbeResetCmd resetCmd = new ProbeResetCmd();
        byte[] cmd = resetCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发resetCmd指令[复位指令] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

}
