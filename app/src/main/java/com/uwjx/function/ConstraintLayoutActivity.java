package com.uwjx.function;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import butterknife.ButterKnife;

public class ConstraintLayoutActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        ButterKnife.bind(this);
    }
}
