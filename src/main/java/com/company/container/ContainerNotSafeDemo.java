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
        list.add("1a");
        list.add("2b");
        list.add("3c");

        new Thread(()->{
            list.add("4d");
        }).start();

        for (String element : list) {
            System.out.println(element);
        }
    }
}
