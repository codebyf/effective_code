package com.byf.interview.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * ·GC回收时间过长会抛出OutOfMemoryError，
 * 过长的定义是，超过98%的时间用来做GC并且回收了不到2%的堆内存，
 * 连续多次GC都 只回收了不到2%的极端情况才会抛出这种异常。
 * ·如果不抛出该异常会有什么问题？
 * 内存不足很快再次耗光，迫使再次GC，恶性循环，
 * CPU使用率一直100%，GC没有任何成功。
 */
public class GCOverheadLimitExceedTest {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e){
            System.out.println("*********i:" +i);
            e.printStackTrace();
            throw e;
        }
    }
}
