package thread;

import org.junit.Test;

/**
 * 线程通信
 * <p>
 * notify()：一旦执行此方法就会唤醒一个wait的线程，如果当前有多个线程在wait,则唤醒优先级高的那个
 * notifyAll()：一旦执行此方法就会唤醒此时所有在wait的线程
 * wait() :一旦进入此方法，当前线程就会进入阻塞状态并释放同步监视器
 *
 * 注意：
 * 上述三个方法必须在同步代码块或同步方法中使用
 * 上述三个方法的调用者必须是同步代码块中的同步监视器(锁是谁就用谁调用，默认是this调用，所以锁必须是this)，否则会抛出异常
 * 上述三个方法是在Object方法中申明的
 *
 * sleep方法和wait方法的比较
 *
 *相同点： 两者都可以使得当前线程进入阻塞状态
 *不同点：1.申明的位置不同，前者在Thread后者在Object
 *       2.前者可以在任何位置用，后者只能在同步块或者同步方法中用
 *       3.前者不会主动释放锁或者会主动释放锁
 */
public class TestWaitAndNotify {

    @Test
    public void test() {
        NumConsoleThread numConsoleThread = new NumConsoleThread();
        Thread t1 = new Thread(numConsoleThread);
        Thread t2 = new Thread(numConsoleThread);
        t1.start();
        t2.start();
    }

}


//实现线程交替打印一到一百的数字
class NumConsoleThread implements Runnable {

    private int num = 0;

    @Override
    public void run() {

        while (true) {
            synchronized (this) {
                notify(); //唤醒其他锁
                if (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + " " + num);
                    num++;
                }
                try {
                    wait(); //进入阻塞状态并且会主动释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
