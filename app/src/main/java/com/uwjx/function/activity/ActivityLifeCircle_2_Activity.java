package com.uwjx.function.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.uwjx.function.R;

import butterknife.ButterKnife;

public class ActivityLifeCircle_2_Activity extends Activity {

    private final static String TAG = "LifeCircle2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecircle_2_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG , "LifeCircle2 -> onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG , "LifeCircle2 -> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG , "LifeCircle2 -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG , "LifeCircle2 -> onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG , "LifeCircle2 -> onDestroy()");
    }
}
