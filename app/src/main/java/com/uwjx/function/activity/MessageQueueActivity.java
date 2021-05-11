package com.uwjx.function.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.uwjx.function.R;
import com.uwjx.function.service.ActiveMqService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageQueueActivity extends Activity {

    @BindView(R.id.mq_consumer_msg)
    TextView mq_consumer_msg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mq_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mq_connect)
    public void mq_connect(){
        Log.w("hugh" , "MQ 连接");

        ActiveMqService.startService(MessageQueueActivity.this);

    }

    @OnClick(R.id.mq_push)
    public void mq_push(){
        Log.w("hugh" , "MQ 发送消息");
        ActiveMqService.publish("发送MQ消息到 MQTT 服务器");
    }
}
