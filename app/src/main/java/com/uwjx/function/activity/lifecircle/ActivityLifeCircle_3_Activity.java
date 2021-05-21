package com.uwjx.function.activity.lifecircle;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.uwjx.function.R;

import butterknife.ButterKnife;

public class ActivityLifeCircle_3_Activity extends ActivityLifeCircle_base_Activity {

    private final static String TAG = "LifeCircle_3";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecircle_3_layout);
        ButterKnife.bind(this);
        Log.w(TAG , "LifeCircle_3 -> onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG , "LifeCircle_3 -> onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG , "LifeCircle_3 -> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG , "LifeCircle_3 -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG , "LifeCircle_3 -> onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG , "LifeCircle_3 -> onDestroy()");
    }
}
