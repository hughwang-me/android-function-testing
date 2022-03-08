package com.uwjx.function.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.task.DownloadTask;
import com.uwjx.function.R;
import com.uwjx.function.activity.emqx.EmqxActivity;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StorageActivity extends Activity {

    @OnClick(R.id.storage_btn1)
    void storage_btn1(){
        Log.w("func" , "查看文件系统");
        File file1 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        Log.w("func" , "file1查看DIRECTORY_DOWNLOADS : " + file1.getAbsolutePath());
        Log.w("func" , "file1是否存在 : " + file1.exists());
        Log.w("func" , "file1是否可读 : " + file1.canRead());
        Log.w("func" , "file1是否可写 : " + file1.canWrite());
        Log.w("func" , "file1是否可执行 : " + file1.canExecute());

        File sdCard = Environment.getExternalStorageDirectory();
        Log.w("func" , "查看getExternalStorageDirectory : " + sdCard.getAbsolutePath());

        boolean exists = sdCard.exists();
        Log.w("func" , "sdCard是否存在 : " + exists);
        Log.w("func" , "sdCard是否可读 : " + sdCard.canRead());
        Log.w("func" , "sdCard是否可写 : " + sdCard.canWrite());
        Log.w("func" , "sdCard是否可执行 : " + sdCard.canExecute());
    }

    @OnClick(R.id.storage_btn2)
    void storage_btn2(){
        Log.w("func" , "开始下载");
        File file1 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        Log.w("func" , "查看DIRECTORY_DOWNLOADS : " + file1.getAbsolutePath());

        File sdCard = Environment.getExternalStorageDirectory();

        Log.w("func" , "查看getExternalStorageDirectory : " + sdCard.getAbsolutePath());
        String url = "https://levo-atg.oss-ap-southeast-2.aliyuncs.com/dc2-apk/DC2-AR-V1.0.0.22.apk";
        String[] urls = url.split("/");
        String filePath = file1.getAbsolutePath() + "/" + urls[urls.length-1];
        Log.w("func" , "filePath : " + filePath);
        long taskId = Aria.download(this)
                .load(url)     //读取下载地址
                .setFilePath(filePath) //设置文件保存的完整路径
                .create();   //创建并启动下载
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_storage);
        ButterKnife.bind(this);
        Aria.download(this).register();


    }

    //在这里处理任务执行中的状态，如进度进度条的刷新
    @Download.onTaskRunning
    protected void running(DownloadTask task) {

        int p = task.getPercent();	//任务进度百分比
        String speed = task.getConvertSpeed();	//转换单位后的下载速度，单位转换需要在配置文件中打开
        long speed1 = task.getSpeed(); //原始byte长度速度
        Log.w("func" , "下载中： 百分比 " + p + "  速度 " + speed1);
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        //在这里处理任务完成的状态
        Log.w("func" , "下载完成");
    }
}
