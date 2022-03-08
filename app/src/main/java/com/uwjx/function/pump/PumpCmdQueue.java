package com.uwjx.function.pump;

import android.util.Log;


import com.uwjx.function.event.CmdEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class PumpCmdQueue {

    private static PumpCmdQueue pumpCmdQueue;

    private PumpCmdQueue() {
    }

    public static PumpCmdQueue getInstance(){
        if(pumpCmdQueue == null){
            pumpCmdQueue = new PumpCmdQueue();
        }
        return pumpCmdQueue;
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
                Log.e("hugh" , "开启cmd queue 处理线程@@@@@@@@@@@@@@@@@@@@@@@@" );
                StringBuilder full = new StringBuilder();
                while (isProcess){
                    String cmd = queue.poll();
                    Log.e("hugh" , "开启cmd queue ----------------------------------- isProcess : " +isProcess );
                    if(cmd != null){
                        Log.e("hugh" , "开启cmd queue -----------------------------------1111111" );
                        full.append(cmd);
                        Log.w("hugh" , "full 当前状态:" + full.toString());
                        if(full.toString().trim().startsWith("2021") && full.toString().trim().endsWith("2022")){
                            EventBus.getDefault().post(new CmdEvent(full.toString().trim()));
                            full.delete(0,full.length());
                        }else {
                            if(full.toString().trim().contains("2022")){
                                String[] splits = full.toString().trim().split("2022");
                                Log.w("hugh" , "full splits [0]:" + splits[0]);
                                Log.w("hugh" , "full splits [01]:" + splits[1]);
                                EventBus.getDefault().post(new CmdEvent(splits[0]));
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
