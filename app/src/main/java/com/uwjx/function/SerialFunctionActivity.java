package com.uwjx.function;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.uwjx.function.cmd.Hoses;
import com.uwjx.function.cmd.QueryRealTimeOilingCmd;
import com.uwjx.function.probe.ProbeQueryLiquidLevelCmd;
import com.uwjx.function.util.ByteUtils;
import com.uwjx.function.util.DateUtil;
import com.uwjx.serial.Device;
import com.uwjx.serial.SerialPortManager;
import com.uwjx.serial.listener.OnOpenSerialPortListener;
import com.uwjx.serial.listener.OnSerialPortDataListener;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SerialFunctionActivity extends Activity implements OnOpenSerialPortListener {

    public static final String DEVICE = "device-data";

    SerialPortManager mSerialPortManager;

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

    OnSerialPortDataListener serialPortDataListener = new OnSerialPortDataListener() {
        @Override
        public void onDataReceived(byte[] bytes) {
//            Log.i(TAG, "onDataReceived [ byte[] ]: " + Arrays.toString(bytes));
//            Log.i(TAG, "onDataReceived [ String ]: " + new String(bytes));
            Log.e("hugh", "接收到来自设备的16进制数据 = " + ByteUtils.genHexStr(bytes));
//            cmdQueue.add(ByteUtils.genHexStr(bytes));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    probe_receive_cmd.append(DateUtil.getFormat() + " \t " + ByteUtils.genHexStr(bytes) + "\n");
                }
            });
        }

        @Override
        public void onDataSent(byte[] bytes) {
//            Log.i(TAG, "onDataSent [ byte[] ]: " + Arrays.toString(bytes));
            Log.e("hugh", "数据下发到设备成功 [ String ]: " + ByteUtils.genHexStr(bytes));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    probe_send_cmd.append(DateUtil.getFormat() + " \t " + ByteUtils.genHexStr(bytes) + "\n");
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

    @OnClick(R.id.probe_query_liquid_level)
    public void probe_query_liquid_level(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(1);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_query_software_version)
    public void probe_query_software_version(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(1);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_query_sn)
    public void probe_query_sn(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(1);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_open_24v)
    public void probe_open_24v(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(1);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_open_relay)
    public void probe_open_relay(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(1);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_pre_upgrade)
    public void probe_pre_upgrade(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(1);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_upgrade)
    public void probe_upgrade(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(1);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

    @OnClick(R.id.probe_reset)
    public void probe_reset(){
        ProbeQueryLiquidLevelCmd probeQueryLiquidLevelCmd = new ProbeQueryLiquidLevelCmd(1);
        byte[] cmd = probeQueryLiquidLevelCmd.getSendCmd();
        mSerialPortManager.sendBytes(cmd);
        Log.i("hugh", "下发30指令[查询液位] 到设备 = " + ByteUtils.genHexStr(cmd));
    }

}
