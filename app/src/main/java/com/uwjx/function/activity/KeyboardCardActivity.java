package com.uwjx.function.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeyboardCardActivity extends Activity   {

//    @BindView(R.id.keyboard_card_result)
//    TextView result;
//    String info="";
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.keyboard_card_layout);
//        ButterKnife.bind(this);
//        Log.w("hugh" , "FLAG_LONG_PRESS: " + KeyEvent.FLAG_LONG_PRESS);
//        Log.w("hugh" , "FLAG_CANCELED_LONG_PRESS: " + KeyEvent.FLAG_CANCELED_LONG_PRESS);
//    }
//
//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        Log.w("hugh" , "onKey keyCode -> " + keyCode);
//        info += keyCode;
//        result.setText(info);
//        return false;
//    }
//
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        String msg = "dispatchKeyEvent keyCode -> " + event.getKeyCode() + " name : " + event.getDevice().getName() + " action : " + event.getAction();
//        Log.w("hugh" ,msg );
//        return super.dispatchKeyEvent(event);
//    }
}
