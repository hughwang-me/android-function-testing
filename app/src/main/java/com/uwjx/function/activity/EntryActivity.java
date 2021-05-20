package com.uwjx.function.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.uwjx.function.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntryActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.entry_lifecircle)
    void entry_lifecircle(){
        startActivity(new Intent(this , ActivityLifeCircleActivity.class));
    }
}
