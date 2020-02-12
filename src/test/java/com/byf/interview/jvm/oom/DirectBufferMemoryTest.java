package com.byf.interview.jvm.oom;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * JVM参数：
 *      -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 故障现象：
 *      java.lang.OutOfMemoryError: Direct buffer memory
 * 导致原因：
 *      写NIO程序经常使用ByteBuffer来取或者写入数据，这是一种基于通道（Channel）与缓冲区（Buffer）的I/O方式
 *      它可以使用Native函数直接分配堆外内存，然后通过Java堆DirectByteBuffer对象为引用操作。
 *      这样能在一些场景中显著提高性能，因为避免了在Java堆和Native堆中来回拷贝
 *
 *  ByteBuffer.allocate(capability) 分配JVM堆内存，属于GC管辖范围，需要用户(Java堆)/内核(Native堆)之间拷贝，速度相对较慢
 *
 *  ByteBufffer.allocateDirect(capability) 分配OS本地内存，不属于GC管辖，由于不需要内存拷贝，速度相对较快
 *
 *  但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC，
 */
public class DirectBufferMemoryTest {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory："+(sun.misc.VM.maxDirectMemory())/(double)1024/1024 + "MB.");
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
