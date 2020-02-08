package com.byf.interview.deadlock;

import java.util.concurrent.TimeUnit;

class HoldLockThreaad implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThreaad(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t 持有锁：" + lockA + "\t等待获取锁：" +lockB);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 持有锁：" + lockB + "\t等待获取锁：" +lockA);
            }

        }
    }
}
/**
 * 死锁是指两个或两个以上的进程在执行过程中，
 * 因争夺资源而造成的一种互相等待的现象，
 * 若无外力干涉那么他们将无法继续推进下去
 */
public class DeadLockTest {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThreaad(lockA,lockB), "threadA").start();
        new Thread(new HoldLockThreaad(lockB,lockA), "threadB").start();
    }
}
