package io;

import java.io.File;
import java.io.IOException;

/**
 * 文件对象的使用
 */
public class FileTest {
    public static void main(String[] args) {
        //new出来的在内存
        File file = new File("hello.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(file.getAbsolutePath());
        //字符长度是怎么算的？utf-8 中文3个字节，GBK中文2个字节
        System.out.println(file.length());
        System.out.println(file.exists());
    }
}
