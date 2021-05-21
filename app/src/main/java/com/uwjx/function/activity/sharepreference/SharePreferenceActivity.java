package com.uwjx.function.activity.sharepreference;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.uwjx.function.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SharePreferenceActivity extends Activity {

    private final static String TAG = "hugh";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.share_preference_save)
    void share_preference_save(){
        Log.w(TAG , " -- 保存  -- ");
        SharePreferenceUtil.saveString(SharePreferenceActivity.this , "key" , "value");
    }

    @OnClick(R.id.share_preference_query)
    void share_preference_query(){
        Log.w(TAG , " -- 查询  -- ");
    }

}
