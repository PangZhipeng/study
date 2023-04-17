package com.company.Thread.MultiThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有两个线程，一个线程用来输出 110 的数字，另一个线程用来输出 AJ 的字母，要求交替输出，
 * 即输出 1A2B3C...9I10J。可以使用Condition实现。
 */
public class NumberLetter {

    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    public static Integer number = 0;
    public static Integer letter = 64;
    public static Boolean isNumber = true;


    public  static void main(String[] args) {
        new Thread(new NumberThread()).start();
        new Thread(new LetterThread()).start();
    }

    public static class  NumberThread implements Runnable{
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (!isNumber){
                        condition.await();
                    }
                    number ++;
                    System.out.println(number);
                    isNumber = false;
                    condition.signalAll();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static class  LetterThread implements Runnable{
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (isNumber){
                        condition.await();
                    }
                    letter ++;
                    System.out.println((char) letter.intValue());
                    isNumber = true;
                    condition.signalAll();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
