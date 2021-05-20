package com.uwjx.function.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.uwjx.function.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLifeCircle_base_Activity extends Activity {

    private final static String TAG = "base_Activity";
 
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(TAG , "base_Activity -> onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG , "base_Activity -> onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG , "base_Activity -> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG , "base_Activity -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG , "base_Activity -> onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG , "base_Activity -> onDestroy()");
    }
}
