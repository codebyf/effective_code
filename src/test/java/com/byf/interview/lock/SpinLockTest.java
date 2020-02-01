package com.byf.interview.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockTest {
    AtomicReference<Thread> atomicReference = new AtomicReference<>(null);

    private void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in...");
        while (!atomicReference.compareAndSet(null, thread)){
            // 自旋
            System.out.print(".");
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {e.printStackTrace();}
        }
        System.out.println();
    }

    private void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println("\n" + Thread.currentThread().getName() + "\t come out...");
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        SpinLockTest lock = new SpinLockTest();
        new Thread(()->{
            lock.myLock();
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) {e.printStackTrace();}
            lock.myUnLock();
        }, "t1").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            lock.myLock();
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
            lock.myUnLock();
        }, "t2").start();
    }
}
