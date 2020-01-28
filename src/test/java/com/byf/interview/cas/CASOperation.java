package com.byf.interview.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASOperation {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t 当前值：" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 100) + "\t 当前值：" + atomicInteger.get());
    }
}
