package com.company.Thread.MultiThread;

/**
 * 好的，这里是另一个题目：
 *
 * 有三个线程 A、B、C，每个线程循环打印10次自己的名字，要求按照 ABCABCABC 的顺序打印。
 *
 * 例如，正确的输出应该是：A B C A B C A B C A B C A B C A B C A B C A B C A B C。
 *
 * 你可以使用 Java 的多线程和锁机制来解决这个问题。
 */
public class ABCThread {

    public static ABCThread abcThread = new ABCThread();
    public static Integer index = 0;

    public static void main(String[] args) {
        new Thread(new AThread()).start();
        new Thread(new BThread()).start();
        new Thread(new CThread()).start();
    }

    public static class AThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (abcThread){
                    while (index != 0){
                        try {
                            abcThread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("A" + i);
                    index = (index + 1) % 3;
                    abcThread.notifyAll();
                }
            }
        }
    }

    public static class BThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (abcThread){
                    while (index != 1){
                        try {
                            abcThread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("B" + i);
                    index = (index + 1) % 3;
                    abcThread.notifyAll();
                }
            }
        }
    }

    public static class CThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (abcThread){
                    while (index != 2){
                        try {
                            abcThread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("C" + i);
                    index = (index + 1) % 3;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    abcThread.notifyAll();
                }
            }
        }
    }
}
