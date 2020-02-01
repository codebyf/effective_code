package com.byf.interview.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static ReentrantLock lock = new ReentrantLock(false);
    public static void main(String[] args) {
        sendSMS();
        new Thread(()->{
            sendSMS();
        }, "t1").start();
        new Thread(()->{
            sendSMS();
        }, "t2").start();
    }

    private static void sendSMS(){
        lock.lock();
        try {
            System.out.println("sendSMS...");
            sendEmail();
        } finally {
            lock.unlock();
        }
    }

    private static void sendEmail(){
        lock.lock();
        lock.lock();
        try {
            System.out.println("#####sendEmail...");
        } finally {
            lock.unlock();
            // lock.lock();
        }
    }

    /*public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }*/
}
