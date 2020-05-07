package io;

import java.io.*;

/**
 * 流的分类
 * 1.操作数据单位：字节流和字符流
 * 2.数据的流向：输入流和输出流
 * 3.流的角色：节点流和处理流
 *
 * 流的体系结构
 * 抽象类           节点流             缓冲流(高级流)
 * InputStream    FileInputStream   BufferInputStream
 * OutputStream   FileOutputStream  BufferOutputStream
 * Reader         FileReader        BufferReader
 * Writer         FileWriter        BufferWriter
 */
public class FileReadWriterTest {
    //字符流用来处理文本数据(.txt,.java,.c,.cpp)
    public static void main(String[] args) {
        File file = new File("source/hello.txt");
        File file2 = new File("source/source.txt");

        read(file);
        write(file);
        copy(file,file2);

    }


    public static void read(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            //方式一
//            while (fileReader.read()!=-1){
//                System.out.print(fileReader.read());
//            }
            //方式二
            int length;
            char[] cubf = new char[5];
            while ((length = fileReader.read(cubf)) != -1) {
                String str = new String(cubf, 0, length);
                System.out.print(str);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void write(File file) {

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write("\n文件IO流太简单了");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void copy(File source, File target) {

        if (!source.exists()) {
            try {
                source.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!target.exists()) {
            try {
                target.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(source);
            fileWriter = new FileWriter(target);
            char[] line = new char[5];
            int len;
            while ((len = fileReader.read(line)) != -1) {
                String str = new String(line, 0, len);
                fileWriter.write(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
