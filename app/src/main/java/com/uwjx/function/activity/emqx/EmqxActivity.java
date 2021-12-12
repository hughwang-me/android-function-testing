package com.uwjx.function.activity.emqx;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.uwjx.function.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmqxActivity extends Activity {

    private final static String TAG = "EmqxActivity";

    @BindView(R.id.emqx_msg)
    TextView emqxMsg;


    @OnClick(R.id.emqx_send_1)
    void emqx_send_1(){
        Log.w(TAG , "EmqxActivity -> emqx_send_1()");
    }

    @OnClick(R.id.emqx_reg)
    void emqx_reg(){
        Log.w(TAG , "EmqxActivity -> emqx_reg()");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_emqx);
        ButterKnife.bind(this);
        Log.w(TAG , "EmqxActivity -> onCreate()");
    }

}
