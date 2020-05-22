package juc;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 演示线程池
 */
public class ThreadPool {


    @Test
    public void test() {
        //固定线程数的线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //单线程的线程池
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //线程数量可扩展的的线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "正在办理业务");
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }


    @Test
    public void test2() {
        //一般不推荐使用Exectores创建线程池，因为其阻塞队列的参数和最大线程数默认是Integer.Max
        new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                new ThreadPoolExecutor.AbortPolicy());


        //能处理的最大线程数5+3；有四个拒绝策略，上面是默认的

    }


}
