package com.uwjx.function.service;

import com.github.mjdev.libaums.UsbMassStorageDevice;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class UpgradeService {

    public void run(){

//        UsbMassStorageDevice[] devices = UsbMassStorageDevice.getMassStorageDevices(this /* Context or Activity */);
//
//        for(UsbMassStorageDevice device: devices) {
//
//            // before interacting with a device you need to call init()!
//            device.init();
//
//            // Only uses the first partition on the device
//            FileSystem currentFs = device.getPartitions().get(0).getFileSystem();
//            Log.d(TAG, "Capacity: " + currentFs.getCapacity());
//            Log.d(TAG, "Occupied Space: " + currentFs.getOccupiedSpace());
//            Log.d(TAG, "Free Space: " + currentFs.getFreeSpace());
//            Log.d(TAG, "Chunk size: " + currentFs.getChunkSize());
//        }
//        BufferedInputStream bufferedInputStream = null;
//        try {
//            bufferedInputStream = new BufferedInputStream(new FileInputStream(indexPath));
//            int buffer_size = 512;
//            byte[] buffer = new byte[buffer_size];
//            int len = 0;
//            int index = 0;
//            while (-1 != (len = bufferedInputStream.read(buffer , 0 , buffer_size))){
//                L.d("读取长度:" + len);
//                String binName = ((binIndex == 1) ? UpgradeHandler.BIN_1_PREFIX_NAME : UpgradeHandler.BIN_2_PREFIX_NAME) + index;
//                L.d("binName -> " + binName);
//                jedisClient.hSetBytes(UpgradeHandler.UPGRADE_TABLE_NAME , binName , buffer);
//                index++;
//            }
//
//            // 初始化总 bin 数
//            jedisClient.hSet(UpgradeHandler.UPGRADE_TABLE_NAME , (binIndex == 1) ? UpgradeHandler.BIN_1_SIZE : UpgradeHandler.BIN_2_SIZE , String.valueOf(index));
//            L.e("升级文件Bin 总数 ： " + index);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                bufferedInputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
