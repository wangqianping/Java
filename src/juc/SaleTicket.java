package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class Ticket {

    private int num = 300;
    private Lock lock = new ReentrantLock();

    public void sale() {

        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (num--) + "号票，还剩下" + num + "张票");
            }
        } finally {
            lock.unlock();
        }

    }

}


/**
 * 三个售票员卖30张票
 * <p>
 * 多线程企业编程模版
 * <p>
 * 高聚合低耦合 线程  操作(对外暴露的调用方法)   资源类
 */
public class SaleTicket {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 1; i < 100; i++) ticket.sale();
        },"A").start();

        new Thread(() -> {
            for (int i = 1; i < 100; i++) ticket.sale();
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i < 120; i++) ticket.sale();
        },"C").start();

    }


}
