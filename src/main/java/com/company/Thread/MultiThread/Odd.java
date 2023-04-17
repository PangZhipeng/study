package com.company.Thread.MultiThread;

/**
 * 两个线程交替输出奇偶数（要求用wait和notify实现）
 */
public class Odd {
    public static Odd oddObj = new Odd();

    public static Boolean odd = true;
    public static Integer number = 0;
    public static Integer count = 0;


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(new OddThread()).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new EvenThread()).start();
        }
        Thread.sleep(10000);
    }

    public static class OddThread implements Runnable{
        @Override
        public void run() {
            while (count < 100){
                synchronized (oddObj){
                    while (!odd) {
                        try {
                            oddObj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    number ++;
                    System.out.println("奇数: "+ number);
                    odd = false;
                    count ++;
                    oddObj.notifyAll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class EvenThread implements Runnable{
        @Override
        public void run() {
            while (count < 100){
                synchronized (oddObj){
                    while (odd) {
                        try {
                            oddObj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    number ++;
                    System.out.println("偶数: "+ number);
                    count++;
                    odd = true;
                    oddObj.notifyAll();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
