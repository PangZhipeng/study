package com.company.Thread.MultiThread;

import java.util.concurrent.Semaphore;

/**
 * 使用 Semaphore 的多线程题目：
 *
 * 假设有一家餐馆，只有 2 张桌子，
 * 有 5 个人要来吃饭。当有空桌时，就会有人入座吃饭，否则就需要等待。
 * 当一个人用完餐后，就会离开餐桌，桌子就空出来了，这时候等待最久的人就可以入座了。使用 Semaphore 来实现这个场景。
 */
public class Restaurant {
    // 2张桌子
    public static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new eat(), "线程" + i).start();
        }
    }

    public static class eat implements Runnable{
        @Override
        public void run() {
            if (semaphore.tryAcquire()){
                try {
                    System.out.println(Thread.currentThread().getName() + " ---------  吃饭");
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }else {
                System.out.println(Thread.currentThread().getName() + " ---------  没位置了，走人");
            }
        }
    }

}
