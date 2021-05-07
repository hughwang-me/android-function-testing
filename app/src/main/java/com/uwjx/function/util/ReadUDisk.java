package com.uwjx.function.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.github.mjdev.libaums.UsbMassStorageDevice;
import com.github.mjdev.libaums.fs.FileSystem;
import com.github.mjdev.libaums.fs.UsbFile;
import com.github.mjdev.libaums.partition.Partition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReadUDisk {

//    private UDiskCallBack.OnUDiskCallBack mOnUDiskCallBack = null;
//    private Context mContext;
//    private UsbMassStorageDevice[] storageDevices;
//    private List<UsbFile> usbFiles = new ArrayList<>();
//    private final UsbManager mUsbManager;
//
//    public ReadUDisk(Context context) {
//        mContext = context;
//        mUsbManager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
//    }
//
//    /**
//     * 接受U盘插入和拔出事件
//     * @param onUDiskCallBack
//     */
//    public void setOnUDiskCallBack(UDiskCallBack.OnUDiskCallBack onUDiskCallBack) {
//        if (mOnUDiskCallBack == null) {
//            registerReceiver();
//            mOnUDiskCallBack = onUDiskCallBack;
//        }
//    }
//
//    /**
//     * 注册广播接收者
//     */
//    public void registerReceiver() {
//        //监听otg插入 拔出
//        IntentFilter usbDeviceStateFilter = new IntentFilter();
//        usbDeviceStateFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
//        usbDeviceStateFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
//        mContext.registerReceiver(UDiskMountedReceiver, usbDeviceStateFilter);
//        //注册监听自定义广播
//        IntentFilter filter = new IntentFilter(Constant.ACTION_USB_PERMISSION);
//        mContext.registerReceiver(UDiskMountedReceiver, filter);
//        Log.e("ReadUDisk", "registerReceiver: ");
//    }
//
//    /**
//     * 注销广播接收者
//     */
//    public void unReisterReceiver() {
//        if (UDiskMountedReceiver != null) {
//            mContext.unregisterReceiver(UDiskMountedReceiver);
//        }
//    }
//
//    /**
//     * 检查usb设备的权限
//     * @param device
//     * @return
//     */
//    public boolean checkPerssion(UsbMassStorageDevice device) {
//        if (mUsbManager==null){
//            return false;
//        }
//        if (mUsbManager.hasPermission(device.getUsbDevice())) {//有就直接读取设备是否有权限
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//
//    /**
//     * 读取当前usb设备的数量
//     * @return
//     */
//    public int getDeviceCount() {
//        //获取存储设备
//        UsbMassStorageDevice[] storageDevices =UsbMassStorageDevice.getMassStorageDevices(mContext);
//        return storageDevices.length;
//    }
//
//    /**
//     * 根据position获取usb设备
//     * @param position
//     * @return
//     */
//    public UsbMassStorageDevice getUsbMassDevice(int position) {
//        //获取存储设备
//        UsbMassStorageDevice[] storageDevices =UsbMassStorageDevice.getMassStorageDevices(mContext);
//        if (position > storageDevices.length) {
//            return null;
//        } else {
//            return storageDevices[position];
//        }
//    }
//
//    /**
//     * 获取usb上所有的存储设备
//     * @return
//     */
//    public UsbMassStorageDevice[] getUsbMassAllDevice() {
//        //获取存储设备
//        UsbMassStorageDevice[] storageDevices =UsbMassStorageDevice.getMassStorageDevices(mContext);
//        return storageDevices;
//    }
//
//    /**
//     * 根据设备获取路径
//     * @param device
//     * @return
//     */
//    public FileSystem readDevice(UsbMassStorageDevice device) {
//        try {
//            if (!checkPerssion(device)){  //检查是否有权限
//                return null;
//            }
//
//            device.init();//使用设备之前需要进行 初始化
//            Partition partition = device.getPartitions().get(0); //仅使用设备的第一个分区
//            FileSystem currentFs = partition.getFileSystem();
//            // currentFs.getCapacity(); //容量大小
//            // currentFs.getOccupiedSpace(); //已使用大小
//            // currentFs.getFreeSpace();  //未使用的大小
//            UsbFile root       = currentFs.getRootDirectory();//获取根目录
//            String  deviceName = currentFs.getVolumeLabel();//获取设备标签
//            return currentFs;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 获取U盘的文件和文件夹路径
//     * @param fileSystem
//     * @return
//     */
//    public List<UsbFile> getUsbFiles(FileSystem fileSystem) {
//        usbFiles.clear();
//        try {
//            for (UsbFile file : fileSystem.getRootDirectory()
//                    .listFiles()) {  //将所以文件和文件夹路径添加到usbFiles数组中
//                usbFiles.add(file);
//            }
//            Collections.sort(usbFiles, new Comparator<UsbFile>() {//简单排序 文件夹在前 文件在后
//                @Override
//                public int compare(UsbFile oFile1, UsbFile oFile2) {
//                    return oFile1.isDirectory()
//                            ? -1
//                            : 1;
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return usbFiles;
//    }
//
//
//    private BroadcastReceiver UDiskMountedReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            switch (action) {
//                case ACTION_USB_PERMISSION: //自定义权限广播
//                    if (mOnUDiskCallBack != null) {
//                        mOnUDiskCallBack.onPermissionCallBack();
//                    }
//                    break;
//                case UsbManager.ACTION_USB_DEVICE_ATTACHED: //usb设备插入广播
//                    if (mOnUDiskCallBack != null) {
//                        mOnUDiskCallBack.onAttachDeviceCallBack();
//                    }
//                    break;
//
//                case UsbManager.ACTION_USB_DEVICE_DETACHED: //usb设备拔出广播
//                    if (mOnUDiskCallBack != null) {
//                        mOnUDiskCallBack.onDetachDeviceCallBack();
//                    }
//                    break;
//            }
//        }
//    };
}
