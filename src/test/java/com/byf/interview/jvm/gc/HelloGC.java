package com.byf.interview.jvm.gc;

import java.util.concurrent.TimeUnit;

public class HelloGC {
    public static void main(String[] args) {
        System.out.println("hello GC.");
        try { TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); } catch (InterruptedException e) {e.printStackTrace();};
        /*long maxHeapSize = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println(maxHeapSize+"Byte\t"+maxHeapSize/(double)1024/1024+"MB");
        System.out.println(totalMemory+"Byte\t"+totalMemory/(double)1024/1024+"MB");
*/
        // -Xms10m -Xmx10m -XX:+PrintGCDetails -Xss128k -XX:+PrintCommandLineFlags
        // -XX:+PrintGCDetails -XX:+UseSerialGC -Xms10m -Xmx10m -XX:SurvivorRatio=4 -XX:NewRatio=1
//        byte[] bytes = new byte[50 * 1024 * 1024];

    }
}
