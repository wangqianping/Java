package io;

import java.io.*;

/**
 * 字节流用来处理图片，视频等非文本文件
 */
public class FileInputOutputStreamTest {

    public static void main(String[] args) {
        File file = new File("source/pizza.jpeg");
        File file2 = new File("source/pizzaCopy.jpeg");
        copy(file,file2);
    }

    public static void copy(File source,File target){

        if(!source.exists()){
            try {
                source.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!target.exists()){
            try {
                target.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(target);

            byte[] line = new byte[(int)source.length()];
            while(fileInputStream.read(line)!=-1){
                fileOutputStream.write(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }
}
