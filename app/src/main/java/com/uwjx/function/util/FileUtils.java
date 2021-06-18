package com.uwjx.function.util;

import android.os.Environment;
import android.util.Log;

public class FileUtils {

    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        Log.w("hugh" , "外部存储是否可以写 : " + state);
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable(){
        String state =Environment.getExternalStorageState();
        Log.w("hugh" , "外部存储是否可以读 : " + state);
        if(Environment.MEDIA_MOUNTED.equals(state)||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;
    }

}
