package com.company.Thread.MultiThread;

public class Express {
    private static final int DISTANCE = 500; // 快递距离目的地的距离
    private static int currentLocation = 0; // 当前快递位置
    private static boolean arrive = false; // 快递是否到达目的地

    public static void main(String[] args) throws InterruptedException {
        // 启动生产者线程
        for (int i = 0; i < 3; i++) {
            new ExpressProducer().start();
        }

        // 启动消费者线程
        for (int i = 0; i < 3; i++) {
            new ExpressConsumer().start();
        }
    }

    /**
     * 生产者线程
     */
    public static class ExpressProducer extends Thread {
        @Override
        public void run() {
            while (!arrive) {
                synchronized (Express.class) {
                    forward();
                    Express.class.notifyAll();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者线程
     */
    public static class ExpressConsumer extends Thread {
        @Override
        public void run() {
            while (!arrive) {
                synchronized (Express.class) {
                    if (currentLocation >= DISTANCE) {
                        System.out.println("快递已到达目的地");
                        arrive = true;
                        return;
                    } else {
                        try {
                            Express.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 快递向前移动一定距离
     */
    public static void forward() {
        currentLocation += 100;
        System.out.println("快递已到达" + currentLocation + "米");
    }
}
