package juc;

/**
 * 两个线程操作一个初始化为0的变量
 * 一个线程负责加1，一个线程负责减1
 * 实现交替来10轮，变量初始值为0
 *
 * 多线程编程套路总结
 *
 * 1.高聚低合前提下，线程操作资源类
 * 2.消费者生产者模式(线程通信模式)下，判断/干活/通知
 * 3.多线程交互存在虚假唤醒的情况，判断不能用if而是用while
 * 4。标识位，用于解决多线程之间精准通信的问题
 *
 */
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) {

        AirConditional airConditional = new AirConditional();

        new Thread(()->{

            for (int i=0;i<10;i++) {
            try {
                airConditional.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        },"A").start();

        new Thread(()->{

            for (int i=0;i<10;i++) {
                try {
                    airConditional.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

    }
}


class AirConditional{

    private int num = 0;


    public synchronized void increment() throws InterruptedException {
        //1。判断
        if(num!=0){
            this.wait();
        }
        //2.干活
        num++;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        //3.通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //判断
        if(num==0){
            this.wait();
        }
        //干活
        num--;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        //通知
        this.notifyAll();

    }
}
