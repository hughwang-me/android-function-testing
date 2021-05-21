package com.uwjx.function.activity.lifecircle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.uwjx.function.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLifeCircle_2_Activity extends ActivityLifeCircle_base_Activity {

    private final static String TAG = "LifeCircle2";

    @OnClick(R.id.life_circle_2_btn)
    void life_circle_btn(){
        Intent intent = new Intent(this , ActivityLifeCircle_3_Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecircle_2_layout);
        ButterKnife.bind(this);
        Log.w(TAG , "LifeCircle2 -> onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG , "LifeCircle2 -> onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG , "LifeCircle2 -> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG , "LifeCircle2 -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG , "LifeCircle2 -> onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG , "LifeCircle2 -> onDestroy()");
    }
}
