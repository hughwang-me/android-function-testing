package com.uwjx.function.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.uwjx.function.R;
import com.uwjx.function.activity.lifecircle.ActivityLifeCircleActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntryActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.entry_lifecircle)
    void entry_lifecircle(){
        startActivity(new Intent(this , ActivityLifeCircleActivity.class));
    }

    @OnClick(R.id.entry_serial_port_atg)
    void entry_serial_port_atg(){
        Intent intent = new Intent(this , SerialMainActivity.class);
        intent.putExtra("pumpOrAtg" , "atg");
        startActivity(intent);
    }

    @OnClick(R.id.entry_serial_port_pump)
    void entry_serial_port_pump(){
        Intent intent = new Intent(this , SerialMainActivity.class);
        intent.putExtra("pumpOrAtg" , "pump");
        startActivity(intent);
    }
}
