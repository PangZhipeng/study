package com.company.Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class myThreadData{
    private volatile int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add() {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+"\t当前num:" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decr() {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"\t当前num:" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}



public class Producer_Consumer {
    public static void main(String[] args) {
        myThreadData myThreadData = new myThreadData();

        for (int i = 0; i < 5; i++) {
            new Thread(()->myThreadData.add(),"t1").start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(()->myThreadData.decr(),"t2").start();
        }
    }
}
