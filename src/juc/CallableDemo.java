package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask = new FutureTask(new HisThread());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

    }
}

class HisThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <50 ; i++) {
            sum+=i;
        }
        return sum;
    }
}