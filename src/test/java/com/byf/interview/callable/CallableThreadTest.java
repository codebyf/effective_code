package com.byf.interview.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable{
    @Override
    public void run() {

    }
}

class MyThread1 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("******come in, callbale");
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) {e.printStackTrace();}
        return 1024;
    }
}

public class CallableThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread1());
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread1());

        Thread t1 = new Thread(futureTask, "t1");
        Thread t2 = new Thread(futureTask1, "t2");
        t1.start();
        t2.start();


        System.out.println(Thread.currentThread().getName() + "\t主线程计算中...");
        int result1 = 100;

        while (!futureTask.isDone()){

        }

        int result = futureTask.get();
        /*System.out.println("Callable 返回值：" + futureTask.get());*/
        System.out.println("*********** 汇总结果：" + (result + result1));
    }
}
