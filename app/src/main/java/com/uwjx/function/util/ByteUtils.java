package com.uwjx.function.util;


import java.text.DecimalFormat;

public class ByteUtils {

    private static final String TAG = "ByteUtils";

    public static void printBytes(byte[] b) {
        for(byte n: b) {
            System.out.print(n + " ");
        }
    }

    private static String hexStr =  "0123456789ABCDEF";
    private static String[] binaryArray =
            {"0000","0001","0010","0011",
                    "0100","0101","0110","0111",
                    "1000","1001","1010","1011",
                    "1100","1101","1110","1111"};


    /**
     * 字节数组转short
     * @param bytes 需要转换的字节数组
     * @return short
     */
    public static short byteArrToShort(byte[] bytes){
        int FF = 0xff;
        int first = (bytes[0] & FF) << 8;
        short result = (short) (first | (bytes[1] & FF));
        return result;
        //可以直接return (short) ((bytes[0] << 8) | bytes[1]);
        //之所以向上面那么写，是为了方便看的清楚
    }

    /**
     * short转字节数组
     * @param value 待转换的short
     * @return 字节数组
     */
    public static byte[] shortToByteArr(short value){
        byte highByte = (byte) (value >> 8);//获取高位的字节
        byte lowByte = (byte) value;//获取低位的字节
        byte[] result = new byte[2];
        result[0] = highByte;
        result[1] = lowByte;
        return result;
    }

    /**
     * 字节数组转int
     * @param bytes 待转换的字节数组
     * @return 转换之后的int
     */
    public static int byteArrToInt(byte[] bytes){
        int FF = 0xff;
        //假设字节数组 05 0A 07 04
        int h1 = (bytes[0] & FF) << 24;//左移24位之后，h1为05 00 00 00
        int h2 = (bytes[1] & FF) << 16;//左移16位之后，h2为00 0A 00 00
        int h3 = (bytes[2] & FF) << 8;//左移8位之后，h3为00 00 07 00
        int h4 = bytes[3] & FF;//h4为00 00 00 04
        int result = h1 | h2 | h3 | h4;
        return result;
    }

    /**
     * int转字节数组
     * @param value 待转换的int
     * @return 转换之后的字节数组
     */
    public static byte[] intToByteArr(int value){
        //假设原来的int的16进制为 05 0A 07 04
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (value >> 24);//右移24位之后，返回值00 00 00 05,强转之后变成05
        bytes[1] = (byte) (value >> 16);//右移16位之后，返回值00 00 05 0A,强转之后变成0A
        bytes[2] = (byte) (value >> 8);//右移8位之后，返回值00 05 0A 07,强转之后变成07
        bytes[3] = (byte) value;
        return bytes;
    }

    /**
     * 把字节数组转换成long
     * 这个的处理手法和前面和int和short都有一点差异
     * @param bytes 待转换的字节数组
     * @return 转换之后的long
     */
    public static long byteArrToLong(byte[] bytes){
        long FF = 0xff;
        //假设字节数组为08 0A 01 03 05 07 02 0B
        long b0 = bytes[0] & FF;//00 00 00 00 00 00 00 08
        long h0 = b0 << 56;//08 00 00 00 00 00 00 00
        long b1 = bytes[1] & FF;//00 00 00 00 00 00 00 0A
        long h1 = b1 << 48;//00 0A 00 00 00 00 00 00
        long b2 = bytes[2] & FF;
        long b3 = bytes[3] & FF;
        long b4 = bytes[4] & FF;
        long b5 = bytes[5] & FF;
        long b6 = bytes[6] & FF;
        long b7 = bytes[7] & FF;
        long h2 = b2 << 40;
        long h3 = b3 << 32;
        long h4 = b4 << 24;
        long h5 = b5 << 16;
        long h6 = b6 << 8;
        long h7 = b7;
        return h0 | h1 | h2 | h3 | h4 | h5 | h6 | h7;
    }

