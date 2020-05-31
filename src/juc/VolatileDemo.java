package juc;


import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Mydata{

    volatile int data = 0;

    public void add60(){
        this.data = 60;
    }


    public void add(){
        data++;
    }


    AtomicInteger atomicInteger = new AtomicInteger();

    public void addByatomic(){
        atomicInteger.getAndIncrement();
    }


}

public class VolatileDemo {



    @Test
    public void test1(){

        //演示volatile保证可见性

        Mydata mydata = new Mydata();

        new Thread(()->{

            System.out.println(Thread.currentThread().getName()+" come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                mydata.add60();
                System.out.println(Thread.currentThread().getName()+" value updated " + mydata.data );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


        while(mydata.data==0){

        }


        System.out.println(Thread.currentThread().getName()+"获取了data的可见性"+mydata.data);

    }


    //演示volatile的不可保证原子性
    @Test
    public void test2(){
        Mydata mydata = new Mydata();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    mydata.add();
                }
            }).start();
        }
        System.out.println("最后的结果 "+mydata.data);
    }

    //解决volatile的不保证原子性问题
    @Test
    public void test3(){

        Mydata mydata = new Mydata();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    mydata.addByatomic();
                }
            }).start();
        }

        System.out.println("最后的结果 "+mydata.atomicInteger);
    }



}




