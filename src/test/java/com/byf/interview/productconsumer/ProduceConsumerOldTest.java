package com.byf.interview.productconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：一个初始值为0的变量，两个线程交替操作，一个加1一个减1，来5轮
 * 1 线程     操作      资源类
 * 2 判断     干活      通知
 * 3 防止虚假唤醒
 */
class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 判断 if有可能虚假唤醒
            while (number != 0){
                // 等待，不能执行
                condition.await();
            }

            // 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            // 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            // 判断 if有可能虚假唤醒
            while (number == 0){
                // 等待，不能执行
                condition.await();
            }

            // 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            // 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ProduceConsumerOldTest {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();


        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t3").start();



        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t4").start();

    }
}
