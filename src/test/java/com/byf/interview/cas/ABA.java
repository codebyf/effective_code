package com.byf.interview.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABA {
        private static AtomicInteger atomicInt = new AtomicInteger(100);
        private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(100, 0);
        private static AtomicStampedReference atomicStampedRef1 = new AtomicStampedReference(100, 0);

        public static void main(String[] args) throws InterruptedException {
                Thread intT1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                                atomicInt.compareAndSet(100, 101);
                                atomicInt.compareAndSet(101, 100);
                        }
                },"intT1");

                Thread intT2 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                                try {
                                        TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                }
                                boolean c3 = atomicInt.compareAndSet(100, 101);
                                System.out.println(Thread.currentThread().getName() + "\t" +c3); // true
                        }
                },"intT2");

                intT1.start();
                intT2.start();
                intT1.join();
                intT2.join();
                System.out.println("==============原子引用更新解决ABA问题=================");
                Thread refT1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                                try {
                                        TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                }
                                System.out.println(Thread.currentThread().getName() + "\t" +atomicStampedRef.compareAndSet(100, 2019, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1));
                                System.out.println(Thread.currentThread().getName() + "\t" +atomicStampedRef.compareAndSet(2019, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1));
                        }
                },"refT1");

                Thread refT2 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                                int stamp = atomicStampedRef.getStamp();
                                try {
                                        TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e) {
                                }
                                boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
                                System.out.println(Thread.currentThread().getName() + "\t" +c3); // false
                                System.out.println("当前值："+atomicStampedRef.getReference()+"\t当前版本："+atomicStampedRef.getStamp());
                        }
                },"refT2");

                refT1.start();
                refT2.start();
        }
}