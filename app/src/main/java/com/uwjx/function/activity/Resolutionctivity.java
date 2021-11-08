package com.uwjx.function.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.Nullable;

import com.uwjx.function.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Resolutionctivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resolution_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.resolution_start)
    void resolution_start(){
        Log.w("hugh" , "输出分辨率");
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        Log.w("hugh" , "输出分辨率 screenWidth : " + screenWidth);
        Log.w("hugh" , "输出分辨率 screenHeight: " + screenHeight);
    }


}
