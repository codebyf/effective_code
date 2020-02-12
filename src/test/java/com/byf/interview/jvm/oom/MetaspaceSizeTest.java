package com.byf.interview.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * JVM参数：
 *      -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 * java8 Metaspace：并不在虚拟机，而是在本地内存，class metadata叫做Metaspace的native memory
 *
 * 元空间存放以下信息：
 * 1.虚拟机加载的类信息
 * 2.常量池
 * 3.静态变量
 * 4.即时编译后的代码
 *
 * 模拟Metaspace空间溢出，不断生成类，类占据的空间会超过Metaspace指定的大小
 */
public class MetaspaceSizeTest {
    static class OOMTest{}
    public static void main(String[] args) {
        int i =0 ;
        try {
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e){
            System.out.println("******* 创建了i : " + i + "个加强类");
            e.printStackTrace();
        }
    }
}
