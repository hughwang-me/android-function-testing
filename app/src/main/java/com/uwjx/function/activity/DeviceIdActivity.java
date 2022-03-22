package com.uwjx.function.activity;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.uwjx.function.R;
import com.uwjx.function.deviceid.DeviceInfoUtil;
import com.uwjx.function.service.ActiveMqService;
import com.uwjx.function.util.GSonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceIdActivity extends Activity {

    @BindView(R.id.device_id_info)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_id_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.device_id_device_id)
    public void device_id_device_id(){
        Log.w("hugh" , "device_id_device_id");
        String deviceId = DeviceInfoUtil.getDeviceId(this);
        Log.w("hugh" , "device_id_device_id : " + deviceId);
        textView.setText("获取到的DeviceID : " + deviceId);
    }

    @OnClick(R.id.device_id_serial_id)
    public void device_id_serial_id(){
        String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        textView.setText("获取到的 ANDROID_ID : " + ANDROID_ID);
    }

    @OnClick(R.id.device_id_net)
    void device_id_net(){
        WifiManager wifi = (WifiManager)  getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String wifiMac = info.getMacAddress();
        Log.w("hugh" , "info : " + GSonUtil.toJsonString(info));
        Log.w("hugh" , "wifiMac : " + wifiMac);
        textView.setText("获取到的 WifiInfo : " +  GSonUtil.toJsonString(info) +"\n获取到的mac :" + wifiMac);
    }
}
