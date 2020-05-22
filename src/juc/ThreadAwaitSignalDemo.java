package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadAwaitSignalDemo {


    public static void main(String[] args) {
        AirConditional3 airConditional = new AirConditional3();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    airConditional.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    airConditional.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    airConditional.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    airConditional.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }


}

class AirConditional3{

    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public  void increment() throws InterruptedException {

        lock.lock();
        try {
            //1。判断
            while(num!=0){
                condition.await();//this.wait();
            }
            //2.干活
            num++;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            //3.通知
            condition.signalAll();//this.notifyAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            lock.unlock();
        }



    }

    public  void decrement() throws InterruptedException {
        lock.lock();
        try {
            //1。判断
            while(num==0){
                condition.await();//this.wait();
            }
            //2.干活
            num--;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            //3.通知
            condition.signalAll();//this.notifyAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            lock.unlock();
        }
    }
}
