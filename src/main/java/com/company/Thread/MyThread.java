package com.company.Thread;

public class MyThread{

    public static Integer count = 0;

    public class Thread1 extends Thread{
        @Override
        public synchronized void start() {
            incrCount();
        }
    }

    public class Thread2 extends Thread{
        @Override
        public synchronized void start() {
            incrStaticCount();
        }
    }

    // 生成当前时间

    public void incrCount(){
        System.out.println("no static");
        for (int i = 0; i < 1000000; i++) {
            count++;
        }
    }

    public static void incrStaticCount(){
        System.out.println("static");
        for (int i = 0; i < 1000000; i++) {
            count++;
        }
    }
    public static void main(String[] args) throws InterruptedException {
    }
}
