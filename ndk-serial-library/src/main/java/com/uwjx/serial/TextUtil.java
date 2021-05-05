package com.uwjx.serial;

public class TextUtil {
    public static String genHexStr(byte[] cmd){
        String h = "";
        for (int i = 0; i < cmd.length; i++) {
            String temp = Integer.toHexString(cmd[i] & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            h = h + " " + temp;
        }
        return h;
    }
}
