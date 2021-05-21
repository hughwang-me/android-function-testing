package com.uwjx.function.tank;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class TankMainActivity extends Activity {

    //油罐百分比
    private Double tankPercentage;
    //油罐液位高度
    private Integer tankLevel;
    //油罐容积
    private Integer tankVol;

    //油罐类型 （选择）
    private Integer tankType;
    //探针类型 （选择）
    private Integer probeType;
    //高液位报警值（输入）
    private Double highLevelAlarm;
    //密度（输入）
    private Double density;
    //安全容积 （输入）
    private Double safeFillLevel;
    //低液位切断值 (输入)
    private Double lowLevelCutoff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
