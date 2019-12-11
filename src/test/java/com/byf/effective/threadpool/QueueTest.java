package com.byf.effective.threadpool;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueTest {
    /**
     * 基于数组的有界阻塞队列，队列容量10
     * @throws InterruptedException
     */
    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue<Integer>(10);
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("put : " + i);
        }
    }

    /**
     * 基于链表的有界 / 无界阻塞队列
     * @throws InterruptedException
     */
    @Test
    public void testLinkedBlockingQueue() throws InterruptedException {
        LinkedBlockingQueue queue = new LinkedBlockingQueue<Integer>();
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("put : " + i);
        }
    }

    /**
     * 同步移交阻塞队列，提供生成消费一对一的缓冲作用
     */
    @Test
    public void test() throws InterruptedException {
        SynchronousQueue queue = new SynchronousQueue<Integer>();
        new Thread(()->{
            try {
                queue.put(1);
                System.out.println("put : ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        /*new Thread(()->{
            try {
                queue.take();
                System.out.println("remove : ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/

        Thread.sleep(1000L * 20);
    }
}
