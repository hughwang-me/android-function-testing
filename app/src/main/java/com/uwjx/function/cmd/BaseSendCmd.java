package com.uwjx.function.cmd;



import com.uwjx.function.util.CRCUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSendCmd {

    public byte [] head = {(byte)0x20,(byte)0x21};//帧头
    public byte dir = (byte)0x30;//方向
    public byte addr;//目标地址
    public byte code;//指令码
    public byte [] data;//数据
    public byte [] crc = new byte[2];//CRC校验
    public byte [] end = {(byte)0x20,(byte)0x22};//帧尾

    public byte headlevel=(byte)0x68;//探針


    public abstract byte[] getSendCmd();

/*   /**
     * @param data = (address + validData + ？)
     * @return
     *//*
    protected byte[] getSendCmd(String data) {
        String cmd = frameHeader + direction + data;
        byte[] cmdBytes = Conversion.hexStringToByteArray(cmd);
        dataVerification = CRCUtils.getCrc(cmdBytes);
        cmd += dataVerification + frameEnd;
        return Conversion.hexStringToByteArray(cmd);
    }*/

    protected byte[] getThisCmd() {
        List<Byte> list = new ArrayList<>();
        List<Byte> crc_field = new ArrayList<>();
        list.add(head[0]);
        list.add(head[1]);
        list.add(dir);
        list.add(addr);
        list.add(code);

//        Log.w("hugh" , "生成 list : " + list.toString());

        crc_field.add(dir);
        crc_field.add(addr);
        crc_field.add(code);

        crc = CRCUtils.getCrcByte(getBytes(crc_field));
        list.add(crc[1]);
        list.add(crc[0]);
        list.add(end[0]);
        list.add(end[1]);
        String s = "";
        for (byte b: list) {
            s = s + b + "";
        }
//        Log.w("hugh" , "s list : " + s);
        return getBytes(list);
    }

    protected byte[] getLevelCmd() {
        List<Byte> list = new ArrayList<>();
        List<Byte> crc_field = new ArrayList<>();
        list.add(headlevel);
        list.add(dir);
        list.add(addr);
        list.add(code);

        crc_field.add(dir);
        crc_field.add(addr);
        crc_field.add(code);

        crc = CRCUtils.getCrcByte(getBytes(crc_field));
        list.add(crc[1]);
        list.add(crc[0]);
        list.add((byte)0x16);
        String s = "";
        for (byte b: list) {
            s = s + b + "";
        }
        return getBytes(list);
    }

    protected byte[] getThisCmd(byte[] data) {
        List<Byte> list = new ArrayList<>();
        List<Byte> crc_field = new ArrayList<>();
        list.add(head[0]);
        list.add(head[1]);
        list.add(dir);
        list.add(addr);
        list.add(code);

        crc_field.add(dir);
        crc_field.add(addr);
        crc_field.add(code);

        for (byte b: data) {
            list.add(b);
            crc_field.add(b);
        }
        crc = CRCUtils.getCrcByte(getBytes(crc_field));
        list.add(crc[1]);
        list.add(crc[0]);
        list.add(end[0]);
        list.add(end[1]);
        String s = "";
        for (byte b: list) {
            s = s + b + "";
        }
        return getBytes(list);
    }

    protected byte[] getThisCmd(byte data) {
        List<Byte> list = new ArrayList<>();
        List<Byte> crc_field = new ArrayList<>();

        list.add(head[0]);
        list.add(head[1]);
        list.add(dir);
        list.add(addr);
        list.add(code);
        list.add(data);

        crc_field.add(dir);
        crc_field.add(addr);
        crc_field.add(code);
        crc_field.add(data);

        crc = CRCUtils.getCrcByte(getBytes(crc_field));
        list.add(crc[1]);
        list.add(crc[0]);
        list.add(end[0]);
        list.add(end[1]);
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
//        Log.w("hugh" , "s list getBytes : " + bs);
        return bs;
    }
}
