package thread;

import org.junit.Test;

/**
 * 多线程安全问题
 * ex:创建三个窗口进行买票
 * 会出现错票重票的情况 --> 此时我们认为出现了线程安全问题
 * 原因：当一个线程在操作车票的过程还未结束时，另一个线程就进来了并也操做车票，此时，对于车票来说就开始混乱了
 * 解决：我们希望一个线程在操作车票时，其他线程不能参与进来，直到操作车票的线程执行完毕以后，再让其他线程进来
 *
 * 在Java中，我们通过同步机制来解决线程安全的问题
 *
 * 方式一：同步代码块
 * synchronized(同步监视器/锁){
 *
 * }
 *
 * 说明：
 * 1。共享数据：多个线程共同操作的变量成为共享数据，如车票
 * 2。操作共享数据的代码，即为需要同步的代码
 * 3。同步监视器俗称锁，任何一个类的对象都可以充当锁，但是必须要求多线程共用同一把锁
 *
 *
 * 方式二：同步方法
 * 将操作共享数据的代码块封装成一个方法直接在定义的时候加上synchronized
 *
 * 同步方式很好的解决了线程安全的问题但是在效率上有一定的损耗
 *
 *
 */
public class TestSynchronized {

    @Test
    public void test1(){

        Window window = new Window();

        Thread t1 = new Thread(window);
        Thread t2 = new Thread(window);
        Thread t3 = new Thread(window);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }

    @Test
    public void test2(){
        Table table = new Table();

        Thread t1 = new Thread(table);
        Thread t2 = new Thread(table);
        Thread t3 = new Thread(table);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }

}


class Window implements Runnable {
    //总票数，也代表票号
    private int ticket = 100;
    private Object object = new Object();//充当锁

    @Override
    public void run() {

        while (true) {
            synchronized (object){
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " 卖出了 " + ticket + " 号票");
                    ticket--;
                } else {
                    break;
                }
            }
        }

    }
}

class Table implements Runnable{

    private int ticket = 100;

    @Override
    public void run() {
        while (ticket>0){
         sellTicket();
        }
    }

    private synchronized void sellTicket(){
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + " 卖出了 " + ticket + " 号票");
            ticket--;
        }
    }

}