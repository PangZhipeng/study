package com.company.Thread;

public class SyncDemo {

    public SyncDemo syncDemo = this;

    public static void main(String[] args) {
        new Thread(()->{
            try {
                staticMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                staticMethod2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                staticMethod3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static synchronized void staticMethod() throws InterruptedException {
        System.out.println("staticMethod");
        Thread.sleep(1000);
        System.out.println("staticMethod end");
    }

    public static synchronized void staticMethod2() throws InterruptedException {
        System.out.println("staticMethod2222222222222222");
        Thread.sleep(1000);
        System.out.println("staticMethod2222222222222222      end");
    }

    public static void staticMethod3() throws InterruptedException {
        synchronized (SyncDemo.class){
            System.out.println("staticMethod33333333");
            Thread.sleep(1000);
            System.out.println("staticMethod33333333333      end");
        }
    }

    public void staticMethod4() throws InterruptedException {
        synchronized (this){
            System.out.println("staticMethod4444444");
            Thread.sleep(1000);
            System.out.println("staticMethod4444444      end");
        }
    }
}
