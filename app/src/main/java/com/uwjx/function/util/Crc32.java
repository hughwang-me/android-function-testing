package com.uwjx.function.util;

import java.util.zip.CRC32;

public class Crc32 {

    //hexString Crc32校验
    public static String getCrc32HexString(String hex) {
        CRC32 crc32 = new CRC32();
//        crc32.update(Conversion.hexStringToByteArray(hex));
        return formatCrc(Long.toHexString(crc32.getValue()));
    }

    //byte[] Crc32校验
    public static String getCrc32HexString(byte[] b) {
        CRC32 crc32 = new CRC32();
        crc32.update(b);
        return formatCrc(Long.toHexString(crc32.getValue()));
    }

    //补全八位
    private static String formatCrc(String hex) {
        char[] chars = hex.toCharArray();
        String crcHex = hex;
        for(int i=0;i<8-chars.length;i++) {
            crcHex = "0"+ crcHex;
        }
        return crcHex;
    }
}