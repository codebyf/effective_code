package com.byf.interview.cas;

import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicReferenceOperation {
    private static AtomicReference<User> atomicReference = new AtomicReference<>();
    static User bb = new User("bb", 14);
    private static AtomicStampedReference<User> atomicReference1 = new AtomicStampedReference<User>(bb,1);


    public static void main(String[] args) {
        User zhangsan = new User("ZS", 14);
        User lisi = new User("LS", 15);
        /*atomicReference.set(zhangsan);

        System.out.println(atomicReference.compareAndSet(zhangsan, lisi) + "\t 修改后的值：" + atomicReference.get());
        */
        new Thread(()->{
            int stamp = atomicReference1.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t当前版本号："+stamp);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(atomicReference1.compareAndSet(bb, zhangsan, stamp, ++stamp));
            System.out.println(Thread.currentThread().getName() + "\t第1次修改版本号："+atomicReference1.getStamp());
            System.out.println(atomicReference1.compareAndSet(zhangsan, bb, atomicReference1.getStamp(), atomicReference1.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "\t第2次修改版本号："+atomicReference1.getStamp());
            System.out.println(atomicReference1.getReference());

        }, "t1").start();

        new Thread(()->{
            int stamp = atomicReference1.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t当前版本号："+stamp);
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(atomicReference1.compareAndSet(bb, lisi, stamp, ++stamp));
            System.out.println(atomicReference1.getReference());
        }, "t2").start();
    }


}
@Data
class User{
    private String name;
    private Integer age;

    public User(String name, Integer age){
        this.name = name;
        this.age = age;
    }
}
