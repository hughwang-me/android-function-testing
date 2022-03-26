package com.uwjx.function.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.Nullable;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.task.DownloadTask;
import com.uwjx.function.R;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class KeypadActivity extends Activity {

    @OnClick(R.id.keypad_1)
    void keypad_1(){
        Log.w("func" , "按键测试");
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_keypad);
        ButterKnife.bind(this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        //WWW.WCH.CH
        Log.w("func" , "键盘型号:" + event.getDevice().getName());
        if (event.getDevice().getName().contains("WWW.WCH.CH") && (event.getAction() == KeyEvent.ACTION_DOWN)) {
            Log.w("func" , "按键 DOWN :" + event.getKeyCode());
        }
        if (event.getDevice().getName().contains("WWW.WCH.CH") && (event.getAction() == KeyEvent.ACTION_UP)) {
            Log.w("func" , "按键 UP :" + event.getKeyCode());

        }
        if (event.getDevice().getName().contains("WWW.WCH.CH") && (event.getAction() == KeyEvent.FLAG_LONG_PRESS)) {
            Log.w("func" , "按键 FLAG_LONG_PRESS :" + event.getKeyCode());

        }
        if (event.getDevice().getName().contains("WWW.WCH.CH") && (event.getAction() == KeyEvent.FLAG_CANCELED)) {
            Log.w("func" , "按键 FLAG_CANCELED :" + event.getKeyCode());

        }
        if (event.getDevice().getName().contains("WWW.WCH.CH") && (event.getAction() == KeyEvent.FLAG_CANCELED_LONG_PRESS)) {
            Log.w("func" , "按键 FLAG_CANCELED_LONG_PRESS :" + event.getKeyCode());

        }

        //F1 131
        //F2 132
        //ENTER 66
        //CANCEL 111 4
        //CLR 67
        return super.dispatchKeyEvent(event);
    }
}
