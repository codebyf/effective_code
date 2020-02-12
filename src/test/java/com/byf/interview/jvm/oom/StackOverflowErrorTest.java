package com.byf.interview.jvm.oom;

public class StackOverflowErrorTest {
    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
}
