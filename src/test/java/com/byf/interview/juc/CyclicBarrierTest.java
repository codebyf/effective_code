package com.byf.interview.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class CyclicBarrierTest {


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("************召唤神龙");
        });

        for (int i = 1; i <= 7; i++) {
            final int tmpi = i;
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + "\t收集到:" + tmpi+ "龙珠");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }

}
