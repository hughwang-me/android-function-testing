package com.uwjx.function.probe;

import com.uwjx.function.util.CRCUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseProbeCmd {

    public byte   head =   (byte)0x68;//帧头
    public byte code;//指令码
    public byte  data;//数据
    public byte [] crc = new byte[2];//CRC校验
    public byte   end =  (byte)0x16 ;//帧尾

    public byte headlevel=(byte)0x68;//探針


    public abstract byte[] getSendCmd();

    protected byte[] getCmd() {
        List<Byte> list = new ArrayList<>();
        List<Byte> crc_field = new ArrayList<>();
        list.add(head); //指令头
        list.add(code); //指令码
        list.add(data);//数据

        crc_field.add(head);
        crc_field.add(code);
        crc_field.add(data);

        crc = CRCUtils.getCrcByte(getBytes(crc_field));
        list.add(crc[1]);
        list.add(crc[0]);
        list.add(end);
        String s = "";
        for (byte b: list) {
            s = s + b + "";
        }
//        Log.w("hugh" , "s list : " + s);
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
