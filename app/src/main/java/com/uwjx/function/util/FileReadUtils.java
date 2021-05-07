package com.uwjx.function.util;

import java.io.File;
import java.io.FileInputStream;

public class FileReadUtils {

    /**
     * 以字节流读取文件
     * @param path  文件路径
     * @return 字节数组
     */
    public static byte[] getByteStream(String path){
        // 拿到文件
        File file = new File(path);
        return getByteStream(file);
    }

    /**
     * 以字节流读取文件
     * @param file  文件
     * @return 字节数组
     */
    public static byte[] getByteStream(File file){
        try{
            // 拿到输入流
            FileInputStream input = new FileInputStream(file);
            // 建立存储器
            byte[] buf =new byte[input.available()];
            // 读取到存储器
            input.read(buf);
            // 关闭输入流
            input.close();
            // 返回数据
            return buf;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
