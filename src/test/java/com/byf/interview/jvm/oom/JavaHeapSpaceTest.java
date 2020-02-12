package com.byf.interview.jvm.oom;

import java.util.Random;

public class JavaHeapSpaceTest {
    public static void main(String[] args) {
        String str = "abcdefgh";
        /*while (true){
            str += str + new Random().nextInt(1111111111) + new Random().nextInt(2222222);
            str.intern();
        }*/
        byte[] bytes = new byte[50 * 1024 * 1024];
    }
}
