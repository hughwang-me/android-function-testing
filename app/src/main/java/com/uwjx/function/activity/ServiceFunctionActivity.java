package com.uwjx.function.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.uwjx.function.R;
import com.uwjx.function.service.ServiceFunctionService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceFunctionActivity extends Activity {

    private final static String TAG = "ServiceFunctionActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_function_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.service_function_start)
    void service_function_start(){
        Intent startServiceIntent = new Intent(this , ServiceFunctionService.class);
        startService(startServiceIntent);
    }

    @OnClick(R.id.service_function_stop)
    void service_function_stop(){
        Intent startServiceIntent = new Intent(this , ServiceFunctionService.class);
        stopService(startServiceIntent);
    }

}
