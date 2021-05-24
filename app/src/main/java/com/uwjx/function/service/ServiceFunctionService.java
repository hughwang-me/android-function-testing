package com.uwjx.function.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServiceFunctionService extends Service {

    private final static String TAG = "ServiceFunction";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.w(TAG , "onBind()");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w(TAG , "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(TAG , "onStartCommand()");



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.w(TAG , "onLowMemory()");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.w(TAG , "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.w(TAG , "onRebind()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w(TAG , "onDestroy()");
    }
}
