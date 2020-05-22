package juc;

import com.sun.media.jfxmedia.locator.ConnectionHolder;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList,LinkedList 线程不安去哪
 * HashSet HashMap 线程不安全
 *
 * 安全的集合
 *
 * Connections.SychronizedSet()
 * Connections.SychronizedList()
 * Connections.SychronizedMap()
 *
 * 操作线程不安全的集合会出错
 * java.util.ConcurrentModificationException
 *
 */
public class SafeList {


    @Test
    public void test1(){
        List<String> arrayList = new ArrayList<>();
        List<String> strings = Collections.synchronizedList(arrayList);
        for(int i=1;i<30;i++){
            new Thread(()->{
                strings.add(UUID.randomUUID().toString().substring(1,5));
                System.out.println(strings);
            },String.valueOf(i)).start();
        }
    }

    @Test
    public void test2(){
        List<String> arrayList = new CopyOnWriteArrayList<>();
        for(int i=1;i<30;i++){
            new Thread(()->{
                arrayList.add(UUID.randomUUID().toString().substring(1,5));
                System.out.println(arrayList);
            },String.valueOf(i)).start();
        }
    }

    @Test
    public void test3(){
        Map<String,Integer> stringMap = new ConcurrentHashMap();
        for(int i=1;i<30;i++){
            int finalI = i;
            new Thread(()->{
                stringMap.put(UUID.randomUUID().toString().substring(1,5), finalI);
                System.out.println(stringMap);
            },String.valueOf(i)).start();
        }
    }



}
