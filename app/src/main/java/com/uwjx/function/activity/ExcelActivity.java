package com.uwjx.function.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

import com.uwjx.function.R;
import com.uwjx.function.activity.lifecircle.ActivityLifeCircleActivity;
import com.uwjx.function.domain.Person;
import com.uwjx.function.litepad.LitepadActivity;
import com.uwjx.function.util.ExcelUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExcelActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.excel_export_btn1)
    void excel_export_btn1(){
        File cacheDir = this.getCacheDir();
        Log.w("hugh" , "cacheDir : " + cacheDir);
        File excelFile = new File(cacheDir.getAbsolutePath() , "/excel.xls");
        if(!excelFile.exists()){
            try {
                excelFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String[] title = {"姓名1", "年龄2", "男孩3"};
        String sheetName = "sheetName3";


        List<Person> personList = new ArrayList<>();
        Person demoBean1 = new Person("张1三1", 110, true);
        Person demoBean2 = new Person("小2红1", 112, false);
        Person demoBean3 = new Person("李3四1", 118, true);
        personList.add(demoBean1);
        personList.add(demoBean2);
        personList.add(demoBean3);
        List<String> headers = new ArrayList<>();
        headers.add("姓名");
        headers.add("年龄");
        headers.add("男孩");

        ExcelUtils.generateExcel(excelFile , sheetName , headers , personList);
//        ExcelUtils.initExcel(excelFile, title, sheetName);
//
//
//        ExcelUtils.writeObjListToExcel(personList, excelFile, this);


    }

    @OnClick(R.id.excel_export_btn2)
    void excel_export_btn2(){
        File cacheDir = this.getCacheDir();
        Log.w("hugh" , "cacheDir : " + cacheDir);
        File excelFile = new File(cacheDir.getAbsolutePath() , "/excel.xls");
        if(!excelFile.exists()){
            try {
                excelFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
