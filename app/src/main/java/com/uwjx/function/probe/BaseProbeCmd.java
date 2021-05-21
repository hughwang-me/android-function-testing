package com.uwjx.function.probe;

import com.uwjx.function.util.CRCUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseProbeCmd {

    public byte   head =   (byte)0x68;//帧头
    public byte code;//指令码

    public byte[] upgradeDataLength;//数据长度
    public byte[] upgradeAddressOffset;//地址偏移
    public byte[] upgradeData;//升级数据

    public byte[] crcCheckData = new byte[2];//升级的CRC校验

    public byte  data;//数据
    public byte [] crc = new byte[2];//CRC校验
    public byte   end =  (byte)0x16 ;//帧尾



    public abstract byte[] getSendCmd();

    protected byte[] getCmd() {
        List<Byte> list = new ArrayList<>();
        List<Byte> crc_field = new ArrayList<>();
        list.add(head); //指令头
        list.add(code); //指令码
        list.add(data);//数据

        crc_field.add(code);
        crc_field.add(data);

        crc = CRCUtils.getCrcByte(getBytes(crc_field));
        list.add(crc[0]);
        list.add(crc[1]);
        list.add(end);
        return getBytes(list);
    }

    protected byte[] getQueryCmd() {
        List<Byte> list = new ArrayList<>();
        List<Byte> crc_field = new ArrayList<>();
        list.add(head); //指令头
        list.add(code); //指令码

        crc_field.add(code);

        crc = CRCUtils.getCrcByte(getBytes(crc_field));
        list.add(crc[0]);
        list.add(crc[1]);
        list.add(end);
        String s = "";
        for (byte b: list) {
            s = s + b + "";
        }
        return getBytes(list);
    }

    protected byte[] getUpgradeFileCheckCmd() {
        List<Byte> list = new ArrayList<>();
        List<Byte> crc_field = new ArrayList<>();
        list.add(head); //指令头
        list.add(code); //指令码
        list.add(crcCheckData[0]);//数据
        list.add(crcCheckData[1]);//数据

        crc_field.add(code); //指令码
        crc_field.add(crcCheckData[0]);//数据
        crc_field.add(crcCheckData[1]);//数据

        crc = CRCUtils.getCrcByte(getBytes(crc_field));
        list.add(crc[0]);
        list.add(crc[1]);
        list.add(end);
        String s = "";
        for (byte b: list) {
            s = s + b + "";
        }
        return getBytes(list);
    }

    protected byte[] getPreUpgradeCmd() {
        List<Byte> list = new ArrayList<>();
        List<Byte> crc_field = new ArrayList<>();
        list.add(head); //指令头
        list.add(code); //指令码
        list.add(upgradeDataLength[0]);//数据
        list.add(upgradeDataLength[1]);//数据

        crc_field.add(code); //指令码
        crc_field.add(upgradeDataLength[0]);//数据
        crc_field.add(upgradeDataLength[1]);//数据

        crc = CRCUtils.getCrcByte(getBytes(crc_field));
        list.add(crc[0]);
        list.add(crc[1]);
        list.add(end);
        String s = "";
        for (byte b: list) {
            s = s + b + "";
        }
        return getBytes(list);
    }

    protected byte[] getUpgradeCmd() {
        List<Byte> list = new ArrayList<>();
        List<Byte> crc_field = new ArrayList<>();
        list.add(head); //指令头
        list.add(code); //指令码
        list.add(upgradeDataLength[0]);//数据
        list.add(upgradeDataLength[1]);//数据
        list.add(upgradeAddressOffset[0]);//数据
        list.add(upgradeAddressOffset[1]);//数据

        for (byte upgradeDatum : upgradeData) {
            list.add(upgradeDatum);
        }

        crc_field.add(code); //指令码
        crc_field.add(upgradeDataLength[0]);//数据
        crc_field.add(upgradeDataLength[1]);//数据
        crc_field.add(upgradeAddressOffset[0]);//数据
        crc_field.add(upgradeAddressOffset[1]);//数据

        for (byte upgradeDatum : upgradeData) {
            crc_field.add(upgradeDatum);
        }

        crc = CRCUtils.getCrcByte(getBytes(crc_field));
        list.add(crc[0]);
        list.add(crc[1]);
        list.add(end);
        String s = "";
        for (byte b: list) {
            s = s + b + "";
        }
        return getBytes(list);
    }

    private byte[] getBytes(List<Byte> list) {
        byte [] bs = new byte[list.size()];
        for (int i = 0;i < list.size();i++) {
            bs[i] = list.get(i);
        }
        return bs;
    }
}
