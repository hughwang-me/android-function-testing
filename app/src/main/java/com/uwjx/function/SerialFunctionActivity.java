package com.uwjx.function;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.uwjx.function.util.ByteUtils;
import com.uwjx.serial.Device;
import com.uwjx.serial.SerialPortManager;
import com.uwjx.serial.listener.OnOpenSerialPortListener;
import com.uwjx.serial.listener.OnSerialPortDataListener;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SerialFunctionActivity extends Activity implements OnOpenSerialPortListener {

    public static final String DEVICE = "device-data";

    SerialPortManager mSerialPortManager;

    @BindView(R.id.device_info)
    TextView device_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_function);
        ButterKnife.bind(this);

        Device device = (Device) getIntent().getSerializableExtra(DEVICE);
        Log.i("hugh", "onCreate: device = " + device);
        if (null == device) {
            device_info.setText("设备信息为空");
            return;
        }else {
            device_info.setText(device.getName() + " ");
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
        }

        @Override
        public void onDataSent(byte[] bytes) {
//            Log.i(TAG, "onDataSent [ byte[] ]: " + Arrays.toString(bytes));
            Log.e("hugh", "数据下发到设备成功 [ String ]: " + ByteUtils.genHexStr(bytes));
//            final byte[] finalBytes = bytes;
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    showToast(String.format("发送\n%s", new String(finalBytes)));
//                }
//            });
        }
    };

    @Override
    public void onSuccess(File device) {
        Log.w("hugh" , "串口打开成功@@@@@@@@@@@@@@@@@@@");
    }

    @Override
    public void onFail(File device, Status status) {
        Log.w("hugh" , "串口打开失败 ###################");
    }
}
