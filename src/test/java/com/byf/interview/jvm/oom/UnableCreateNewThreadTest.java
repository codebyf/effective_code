package com.byf.interview.jvm.oom;

import java.util.concurrent.TimeUnit;

/**
 * 故障现象：
 *      高并发请求服务器时，经常出现：
 *          java.lang.OutOfMemoryError: unable to create new native thread
 *      native thread 与 OS平台有关
 * 导致原因：
 *      1.创建线程太多，超过系统承载限制；
 *      2.Linux默认1024，超过则报：
 *          java.lang.OutOfMemoryError: unable to create new native thread
 *
 * 解决办法：
 *      1.降低可创建线程数，分析应用是否需要这么多线程；
 *      2.确实需要，配置Linux服务器，扩大默认限制；
 */
public class UnableCreateNewThreadTest {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("********** i = " + i);
            new Thread(()->{
                try { TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); } catch (InterruptedException e) {e.printStackTrace();}
            }, "t1").start();

        }
    }
}
