package com.byf.interview.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAFixOperation {

    private static AtomicReference<User> atomicReference = new AtomicReference<>();
    // private static Integer enjoy = new Integer(100);
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100,1);

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
        System.out.println("=========以下是ABA的问题解决==========");

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();

            System.out.println(Thread.currentThread().getName() + "\t第1次版本号：" + atomicStampedReference.getStamp());
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
            atomicStampedReference.compareAndSet(100,2019,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t第2次版本号：" + atomicStampedReference.getStamp());
            boolean result = atomicStampedReference.compareAndSet(2019,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t第3次版本号：" + atomicStampedReference.getStamp() + "\t" + result);
        }, "t3").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号：" + stamp);
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) {e.printStackTrace();}
            boolean result = atomicStampedReference.compareAndSet(100,101,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName() + "\t修改成功否：" + result + "\t当前最新版本号：" + atomicStampedReference.getStamp());
            System.out.println("当前值为：" +atomicStampedReference.getReference());

        }, "t4").start();

    }
}
