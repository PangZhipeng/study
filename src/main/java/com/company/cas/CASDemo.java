package com.company.cas;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS compare and swap
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        atomicInteger.compareAndSet(5,20);
        System.out.println(atomicInteger.get());
    }
}
