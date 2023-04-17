package com.company.Thread.MultiThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 有3个线程分别打印字符串"AAA"，"BBB"和"CCC"，
 * 要求线程按照顺序依次打印，
 * 即每个线程打印完自己的字符串后等待其他线程打印完毕后再继续执行。
 *
 * 提示：可以使用CyclicBarrier来控制线程的执行顺序。
 */
public class ABCyclicBarrier {

    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
        System.out.println("输出完成");
    });

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start();
        }
    }
    
    public static class Task implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "AAA");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "BBB");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "CCC");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // 输出当前时间

}
