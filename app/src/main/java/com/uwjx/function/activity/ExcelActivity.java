package com.uwjx.function.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.uwjx.function.R;
import com.uwjx.function.activity.lifecircle.ActivityLifeCircleActivity;
import com.uwjx.function.litepad.LitepadActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExcelActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.excel_export_btn1)
    void excel_export_btn1(){

    }



}
