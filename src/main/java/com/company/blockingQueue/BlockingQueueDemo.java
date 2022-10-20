package com.company.blockingQueue;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


class myThread {
    volatile AtomicInteger atomicInteger = new AtomicInteger(0);
    volatile int num = 0;
    volatile Map<String,String> map = new HashMap<>();
    volatile ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
    volatile List<String> arrayList = new ArrayList<>();
    volatile CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    public void add() {
        num++;
    }

    public void atmoicAdd(){
        atomicInteger.getAndIncrement();
    }

    public void put() {
        String str = UUID.randomUUID().toString();
        map.put(str,str);
    }

    public void concurrentPut(){
        String str = UUID.randomUUID().toString();
        concurrentHashMap.put(str,str);
    }

    public void listAdd(){
        String str = UUID.randomUUID().toString().substring(0,4);
        arrayList.add(str);
    }

    public void copyOnWriteList(){
        String str = UUID.randomUUID().toString().substring(0,4);
        copyOnWriteArrayList.add(str);
    }
}

/**
 * 阻塞队列
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        myThread myThread = new myThread();
        Vector<String> vector = new Vector<>();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myThread.add();
                    myThread.atmoicAdd();
                    myThread.put();
                    myThread.concurrentPut();
                    myThread.listAdd();
                    myThread.copyOnWriteList();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("普通int:"+myThread.num);
        System.out.println("原子int:"+myThread.atomicInteger);
        System.out.println("map:"+myThread.map.size());
        System.out.println("原子map:"+myThread.concurrentHashMap.size());
        System.out.println("list:"+myThread.arrayList.size());
        System.out.println("写时复制List:"+myThread.copyOnWriteArrayList.size());
    }
}
