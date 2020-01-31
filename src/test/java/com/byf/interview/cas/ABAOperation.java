package com.byf.interview.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ABAOperation {
    public static void main(String[] args) {
        new Thread(()->{
            AtomicInteger atomicInteger = new AtomicInteger(5);
            System.out.println(Thread.currentThread().getName() + "期望从5改成2019, " +atomicInteger.compareAndSet(5, 2019) + "\t 当前值：" + atomicInteger.get());
            System.out.println(Thread.currentThread().getName() + "从2019再改回去5, " +atomicInteger.compareAndSet(2019, 5) + "\t 当前值：" + atomicInteger.get());
        }, "t1").start();

        new Thread(()->{
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
            AtomicInteger atomicInteger = new AtomicInteger(5);
            System.out.println(Thread.currentThread().getName() + "期望从5改成100, " +atomicInteger.compareAndSet(5, 100) + "\t 当前值：" + atomicInteger.get());
        }, "t2").start();

        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) {e.printStackTrace();}
        new Thread(()->{

        }, "t1").start();
    }
}
