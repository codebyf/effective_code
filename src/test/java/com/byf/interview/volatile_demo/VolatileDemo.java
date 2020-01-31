package com.byf.interview.volatile_demo;

import java.util.concurrent.TimeUnit;

class MyData{
    volatile int number = 0;
    public void set60(){
        this.number = 60;
    }
}

public class VolatileDemo {


    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t come in...");
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
            myData.set60();
            System.out.println(Thread.currentThread().getName() + "\t come out..." + myData.number);
        }, "t1").start();
        while (myData.number == 0){
            // 直到myData.number不为0
        }
        System.out.println(Thread.currentThread().getName() + "\t game over...");
    }
}
