package com.byf.interview.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue：数组结构的有界阻塞队列，FIFO排序；
 * LinkedBlockingQueue：链表结构的阻塞队列，FIFO排序，吞吐量高于ArrayBlockingQueue；
 * SynchronousQueue：一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则阻塞，吞吐量通常高于LinkedBlockingQueue；
 *
 * 1 队列
 *
 * 2 阻塞队列
 *  2.1 阻塞队列有没有好的一面；
 *
 *  2.2 不得不阻塞，如何管理；
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        /*BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println("队首元素：" + blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/
        /*BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/
        /*BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        try {
            blockingQueue.put("a");
            blockingQueue.put("b");
            blockingQueue.put("c");
            System.out.println("========================");
//            blockingQueue.put("x");
            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();
            System.out.println("*************************");
            blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
    }
}
