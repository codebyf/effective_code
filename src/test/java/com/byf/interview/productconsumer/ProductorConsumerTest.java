package com.byf.interview.productconsumer;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile/CAS/AtomicInteger/BlockingQueue/线程交互/原子引用
 */

class ShareResource{
    private volatile boolean FLAG = true; //默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public ShareResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        // 日志排查传递的具体类型
        System.out.println(blockingQueue.getClass().getName());
    }

    public void produce() throws Exception{
        String data = null;
        boolean result = false;
        while (FLAG){
            data = atomicInteger.getAndIncrement() + "";
            result = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if(result){
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"成功");
            } else {
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"失败");
            }
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
        }
        System.out.println(Thread.currentThread().getName()+"\t大老板叫停，表示FLAG=false，生产动作结束");
    }

    public void consume() throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if(result == null || result.equalsIgnoreCase("")){
                FLAG =false;
                System.out.println(Thread.currentThread().getName() + "\t超过2秒没有取到，消费退出");
                System.out.println();
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t\t消费队列"+result+"成功");
        }
    }

    public void stop(){
        this.FLAG = false;
    }

}
public class ProductorConsumerTest {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource(new LinkedBlockingDeque<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t生产线程启动");
            System.out.println();
            try {
                shareResource.produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Productor").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t消费者线程启动");
            System.out.println();
            try {
                shareResource.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Comsumer").start();

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) {e.printStackTrace();}
        System.out.println();
        System.out.println();
        System.out.println("5秒时间到，大老板叫停");
        shareResource.stop();
    }
}
