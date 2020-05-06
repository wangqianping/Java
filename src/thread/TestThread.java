package thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 两种线程方式的创建对比
 * 继承不仅增加了耦合度同时该类也无法继承其他类了，单继承原则
 * 继承的方式无法共享数据，但实现的方式可以
 */
class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i % 20 == 0) {
                yield();
            }
        }

    }
}

class YourThread implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        }

    }
}

class HisThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <50 ; i++) {
               sum+=i;
        }
        return sum;
    }
}

public class TestThread {

    @Test
    public void test1() throws InterruptedException {

        MyThread t1 = new MyThread();

        t1.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i % 15 == 0) {
                t1.join(); //谁join谁就先执行完
            }
        }

        System.out.println(t1.isAlive());

    }



    @Test
    public void test2(){

        YourThread thread = new YourThread();

        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        Thread t3 = new Thread(thread);

        t1.setName("线程1");
        t2.setName("线程2");
        t3.setName("线程3");

        t1.start();
        t2.start();
        t3.start();

    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {

        HisThread hisThread = new HisThread();
        FutureTask futureTask = new FutureTask(hisThread);
        Thread thread1 = new Thread(futureTask);
        thread1.start();

        //获取返回值,这个根据实际需求看需不需要获取，线程的主要步骤在上面
        Object o = futureTask.get();
        System.out.println(o);

    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        //通过线程池的方式创建线程
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(new YourThread());
        Future<Integer> future = service.submit(new HisThread());
        System.out.println("结果："+future.get());
        service.shutdown();
    }

}
