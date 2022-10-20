package com.company.container;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerNotSafeDemo {
    /**
     * ArrayList 线程不安全的解决方案
     *  1: Vector   add方法加了synchronized
     *  2:  Collections.synchronizedList
     *  3: CopyOnWriteArrayList
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();
        }
    }
}
