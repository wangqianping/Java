package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示多线程之间的精准通信 实现A->B->C
 *
 * 3个线程之间启动如下
 *
 * AA打印5次，BB打印10次，CC打印15次
 *
 * 来10轮
 *
 */
public class ThreadOrderAccess {

    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();

        new Thread(() ->{
            for (int i = 0; i <10 ; i++) {
                sharedResource.printA("AA", 5);
            }
        },"A").start();

        new Thread(() ->{
            for (int i = 0; i <10 ; i++) {
                sharedResource.printB("BB", 10);
            }
        },"B").start();

        new Thread(() ->{
            for (int i = 0; i <10 ; i++) {
                sharedResource.printC("CC", 15);
            }
        },"C").start();
    }
}

//资源类
class SharedResource{

    //标识位
    private int num = 1;//1代表A线程，2代表B线程，3代表C线程
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    public void printA(String str,int count){

        lock.lock();
        try{

                //1.判断
                while (num!=1){
                    condition1.await();
                }
                //2.干活
                for(int i=1;i<=count;i++){
                    System.out.println(Thread.currentThread().getName()+"\t"+str+i);
                }
                //3.通知
                num = 2;
                condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB(String str,int count){

        lock.lock();
        try{
            //1.判断
            while (num!=2){
                condition2.await();
            }
            //2.干活
            for(int i=1;i<=count;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+str+i);
            }
            //3.通知
            num = 3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printC(String str,int count){

        lock.lock();
        try{
            //1.判断
            while (num!=3){
                condition3.await();
            }
            //2.干活
            for(int i=1;i<=count;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+str+i);
            }
            //3.通知
            num = 1;
            condition1.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}
