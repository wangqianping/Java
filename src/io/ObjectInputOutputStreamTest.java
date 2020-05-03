package io;

import java.io.*;

/**
 * 对象流
 * ObjectInputStream
 * ObjectOutputStream
 *
 */
public class ObjectInputOutputStreamTest {

    public static void main(String[] args) throws Exception {
        File file = new File("source/object.txt");
        testObjectOutputStream(file);
        testObjectinputStream(file);
    }

    public static void testObjectOutputStream(File file) throws Exception {
        if(!file.exists()){
            file.createNewFile();
        }
        Person person = new Person(1,"张三",25);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(person);
        oos.close();
    }

    public static void testObjectinputStream(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Person person = (Person) ois.readObject();
        System.out.println(person);
    }



}