    /**
     * long转换成字节数组
     * @param value 待转换的long
     * @return 转换之后的字节数组
     */
    public static byte[] longToByteArr(long value){
        //假设待转换的long为08 0A 01 03 05 07 02 0B
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (value >> 56);//右移之后,00 00 00 00 00 00 00 08,强转之后08
        bytes[1] = (byte) (value >> 48);//右移之后,00 00 00 00 00 00 08 0A,强转之后0A
        bytes[2] = (byte) (value >> 40);//右移之后,00 00 00 00 00 08 0A 01,强转之后01
        bytes[3] = (byte) (value >> 32);
        bytes[4] = (byte) (value >> 24);
        bytes[5] = (byte) (value >> 16);
        bytes[6] = (byte) (value >> 8);
        bytes[7] = (byte) value;
        return bytes;
    }

    /**
     * 将字节数组转换成float
     * @param bytes 待转换的字节数组
     * @return 转换之后的float
     */
    public static float byteArrToFloat(byte[] bytes){
        int FF = 0xff;
        //假设字节数组 05 0A 07 04
        int b0 = bytes[0] & FF;// 00 00 00 05
        int b1 = bytes[1] & FF;// 00 00 00 0A
        int b2 = bytes[2] & FF;
        int b3 = bytes[3] & FF;
        int h0 = b0 << 24;// 05 00 00 00
        int h1 = b1 << 16;// 00 0A 00 00
        int h2 = b2 << 8;
        int h3 = b3;
        int h = h0 | h1 | h2 | h3;
        return Float.intBitsToFloat(h);
    }


    /**
     * float转字节数组
     * @param value 待转换的float
     * @return 转换之后的字节数组
     */
    public static byte[] floatToByteArr(float value){
        int i = Float.floatToIntBits(value);
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (i >> 24);
        bytes[1] = (byte) (i >> 16);
        bytes[2] = (byte) (i >> 8);
        bytes[3] = (byte) (i);
        return bytes;
    }

    /**
     * 将字节数组转换成double
     * @param bytes 待转换的字节数组
     * @return 转换出来的double
     */
    public static double byteArrToDouble(byte[] bytes){
        //假设待转换的字节数组为05 0A 07 04 0B 00 03 01
        long FF  = 0xFF;
        long b0 = bytes[0] & FF;// 00 00 00 00 00 00 00 05
        long b1 = bytes[1] & FF;// 00 00 00 00 00 00 00 0A
        long b2 = bytes[2] & FF;
        long b3 = bytes[3] & FF;
        long b4 = bytes[4] & FF;
        long b5 = bytes[5] & FF;
        long b6 = bytes[6] & FF;
        long b7 = bytes[7] & FF;
        long h0 = b0 << 56;// 05 00 00 00 00 00 00 00
        long h1 = b1 << 48;// 00 0A 00 00 00 00 00 00
        long h2 = b2 << 40;
        long h3 = b3 << 32;
        long h4 = b4 << 24;
        long h5 = b5 << 16;
        long h6 = b6 << 8;
        long h7 = b7;
        long h = h0 | h1 | h2 | h3 | h4 | h5 | h6 | h7;
        return Double.longBitsToDouble(h);
    }

    /**
     * 将字节数组转换成double
     * @param bytes 待转换的字节数组
     * @return 转换出来的double
     */
    public static double byteArrToDouble(byte[] bytes, int decimal){
        //假设待转换的字节数组为05 0A 07 04 0B 00 03 01
        long FF = 0xFF;
        long b0 = 0x00, b1 = 0x00, b2 = 0x00, b3 = 0x00, b4 = 0x00, b5 = 0x00, b6 = 0x00, b7 = 0x00;
        long h0 = 0x00, h1 = 0x00, h2 = 0x00, h3 = 0x00, h4 = 0x00, h5 = 0x00, h6 = 0x00, h7 = 0x00;
        long h = 0x00;
        if (1 <= decimal) {
            b0 = bytes[0] & FF;
            h0 = b0 << 56;
        }
        if (2 <= decimal) {
            b1 = bytes[1] & FF;
            h1 = b1 << 48;
        }
        if (3 <= decimal) {
            b2 = bytes[2] & FF;
            h2 = b2 << 40;
        }
        if (4 <= decimal) {
            b3 = bytes[3] & FF;
            h3 = b3 << 32;
        }
        if (5 <= decimal) {
            b4 = bytes[4] & FF;
            h4 = b4 << 24;
        }
        if (6 <= decimal) {
            b5 = bytes[5] & FF;
            h5 = b5 << 16;
        }
        if (7 <= decimal) {
            b6 = bytes[6] & FF;
            h6 = b6 << 8;
        }
        if (8 <= decimal) {
            b7 = bytes[7] & FF;
            h7 = b7;
        }
        switch (decimal) {
            case 1:
                h = h0;
                break;
            case 2:
                h = h0 | h1;
                break;
            case 3:
                h = h0 | h1 | h2;
                break;
            case 4:
                h = h0 | h1 | h2 | h3;
                break;
            case 5:
                h = h0 | h1 | h2 | h3 | h4;
                break;
            case 6:
                h = h0 | h1 | h2 | h3 | h4 | h5;
                break;
            case 7:
                h = h0 | h1 | h2 | h3 | h4 | h5 | h6;
                break;
            case 8:
                h = h0 | h1 | h2 | h3 | h4 | h5 | h6 | h7;
                break;
        }
        return Double.longBitsToDouble(h);
    }

