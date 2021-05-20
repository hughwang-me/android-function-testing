package com.uwjx.function.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.uwjx.function.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLifeCircleActivity extends Activity {

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG , "ActivityLifeCircleActivity -> onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG , "ActivityLifeCircleActivity -> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG , "ActivityLifeCircleActivity -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG , "ActivityLifeCircleActivity -> onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG , "ActivityLifeCircleActivity -> onDestroy()");
    }
}
