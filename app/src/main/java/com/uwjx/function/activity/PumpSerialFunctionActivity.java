package com.uwjx.function.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.uwjx.function.R;
import com.uwjx.function.pump.PumpCmdQueue;
import com.uwjx.function.pump.Hoses;
import com.uwjx.function.pump.PumpPreUpgradeCmd;
import com.uwjx.function.pump.PumpQuerySoftwareVersionCmd;
import com.uwjx.function.pump.PumpResetCmd;
import com.uwjx.function.pump.PumpUpgradeCmd;
import com.uwjx.function.event.CmdEvent;
import com.uwjx.function.event.ProbeCmdEvent;
import com.uwjx.function.event.PumpPreUpgradeEvent;
import com.uwjx.function.event.PumpUpgradeEvent;
import com.uwjx.function.probe.ProbeQuerySnCmd;
import com.uwjx.function.pump.PumpUpgradeFileCheckCmd;
import com.uwjx.function.util.ByteUtils;
import com.uwjx.function.util.CRCUtils;
import com.uwjx.function.util.DateUtil;
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
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PumpSerialFunctionActivity extends Activity implements OnOpenSerialPortListener {

    public static final String DEVICE = "device-data";

    SerialPortManager mSerialPortManager;
    PumpCmdQueue pumpCmdQueue = PumpCmdQueue.getInstance();

    @BindView(R.id.pump_device_info)
    TextView pump_device_info;
    @BindView(R.id.pump_send_cmd)
    TextView pump_send_cmd;
    @BindView(R.id.pump_receive_cmd)
    TextView pump_receive_cmd;

    private int hoseIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_pump_function);
        ButterKnife.bind(this);

        Device device = (Device) getIntent().getSerializableExtra(DEVICE);
        Log.i("hugh", "onCreate: device = " + device);
        if (null == device) {
            pump_device_info.setText("??????????????????");
            return;
        }else {
            pump_device_info.setText(device.getName() + " ");
        }

        if(!TextUtils.isEmpty(device.getName())){
            if(device.getName().contains("2")){
                hoseIndex = 1;
                Log.w("hugh" , "1???HOSE");
            }else {
                hoseIndex = 2;
            }
        }

        mSerialPortManager = new SerialPortManager();


        // ????????????
        boolean openSerialPort = mSerialPortManager.setOnOpenSerialPortListener(this)
                .setOnSerialPortDataListener(serialPortDataListener)
                .openSerialPort(device.getFile(), 115200);

        Log.i("hugh", "onCreate: openSerialPort = " + openSerialPort);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void cmdEvent(ProbeCmdEvent probeCmdEvent){
        Log.e("hugh" , "eventbus ????????? probe ?????? : " + probeCmdEvent.getCmd());
        String msg = DateUtil.getFormat() + " \t " + probeCmdEvent.getCmd() + "\n";

        String originMSg = "";
        if(pump_receive_cmd.getText() != null ){
            originMSg= pump_receive_cmd.getText().toString();
        }

        pump_receive_cmd.setText(msg + originMSg);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        pumpCmdQueue.startProcess();

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        pumpCmdQueue.stopProcess();
    }

    OnSerialPortDataListener serialPortDataListener = new OnSerialPortDataListener() {
        @Override
        public void onDataReceived(byte[] bytes) {
//            Log.i(TAG, "onDataReceived [ byte[] ]: " + Arrays.toString(bytes));
//            Log.i(TAG, "onDataReceived [ String ]: " + new String(bytes));
            Log.e("hugh", "????????????????????????16???????????? = " + ByteUtils.genHexStr(bytes));

            pumpCmdQueue.add(ByteUtils.genHexStr(bytes));



        }

        @Override
        public void onDataSent(byte[] bytes) {
//            Log.i(TAG, "onDataSent [ byte[] ]: " + Arrays.toString(bytes));
            Log.e("hugh", "??????????????????????????? [ String ]: " + ByteUtils.genHexStr(bytes));

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
     * ??????????????????
     *
     * @param device ??????
     */
    @Override
    public void onSuccess(File device) {
        Log.w("hugh" , "??????????????????@@@@@@@@@@@@@@@@@@@");
        Toast.makeText(getApplicationContext(), String.format("?????? [%s] ????????????", device.getPath()), Toast.LENGTH_SHORT).show();
        pump_device_info.setText(device.getName() + "\t??????????????????");
        pump_device_info.setTextColor(getResources().getColor(R.color.colorGreen));
    }

    /**
     * ??????????????????
     *
     * @param device ??????
     * @param status status
     */
    @Override
    public void onFail(File device, Status status) {
        Log.w("hugh" , "?????????????????? ###################");
        pump_device_info.setText(device.getName() + "\t??????????????????");
        pump_device_info.setTextColor(getResources().getColor(R.color.colorFontRed));
        switch (status) {
            case NO_READ_WRITE_PERMISSION:
                showDialog(device.getPath(), "??????????????????");
                break;
            case OPEN_FAIL:
            default:
                showDialog(device.getPath(), "??????????????????");
                break;
        }
    }

    private void showDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }



    @OnClick(R.id.pump_query_software_version)
    public void pump_query_software_version(){
        PumpQuerySoftwareVersionCmd querySoftwareVersionCmd = new PumpQuerySoftwareVersionCmd(hoseIndex == 1 ? Hoses.HOSE1 : Hoses.HOSE2);
        byte[] cmd = querySoftwareVersionCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "?????? PumpQuerySoftwareVersionCmd ??????[?????????????????????] ????????? = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_query_sn)
    public void pump_query_sn(){
        ProbeQuerySnCmd querySnCmd = new ProbeQuerySnCmd();
        byte[] cmd = querySnCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "??????querySnCmd??????[???????????????] ????????? = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.pump_pre_upgrade)
    public void probe_pre_upgrade(){
        Log.e("hugh" , "???????????? PumpPreUpgradeEvent ?????? " );
        EventBus.getDefault().post(new PumpPreUpgradeEvent());
    }

    @OnClick(R.id.pump_upgrade)
    public void probe_upgrade(){
        Log.e("hugh" , "???????????? PumpUpgradeEvent ?????? " );
        EventBus.getDefault().post(new PumpUpgradeEvent());
    }

    @OnClick(R.id.pump_upgrade_file_check)
    public void pump_upgrade_file_check(){
        Log.e("hugh" , "???????????? pump_upgrade_file_check ?????? " );
        PumpUpgradeFileCheckCmd upgradeFileCheckCmd = new PumpUpgradeFileCheckCmd(hoseIndex == 1 ? Hoses.HOSE1 : Hoses.HOSE2);
        upgradeFileCheckCmd.setUpgradeFileCheckCrc(binAllByteCrc);
        byte[] cmd = upgradeFileCheckCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "?????? 6C ??????[pump_upgrade_file_check] ????????? = " + ByteUtils.genHexStr(cmd));
    }

    private int binTotalSize = -1;
    private int binEveryBlockSize = 1024;
    private int binLastBlockSize = -1;
    private List<byte[]> binBytes = new ArrayList<>();
    private int binCurrentUpgradedIndex = -1;
    byte[] binAllByte;
    byte[] binAllByteCrc = new byte[2];

    private void loadUpgradeBinInfo() {

        //????????????
        binTotalSize = -1;
        binLastBlockSize = -1;
        binBytes = new ArrayList<>();
        binCurrentUpgradedIndex = -1;

        Log.e("hugh", "loadUpgradeBinInfo ?????? Bin ??????");
        String file = "/storage/udisk/PUMP.bin";
        Log.w("hugh", "loadUpgradeBinInfo ???????????????:" + file);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            binTotalSize = inputStream.available();
            binAllByte = new byte[binTotalSize];
            Log.w("hugh", "loadUpgradeBinInfo Bin ???????????????:" + binTotalSize);

            short index = 0;
            while (inputStream.available() >= 1024) {
                byte[] buffer = new byte[1024];
                inputStream.read(buffer);
                binBytes.add(buffer);
                String currentHex = ByteUtils.bytesToHexStr(buffer);
                Log.w("hugh", "bin ??????" + index + "?????????????????? " + currentHex);
                index++;
            }
            binLastBlockSize = inputStream.available();
            byte[] buffer = new byte[binLastBlockSize];
            inputStream.read(buffer);
            binBytes.add(buffer);
            String currentHex = ByteUtils.bytesToHexStr(buffer);
            Log.w("hugh", "?????????" + index + "?????????????????? " + currentHex);
            Log.w("hugh", "?????? " + binBytes.size() + " ?????????????????? ");
            inputStream.close();

            Log.w("hugh", "????????????bin ??????" + binLastBlockSize);
            int binAllByteIndex = 0;
            for (int i = 0; i < binBytes.size(); i++) {
                byte[] item = binBytes.get(i);
                for (byte b : item) {
                    binAllByte[binAllByteIndex] = b;
                    binAllByteIndex++;
                }
                Log.w("hugh", "item ??????" + item.length);
            }
            Log.w("hugh", "binAllByte ??????" + binAllByteIndex);
            binAllByteCrc = CRCUtils.getCrcByte(binAllByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.pump_reset)
    public void pump_reset(){
        PumpResetCmd resetCmd = new PumpResetCmd(hoseIndex == 1 ? Hoses.HOSE1 : Hoses.HOSE2);
        byte[] cmd = resetCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "?????? 6B ??????[????????????] ????????? = " + ByteUtils.genHexStr(cmd));
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void preUpgradeEvent(PumpPreUpgradeEvent preUpgradeEvent) {
        Log.e("hugh", "eventbus ????????? PumpPreUpgradeEvent ?????? ");
        loadUpgradeBinInfo();
        String file = "/storage/udisk/PUMP.bin";

        byte [] lengthByte = new byte[2];
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Log.w("hugh" , "Bin ????????????:" + inputStream.available());
            int size = inputStream.available();
            byte[] byteSize = ByteUtils.shortToByteArr((short) size);
            lengthByte[0] = byteSize[0];
            lengthByte[1] = byteSize[1];
            // ???????????????
            if(inputStream != null){
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PumpPreUpgradeCmd preUpgradeCmd = new PumpPreUpgradeCmd(hoseIndex == 1 ? Hoses.HOSE1 : Hoses.HOSE2);
        preUpgradeCmd.setDataLength(lengthByte);
        byte[] cmd = preUpgradeCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "??????69??????[?????????] ????????? = " + ByteUtils.genHexStr(cmd));
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void pumpUpgradeEvent(PumpUpgradeEvent pumpUpgradeEvent){
        Log.e("hugh" , "eventbus ????????? PumpUpgradeEvent ?????? " );

        String file = "/storage/udisk/PUMP.bin";

        try {
            FileInputStream inputStream = new FileInputStream(file);
            int total = inputStream.available();
            Log.w("hugh" , "Bin ???????????????:" + total);

            short index = 0;
            while (inputStream.available() >= 1024){
                byte[] buffer = new byte[1024];
                inputStream.read(buffer);

                byte[] offsetByte = ByteUtils.shortToByteArr((short) (index * 1024));
                byte [] lengthByte = ByteUtils.shortToByteArr((short)1024);
                PumpUpgradeCmd upgradeCmd = new PumpUpgradeCmd(hoseIndex == 1 ? Hoses.HOSE1 : Hoses.HOSE2);
                upgradeCmd.setDateLength(lengthByte);
                upgradeCmd.setAddressOffset(offsetByte);
                upgradeCmd.setData(buffer);

                byte[] cmd = upgradeCmd.getSendCmd();
                mSerialPortManager.sendBytes(cmd);
                Log.i("hugh", "??? "+index+" ?????????  [????????????] ????????? = " + ByteUtils.genHexStr(cmd));

                String currentHex = ByteUtils.bytesToHexStr(buffer);
                Log.w("hugh" , "?????????"+index+"?????????????????? " + currentHex);
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

            PumpUpgradeCmd upgradeCmd = new PumpUpgradeCmd(hoseIndex == 1 ? Hoses.HOSE1 : Hoses.HOSE2);
            upgradeCmd.setDateLength(lengthByte);
            upgradeCmd.setAddressOffset(offsetByte);
            upgradeCmd.setData(buffer);

            byte[] cmd = upgradeCmd.getSendCmd();
            mSerialPortManager.sendBytes(cmd);
            Log.i("hugh", "??????30??????[????????????] ????????? = " + ByteUtils.genHexStr(cmd));

            String currentHex = ByteUtils.bytesToHexStr(buffer);
            Log.w("hugh" , "?????????"+index+"?????????????????? " + currentHex);


            inputStream.close();
            inputStream = null;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void CmdEvent(CmdEvent cmdEvent){
        Log.w("hugh" , "??????????????????????????????:" + cmdEvent.getCmd());
        pump_receive_cmd.append(DateUtil.getFormat() + " \t " + cmdEvent.getCmd()+ "\n");
    }
}
