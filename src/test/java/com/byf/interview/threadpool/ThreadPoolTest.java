package com.byf.interview.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 第四种获得/使用java多线程的方式：线程池
 *  1 newFixedThreadPool：执行长期的任务
 *  2 newSingleThreadExecutor：一个任务一个任务的执行
 *  3 newCachedThreadPool：适合执行很多短期的任务
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
         ExecutorService threadPool = Executors.newFixedThreadPool(5);   //一个线程池5个处理线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();   //一个线程池1个处理线程
        // ExecutorService threadPool = Executors.newCachedThreadPool();   //一个线程池N个处理线程，处理不过来新开线程，否则复用

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try{
            for (int i = 1; i <= 20; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
