package com.company.threadTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class myData {
    volatile int num = 0;
    volatile AtomicInteger atomicInteger = new AtomicInteger();


    public void numTo60() {
        this.num = 60;
    }

    public void numPlus() {
        this.num++;
    }

    public void atomicNumPlus(){
        this.atomicInteger.getAndIncrement();
    }
}


public class Volatile {
    public static void main(String[] args) throws Exception{

        atmoic();

    }

    /**
     * volatile 不保证原子性验证
     */
    public static void atmoic(){
        myData myData = new myData();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 2000; j++) {
                    myData.numPlus();
                    myData.atomicNumPlus();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() >2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"的num是:"+myData.num);
        System.out.println(Thread.currentThread().getName()+"的atomicInteger是:"+myData.atomicInteger);
    }


    public static void see(){
        myData myData = new myData();

        /**
         * volatile 的可见性验证
         */
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }
            myData.numTo60();
            System.out.println(Thread.currentThread().getName()+"的num是:"+myData.num);
        },"aa").start();

        while (myData.num == 0){

        }

        System.out.println(Thread.currentThread().getName()+"的num是:"+myData.num);
    }
}
