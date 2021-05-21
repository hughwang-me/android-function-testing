package com.uwjx.function.activity.sharepreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {

    private final static String SP_NAME = "tanli-uwjx";

    private final static String STR_DEFAULT = "";

    private final static int INT_DEFAULT = -999;

    public static void saveString(Context mContext , String key , String value){
        SharedPreferences.Editor editor = mContext.getSharedPreferences(SP_NAME , Activity.MODE_PRIVATE).edit();
        editor.putString(key , value);
        editor.apply();
    }

    public static String getString(Context mContext , String key ){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME , Activity.MODE_PRIVATE);
        return sharedPreferences.getString(key , STR_DEFAULT);
    }

    public static void saveInt(Context mContext , String key , int value){
        SharedPreferences.Editor editor = mContext.getSharedPreferences(SP_NAME , Activity.MODE_PRIVATE).edit();
        editor.putInt(key , value);
        editor.apply();
    }

    public static int getInt(Context mContext , String key ){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME , Activity.MODE_PRIVATE);
        return sharedPreferences.getInt(key , INT_DEFAULT);
    }

}
