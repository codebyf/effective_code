package com.byf.interview.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：synchronized和Lock有什么区别？用新的Lock有什么好处？举例说明
 * 1 原始构成
 *      synchronized是关键字属于JVM层面
 *          monitorenter（底层通过monitor对象来完成，其实wait/notify等方法也依赖monitor对象只有在同步代码块中才能调用wait/notify等方法
 *          monitorexit
 *      Lock是具体类（java.util.concurrent.locks.Lock）是api层面的锁
 *
 * 2 使用方法
 *      synchronized 不需要手动释放锁，当synchronized代码执行完成后，系统会自动让线程释放对象锁的占用；
 *      ReentrantLock 需要用户手动释放锁，若没有主动释放锁，就有可能导致死锁；
 *
 * 3 等待是否可中断
 *      synchronized 不可中断，除非抛出异常或者正常运行完成
 *      ReentrantLock 可中断，1.设置超时方法tryLock(long timeout, TimeUnit unit)
 *                           2.lockInterruptily()代码块，调用interrupt()方法可中断
 *
 * 4 加锁是否公平
 *      synchronized 非公平锁
 *      ReentrantLock 两种都可以实现，默认非公平，构造方法可以传入boolean值，true为公平锁，false非公平锁
 *
 * 5 绑定多个条件Condition
 *      synchronized 没有
 *      ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒；而不是像synchronized要么随机唤醒一个，要么唤醒全部；
 *
 *
 * 题目：多线程之间顺序调用，实现A->B->C三个线程启动，要求实现：
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 * ...
 * 来10轮
 *
 */
class ShareResource
{
    private int number = 1; // A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            // 1 盘点
            while (number != 1){
                condition1.await();
            }
            // 2 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            // 1 盘点
            while (number != 2){
                condition2.await();
            }
            // 2 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            // 1 盘点
            while (number != 3){
                condition3.await();
            }
            // 2 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class SynchronizedAndLockTest {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}
