package juc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Mycache {

    private Map<String, String> map = new ConcurrentHashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void read(String key) {

        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始读取数据");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

    }


    public void write(String key, String value) {

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写入数据");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入数据完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }


}


public class ReadWriteLockDemo {

    public static void main(String[] args) {

        Mycache mycache = new Mycache();

        for (int i = 0; i <5 ; i++) {
            int fint = i;
            new Thread(()->{
                mycache.write(String.valueOf(fint), "1");
            }).start();
        }


        for (int i = 0; i <5 ; i++) {
            int fint = i;
            new Thread(()->{
                mycache.read(String.valueOf(fint));
            }).start();
        }


    }
}
