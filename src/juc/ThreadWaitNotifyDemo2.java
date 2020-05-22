package juc;

/**
 * 4个线程操作一个初始化为0的变量
 * 2个线程负责加1，2个线程负责减1
 * 实现交替来10轮，变量初始值为0
 * <p>
 * 多线程的存在虚假唤醒的情况
 * <p>
 * 所以判断不能用if,而是用while
 */
public class ThreadWaitNotifyDemo2 {

    public static void main(String[] args) {

        AirConditional2 airConditional = new AirConditional2();

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


class AirConditional2 {

    private int num = 0;


    public synchronized void increment() throws InterruptedException {
        //1。判断
        while (num != 0) {
            this.wait();
        }
        //2.干活
        num++;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        //3.通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //判断
        while (num == 0) {
            this.wait();
        }
        //干活
        num--;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        //通知
        this.notifyAll();

    }
}
