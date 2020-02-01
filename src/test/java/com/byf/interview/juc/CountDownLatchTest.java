package com.byf.interview.juc;

import org.omg.CORBA.TCKind;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        /*for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 离开教室.");
                latch.countDown();
            },String.valueOf(i)).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName() + " 班长最后离开教室，关灯.");*/

        for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t国被灭.");
                latch.countDown();
            },CountryEnum.iteratorEnums(i).getCountryMessage()).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName() + "秦帝国一统天下.");
        System.out.println();
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getCountryCode());
        System.out.println(CountryEnum.ONE.getCountryMessage());
    }
}
