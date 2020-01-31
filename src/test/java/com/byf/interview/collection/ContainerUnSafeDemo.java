package com.byf.interview.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

public class ContainerUnSafeDemo {
    public static void main(String[] args) {
        // List<String> container = new ArrayList<>();   // UnSafe
        // List<String> container = new Vector<>();      // Safe
        // List<String> container = Collections.synchronizedList(new ArrayList<>()); // Safe
        // List<String> container = new CopyOnWriteArrayList<>(); // Safe
        //Set<String> container = new HashSet<>(); // UnSafe
        //Set<String> container = Collections.synchronizedSet(new HashSet<>()); // Safe
        //Set<String> container = new CopyOnWriteArraySet<>(); // Safe
        // Map<String, String> container = new HashMap<>(); // UnSafe
        // Map<String, String> container = Collections.synchronizedMap(new HashMap<>()); // Safe
        Map<String, String> container = new ConcurrentHashMap<>(); // Safe
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                container.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(container);
            },String.valueOf(i)).start();
        }

        /**
         * 1 故障现象
         *      java.util.ConcurrentModificationException
         *
         * 2 导致原因
         *      并发修改；
         *      共享区域；
         *
         *
         * 3 解决方案
         *      3.1 new Vector<>();
         *      3.2 Collections.synchronizedList(new ArrayList<>());
         *      3.3 new CopyOnWriteArrayList<>();
         *      3.4 Collections.synchronizedList(new HashSet<>());
         *      3.5 new CopyOnWriteArraySet<>();
         */
    }
}
