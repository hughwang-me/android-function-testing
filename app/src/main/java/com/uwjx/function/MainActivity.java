package com.uwjx.function;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uwjx.serial.Device;
import com.uwjx.serial.Driver;
import com.uwjx.serial.SerialPortFinder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout ;
    TextView tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linear_layout);
        tips = findViewById(R.id.tips);

        SerialPortFinder serialPortFinder = new SerialPortFinder();
        try {
            Log.w("hugh" , "获取驱动列表");
            List<Driver> drivers = serialPortFinder.getDrivers();
            for (Driver driver : drivers) {
                Log.w("hugh" , "结果: name : " + driver.getName() + "  root : " + driver.getRoot());
            }

            Log.w("hugh" , "获取设备列表");
            ArrayList<Device> devices = serialPortFinder.getDevices();

            if(devices.size() == 0){
                tips.setText("没有发现可用设备");
                return;
            }else {
                tips.setText("发现可用设备 : " + devices.size() + "个");
            }

            int i = 0;
            for (Device device : devices) {
                Log.w("hugh" , "设备 name: " + device.getName() + " root : " + device.getRoot() + " file : " + device.getFile().getAbsolutePath());

                Button button = new Button(this);
                button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));

                File file = device.getFile();
                boolean canRead = file.canRead();
                boolean canWrite = file.canWrite();
                boolean canExecute = file.canExecute();
                String path = file.getAbsolutePath();

                StringBuffer permission = new StringBuffer();
                permission.append("\t权限[");
                permission.append(canRead ? " 可读 " : " 不可读 ");
                permission.append(canWrite ? " 可写 " : " 不可写 ");
                permission.append(canExecute ? " 可执行 " : " 不可执行 ");
                permission.append("]");


                button.setText(String.format("%s [%s] (%s)  %s", device.getName() , device.getRoot() , path, permission));
                button.setTag(device);
                button.setOnClickListener(clickListener);
                linearLayout.addView(button , i);

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Device device = (Device)v.getTag();
            Log.w("hugh" , "点击的设备 : " + device.toString());
            Intent intent = new Intent(getBaseContext(), SerialFunctionActivity.class);
            intent.putExtra(SerialFunctionActivity.DEVICE, device);
            startActivity(intent);
        }
    };
}