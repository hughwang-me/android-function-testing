package com.uwjx.function.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.uwjx.function.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Resolutionctivity extends Activity {

    @BindView(R.id.resolution_info)
    TextView resolution_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resolution_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.resolution_start)
    void resolution_start(){
        Log.w("hugh" , "输出分辨率");
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        int densityDpi = dm.densityDpi;
        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;

        StringBuilder sb  = new StringBuilder();

        Log.w("hugh" , "输出分辨率 density : " + density);
        sb.append("输出分辨率 density : " + density);
        sb.append("\n");
        Log.w("hugh" , "输出分辨率 densityDpi : " + densityDpi);
        sb.append("输出分辨率 densityDpi : " + densityDpi);
        sb.append("\n");
        Log.w("hugh" , "输出分辨率 widthPixels : " + widthPixels);
        sb.append("输出分辨率 widthPixels : " + widthPixels);
        sb.append("\n");
        Log.w("hugh" , "输出分辨率 heightPixels: " + heightPixels);
        sb.append("输出分辨率 heightPixels : " + heightPixels);
        sb.append("\n");

        resolution_info.setText(sb.toString());
    }


}
