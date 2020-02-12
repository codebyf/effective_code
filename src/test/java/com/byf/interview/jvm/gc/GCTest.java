package com.byf.interview.jvm.gc;

import java.util.Random;

/**
 * 新生代
 * 1 Serial + SerialOld (DefNew+Tenured)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
 *
 * 2 ParNew + SerialOld ：Deprecated (ParNew+Tenured)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC
 *
 * 3 ParallelScavenge + ParallelOld (PSYoungGen+ParOldGen)【吞吐量可控/自适应调节】-XX:MaxGCPauseMillis
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC / -XX:UseParallelOldGC 相互激活
 *
 * 4 老年代
 * 4.1 ParallelOld (PSYoungGen+ParOldGen)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:UseParallelOldGC
 *  默认不加就是 -XX:+UseParallelOldGC
 * 4.2
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 *
 * 5 CMS (par new generation + concurrent mark-sweep generation)
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
 *  四阶段：
 *      InitialMark -> ConcMark + PreClean -> FinalRemark -> ConcSweep
 *
 * 6 G1 (garbage-first heap)
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 *
 */
public class GCTest {
    public static void main(String[] args) {
        String str = "abcdefgh";
        try {
            while (true){
                str += str + new Random().nextInt(1111111111) + new Random().nextInt(2222222);
                str.intern();
            }
        } catch (Throwable e){
            e.printStackTrace();
        }

    }
}
