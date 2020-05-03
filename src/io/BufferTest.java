package io;

import java.io.*;

/**
 * 缓冲流读写速度更快
 * 缓冲流就是套接在字节流基础之上的流
 */
public class BufferTest {

    public static void main(String[] args) {
        File file = new File("source/pizza.jpeg");
        File file2 = new File("source/pizzaPlus.jpeg");
        copyJpg(file,file2);

        File source = new File("source/hello.txt");
        File target = new File("source/target.txt");
        copyFile(source,target);
    }


    public static void copyJpg(File source, File target){

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
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(target);
            bis = new BufferedInputStream(fileInputStream);
            bos = new BufferedOutputStream(fileOutputStream);
            byte[] line = new byte[(int)source.length()];
            while(bis.read(line)!=-1){
                bos.write(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关流只需关外层流
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    public static void copyFile(File source, File target){
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

        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            fileReader = new FileReader(source);
            fileWriter = new FileWriter(target);
            br = new BufferedReader(fileReader);
            bw = new BufferedWriter(fileWriter);
            String str;
            while((str=br.readLine())!=null){
                bw.write(str);
                bw.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关流只需关外层流
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
