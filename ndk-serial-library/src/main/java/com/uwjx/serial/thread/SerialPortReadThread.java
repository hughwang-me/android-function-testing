package com.uwjx.serial.thread;

import android.util.Log;


import com.uwjx.serial.TextUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Kongqw on 2017/11/14.
 * 串口消息读取线程
 */

public abstract class SerialPortReadThread extends Thread {

    public abstract void onDataReceived(byte[] bytes);

    private static final String TAG = SerialPortReadThread.class.getSimpleName();
    private InputStream mInputStream;
    private byte[] mReadBuffer;

    public SerialPortReadThread(InputStream inputStream) {
        mInputStream = inputStream;
        mReadBuffer = new byte[1024];
    }

    @Override
    public void run() {
        super.run();
        Log.i(TAG, "@@@@@@@@@@@@@@@@@ 读取线程运行中 @@@@@@@@@@@@@@@@@@@@@@@@ " + isInterrupted());
//        StringBuilder cmdCached = new StringBuilder();
//        byte[] bytesCached = new byte[1024];
        while (!isInterrupted()) {
            try {
                Log.i(TAG, "读取串口返回的数据中#####################################################");
                if (null == mInputStream) {
                    Log.i(TAG, "mInputStream: null!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
                    return;
                }

                Log.e(TAG, "读取线程等待中 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
                int size = mInputStream.read(mReadBuffer);
                Log.w(TAG , "读取到的size : " + size);
                if (0 >= size) {
                    return;
                }

                byte[] readBytes = new byte[size];

                System.arraycopy(mReadBuffer, 0, readBytes, 0, size);

//                Log.i(TAG, "run: readBytes = " + new String(readBytes));
                Log.e(TAG, "串口读取线程获取到设备返回的数据 :  " + TextUtil.genHexStr(readBytes));
                onDataReceived(readBytes);
//                cmdCached.append(TextUtil.genHexStr(readBytes));
//                Log.i(TAG, "cmdCached = " + cmdCached.toString());
//
//                bytesCached.
//
//                if(cmdCached.toString().startsWith("2021") && cmdCached.toString().endsWith("2022")){
//                    onDataReceived(bytesCached);
//                }else {
//                    cmdCached +=
//                }
//

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    /**
     * 关闭线程 释放资源
     */
    public void release() {
        interrupt();

        if (null != mInputStream) {
            try {
                mInputStream.close();
                mInputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