    /**
     * 将double转换成字节数组
     * @param value 待转换的double
     * @return 转换之后的字节数组
     */
    public static byte[] doubleToByteArr(double value){
        long lbits = Double.doubleToLongBits(value);
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (lbits >> 56);
        bytes[1] = (byte) (lbits >> 48);
        bytes[2] = (byte) (lbits >> 40);
        bytes[3] = (byte) (lbits >> 32);
        bytes[4] = (byte) (lbits >> 24);
        bytes[5] = (byte) (lbits >> 16);
        bytes[6] = (byte) (lbits >> 8);
        bytes[7] = (byte) (lbits);
        return bytes;
    }

    /**
     * 将double转换成字节数组
     * @param value 待转换的double
     * @return 转换之后的字节数组
     */
    public static byte[] doubleToByteArr(double value, int decimal){
        long lbits = Double.doubleToLongBits(value);
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (lbits >> 56);
        bytes[1] = (byte) (lbits >> 48);
        bytes[2] = (byte) (lbits >> 40);
        bytes[3] = (byte) (lbits >> 32);
        bytes[4] = (byte) (lbits >> 24);
        bytes[5] = (byte) (lbits >> 16);
        bytes[6] = (byte) (lbits >> 8);
        bytes[7] = (byte) (lbits);
        byte[] bs = new byte[decimal];
        for(int i = 0;i < decimal;i++) {
            bs[i] = bytes[i];
        }
        return bs;
    }


    /**
     * 将字节数组转换成十六进制字符串
     * @param bytes 待转换的字节数组
     * @return 转换之后的十六进制字符串
     */
    public static String bytesToHexStr(byte[] bytes) {

        String result = "";
        String hex = "";
        int F0 = 0xF0;
        int ZeroF = 0x0F;
        for (int i = 0; i < bytes.length; i++) {
            //字节高4位
            hex = String.valueOf("0123456789ABCDEF".charAt((bytes[i] & F0) >> 4));
            //字节低4位
            hex += String.valueOf("0123456789ABCDEF".charAt(bytes[i] & ZeroF));
            result += hex;
        }
        return result;
    }

    //生成指定位数的byte数组,注意：str应为十进制数的字符形式
//    public static byte[] toBytes(String str, int decimal) {
//        double d = Double.valueOf(str);
//        return ByteUtils.doubleToByteArr(d,decimal);
//    }

