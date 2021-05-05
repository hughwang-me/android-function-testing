package com.uwjx.serial.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.uwjx.serial.R;

public class SerialView extends androidx.appcompat.widget.AppCompatTextView {
    public SerialView(Context context) {
        super(context);
        init();
    }

    public SerialView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SerialView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        this.setBackgroundColor(getResources().getColor(R.color.colorThemeTranslucent));
    }
}
