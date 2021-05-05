package com.uwjx.function.probe;

import android.util.Log;

import com.uwjx.function.event.CmdEvent;
import com.uwjx.function.event.ProbeCmdEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ProbeCmdQueue {

    private static ProbeCmdQueue cmdQueue;

    private ProbeCmdQueue() {
    }

    public static ProbeCmdQueue getInstance(){
        if(cmdQueue == null){
            cmdQueue = new ProbeCmdQueue();
        }
        return cmdQueue;
    }

    private boolean isProcess = true;

    private Queue<String> queue = new LinkedList<>();

    public void add(String cmd){
        queue.offer(cmd);
    }

    /**
     *      add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
     * 　　remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
     * 　　element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
     * 　　offer       添加一个元素并返回true       如果队列已满，则返回false
     * 　　poll         移除并返问队列头部的元素    如果队列为空，则返回null
     * 　　peek       返回队列头部的元素             如果队列为空，则返回null
     * 　　put         添加一个元素                      如果队列满，则阻塞
     * 　　take        移除并返回队列头部的元素     如果队列为空，则阻塞
     */
    public void startProcess(){
        new Thread(){
            @Override
            public void run() {
                Log.e("hugh" , "@@@@@@@@@@@@@@@@@@@开启 probe cmd queue 处理线程@@@@@@@@@@@@@@@@@@@@@@@@" );
                StringBuilder full = new StringBuilder();
                while (isProcess){
                    String cmd = queue.poll();
                    if(cmd != null){
                        full.append(cmd);
                        Log.w("hugh" , "full 当前状态:" + full.toString());
                        if(full.toString().trim().startsWith("68") && full.toString().trim().endsWith("16")){
                            EventBus.getDefault().post(new ProbeCmdEvent(full.toString().trim()));
                            Log.e("hugh" , "抛出完整指令:" + full.toString().trim());
                            full.delete(0,full.length());

                        }else {
                            if(full.toString().trim().contains("16")){
                                String[] splits = full.toString().trim().split("16");
                                Log.w("hugh" , "full splits [0]:" + splits[0]);
                                Log.w("hugh" , "full splits [01]:" + splits[1]);
                                EventBus.getDefault().post(new ProbeCmdEvent(splits[0]));
                                full.delete(0,full.length());
                                full.append(splits[1]);
                            }
                        }

                    }else {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    public void stopProcess(){
        isProcess = false;
    }
}
