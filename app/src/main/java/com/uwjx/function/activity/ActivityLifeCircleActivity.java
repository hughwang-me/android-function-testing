package com.uwjx.function.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.uwjx.function.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLifeCircleActivity extends ActivityLifeCircle_base_Activity {

    private final static String TAG = "LifeCircleActivity";

    @OnClick(R.id.life_circle_btn)
    void life_circle_btn(){
        Intent intent = new Intent(this , ActivityLifeCircle_2_Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecircle_layout);
        ButterKnife.bind(this);
        Log.w(TAG , "ActivityLifeCircleActivity -> onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG , "ActivityLifeCircleActivity -> onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG , "ActivityLifeCircleActivity -> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG , "ActivityLifeCircleActivity -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG , "ActivityLifeCircleActivity -> onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG , "ActivityLifeCircleActivity -> onDestroy()");
    }
}
