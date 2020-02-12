package com.byf.interview.jvm.gcroots;

/**
 * 在java中可作为GC ROOTs的对象有：
 * （1）虚拟机栈（栈帧中的局部变量区，也叫局部变量表）中引用的对象。
 * （2）方法区中的类静态属性引用的对象；
 * （3）方法区中常量引用的对象；
 * （4）本地方法栈JNI（Native方法）引用的对象；
 */
public class GCRootsTest {
    private byte[] byteArray = new byte[100 * 1024 * 1024];

    private static GCRootsTest gcRoot1;
    private static final GCRootsTest gcRoot2 = new GCRootsTest();

    public static void m1(){
        GCRootsTest gcRoot3 = new GCRootsTest();
        new Thread(()->{
            GCRootsTest gcRoot4 = new GCRootsTest();
        }, "t1").start();
    }
}