    //将double的数据转换为特殊的byte数组
    public static byte[] toBytes(String str, int decimal) {
        byte [] bytes = new byte[decimal];
        try {
            double d = Double.valueOf(str) * 100;//10000.0
            String s = new DecimalFormat("0").format(d);//10000
            int len = s.length();//5
            while (len < decimal) {
                s = "0" + s;
                len ++;
            }//s: 00010000
            for (int i = 0;i < decimal; i++) {
                char c = s.charAt(i);
                bytes[i] = (byte) c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    //解析来自串口的数据
    public static double str2Double(String value) {
        double dData = 0;
        try {
            //30 30 30 31 30 30 30 30
            int len = value.length() / 2;
            int [] intArg = new int[len];
            int index = 0;
            for (int i = 0; i < value.length(); i = i + 2) {
                String str = value.substring(i, i+2);
                intArg[index] = Integer.parseInt(str);
                index++;
            }
            //intArg=[30, 30, 30, 31, 30, 30, 30, 30]
            String s = "";
            for (int i: intArg) {
                s += (i - 30);
            }
            //s: 00010000
            int data = Integer.parseInt(s);//10000
            dData = (double) data / 100;//100.00
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dData;
    }

    public static String stringToHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

        /**
         * 将16进制转换成二进制字节数组,注意16进制不要输入0x，只需输入ff，不要输入成0xff
         * @param hexString 带转换的十六进制字符串
         * @return 转换之后的字节数组
         */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    public static byte[] intToByteArray(int i) {

        byte[] result = new byte[4];

        result[0] = (byte)((i >> 24) & 0xFF);

        result[1] = (byte)((i >> 16) & 0xFF);

        result[2] = (byte)((i >> 8) & 0xFF);

        result[3] = (byte)(i & 0xFF);

        return result;

    }

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }



    /**
     * 二进制数组转换为二进制字符串   2-2
     */
    public static String bytesToBinStr(byte[] bArray) {
        String outStr = "";
        int pos = 0;
        for (byte b : bArray) {
            //高四位
            pos = (b & 0xF0) >> 4;
            outStr += binaryArray[pos];
            //低四位
            pos = b & 0x0F;
            outStr += binaryArray[pos];
        }
        return outStr;
    }

    /**
     * 将十六进制字符串转换为二进制字符串
     */
    public static String hexStr2BinStr(String hexString){
        return bytesToBinStr(hexStringToBytes(hexString));
    }

    public static double strBytesToDouble(String bytesStr) {
        bytesStr = bytesStr.toUpperCase();
        int length = bytesStr.length() / 2;
        char[] hexChars = bytesStr.toCharArray();
        byte[] d = new byte[length];
        int index = 0;
        for (int i = 0; i < length; i += 2) {
            String s = String.valueOf(hexChars[i]) + hexChars[i + 1];
            byte b = Byte.valueOf(s);
            d[index] = b;
            index++;
        }
        return byteArrToDouble(d, length);
    }

//    public static byte[] hexStringToBytes(String hexString) {
//        if (hexString == null || hexString.equals("")) {
//            return null;
//        }
//        hexString = hexString.toUpperCase();
//        int length = hexString.length() / 2;
//        char[] hexChars = hexString.toCharArray();
//        byte[] d = new byte[length];
//        for (int i = 0; i < length; i++) {
//            int pos = i * 2;
//            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
//        }
//        return d;
//    }

    /**
     * 截取指定的字节数组
     * @param src
     * @param begin
     * @param count
     * @return
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }



    /**
     * int转byte[],高位低位，两个字节
     * @param a
     * @return
     */
    public static byte[] intTo2Bytes(int a) {
        return new byte[] {
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }


    /**
     * @param data1
     * @param data2
     * @return data1 与 data2拼接的结果
     */
    public static byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length+data2.length];
        System.arraycopy(data1,0,data3,0,data1.length);
        System.arraycopy(data2,0,data3,data1.length,data2.length);
        return data3;
    }


    /**
     *从byte[]中抽取新的byte[]
     * @param data - 元数据
     * @param start - 开始位置
     * @param len - 长度
     * @return 新byte[]
     */
    public static byte[] getByteArr(byte[]data,int start ,int len){
        //首先判断数据长度是否OK
        if(data.length < start || data.length < start + len){
            return new byte[]{0x00};
        }
        int end = start + len;
        byte[] ret=new byte[end-start];
        for(int i=0;(start+i)<end;i++){
            ret[i]=data[start+i];
        }
        return ret;
    }


    /**
     * 安全获取字节数组中的某个字节
     */
    public static byte getbyte(byte[] data,int position){
        if(data.length >= position + 1){
            return data[position];
        }else {
            return -1;
        }
    }

    /**
     * int转byte[],4个字节
     * @param data
     * @return
     */
    public static byte[] intTo4Bytes(int data) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((data & 0xff000000) >> 24);
        bytes[1] = (byte) ((data & 0xff0000) >> 16);
        bytes[2] = (byte) ((data & 0xff00) >> 8);
        bytes[3] = (byte) (data & 0xff);
        return bytes;
    }

    public static String genHexStr(byte[] cmd){
        String h = "";
        for (int i = 0; i < cmd.length; i++) {
            String temp = Integer.toHexString(cmd[i] & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            h = h + "" + temp;
        }
        return h;
    }
}