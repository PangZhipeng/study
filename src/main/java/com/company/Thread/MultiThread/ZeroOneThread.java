package com.company.Thread.MultiThread;

/**
 * 现在有 4 个线程，其中两个线程每次加 1，另外两个线程每次减 1，设计一个程序，使得这四个线程交替执行，并且初始值为 0，最后输出结果应该为 0。
 * 要求：
 * 使用 Java 多线程实现。
 * 使用 wait 和 notify 实现线程同步。
 */
public class ZeroOneThread {
    public static ZeroOneThread zeroOneThread = new ZeroOneThread();
    public static Integer number = 0;
    public static Integer index = 0;

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
        new Thread(new Thread3()).start();
        new Thread(new Thread4()).start();

    }

    public static class Thread1 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (zeroOneThread){
                    while (index != 0){
                        try {
                            zeroOneThread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("第" + i + "次输出====================================");
                    number ++;
                    System.out.println(number);
                    index = (index + 1) % 4;
                    zeroOneThread.notifyAll();
                }
            }
        }
    }

    public static class Thread2 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (zeroOneThread){
                    while (index != 1){
                        try {
                            zeroOneThread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    number --;
                    System.out.println(number);
                    index = (index + 1) % 4;
                    zeroOneThread.notifyAll();
                }
            }
        }
    }

    public static class Thread3 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (zeroOneThread){
                    while (index != 2){
                        try {
                            zeroOneThread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    number ++;
                    System.out.println(number);
                    index = (index + 1) % 4;
                    zeroOneThread.notifyAll();
                }
            }
        }
    }

    public static class Thread4 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (zeroOneThread){
                    while (index != 3){
                        try {
                            zeroOneThread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    number --;
                    System.out.println(number);
                    index = (index + 1) % 4;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    zeroOneThread.notifyAll();
                }
            }
        }
    }
}
