package com.byf.effective.threadpool;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池对比
 */
public class ThreadVs {

    /**
     * 新的处理方式
     */
    @Test
    public void newHandler() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0;i<100;i++){
            threadPool.execute(()->{
                System.out.println("Start");
                try{
                    Thread.sleep(1000L*30);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("End");
            });
        }

        Thread.sleep(1000L * 1000);

    }

    /**
     * 老的处理方式
     */
    @Test
    public void oldHandler() throws InterruptedException {
        /*for (int i=0;i<100;i++){
            new Thread(()->{
                System.out.println("Start");
                try{
                    Thread.sleep(1000L*30);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("End");
            }).start();
        }
        Thread.sleep(1000L * 1000);*/
    }
}
