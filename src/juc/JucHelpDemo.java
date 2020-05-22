package juc;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 该类用来演示juc的一些常用辅助类
 */
public class JucHelpDemo {


    @Test
    public void test1() throws InterruptedException {
        //减少计数，某些线程必须在一些线程都执行完时才执行(人走光了锁门)

        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i <5 ; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+" 离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println("班长锁门走人");
    }

    @Test
    public void test2(){
        //增加计数，某些线程必须在一些线程都执行完时才执行(人到齐了再开会)

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            System.out.println("5人到齐了就开会");
        });

        for (int i = 1; i <=5 ; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+" 来了");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }

    @Test
    public void test3() {
        //控制多线程的并发数（6个线程抢3个资源）
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i <10 ; i++) {
            new Thread(() ->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到了车位");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //TODO 测试不通过
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }







}
