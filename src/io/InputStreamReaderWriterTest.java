package io;

import java.io.*;

/**
 * 转换流
 * InputStreamReader 字节输入流转成字符输入流
 * OutputStreamWriter 字节输出流转成字符输出流
 */
public class InputStreamReaderWriterTest {

    public static void main(String[] args) throws Exception {

        File source = new File("source/hello.txt");
        File target = new File("source/target.txt");

        if(!source.exists()){
            source.createNewFile();
        }

        if(!target.exists()){
            target.createNewFile();
        }


        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);

        InputStreamReader isr = new InputStreamReader(fis,"utf-8");
        OutputStreamWriter osr = new OutputStreamWriter(fos,"gbk");

        char[] text = new char[1024];
        int len;
        while((len=isr.read(text))!=-1){
            osr.write(text,0,len);
        }

        isr.close();
        osr.close();

    }

}