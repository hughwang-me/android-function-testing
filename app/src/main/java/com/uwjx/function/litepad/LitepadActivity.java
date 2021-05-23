package com.uwjx.function.litepad;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.uwjx.function.R;
import com.uwjx.function.util.GSonUtil;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LitepadActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepad_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.litepad_add)
    void litepad_add(){
        Log.w("hugh" , "@新增@" );
        Random random = new Random();
        int key = random.nextInt(100);
        Book book = new Book();
        book.setName("老人与海" + key);
        book.setPrice(89.8d);
        book.setAuthor("海明威"+key);
        book.setPublishDate(new Date());
        book.save();
        Log.w("hugh" , "保存 : " + GSonUtil.toJsonString(book));
    }

    @OnClick(R.id.litepad_delete)
    void litepad_delete(){
        Log.w("hugh" , "@删除@" );
    }

    @OnClick(R.id.litepad_modify)
    void litepad_modify(){
        Log.w("hugh" , "@修改@" );
    }

    @OnClick(R.id.litepad_query)
    void litepad_query(){
        Log.w("hugh" , "@查询@" );
        List<Book> books = LitePal.findAll(Book.class);
        Gson gson = new Gson();
        for (Book book : books) {
            Log.w("hugh" , "查询:" + gson.toJson(book));
        }
    }

    @OnClick(R.id.litepad_query_one)
    void litepad_query_one(){
        Log.w("hugh" , "@查询@" );
        Book book = LitePal.find(Book.class , 1);
        Gson gson = new Gson();
        Log.w("hugh" , "查询:" + gson.toJson(book));
    }
}
