package com.company.Thread.MultiThread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDown {

    public static CountDownLatch countDownLatch = new CountDownLatch(3);
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Player(), "玩家" + i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有玩家都已经准备");
    }

    public static class Player implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(2 * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + "已经准备");
                countDownLatch.countDown();
            }
        }
    }
}
