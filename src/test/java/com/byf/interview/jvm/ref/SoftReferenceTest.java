package com.byf.interview.jvm.ref;

import java.lang.ref.SoftReference;

public class SoftReferenceTest {
    public static void main(String[] args) {
        // memoryEnough();
        notMemoryEnough();
    }

    // -Xms5m -Xmx5m -XX:+PrintGCDetails
    private static void notMemoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Throwable e){
            e.printStackTrace();
        } finally {
            System.out.println();
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    private static void memoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println();
        System.out.println(o1);
        System.out.println(softReference.get());
    }
}
