package com.company.Thread;

public class GuardedSuspension {
    public static void main(String[] args) {
        Data data = new Data();
        Thread t1 = new Thread(new Producer(data), "ProducerThread");
        Thread t2 = new Thread(new Consumer(data), "ConsumerThread");
        t1.start();
        t2.start();
    }
}

class Data {
    private String message;
    private boolean isProduced;

    public synchronized void produce(String message) throws InterruptedException {
        while (isProduced) {
            wait();
        }
        this.message = message;
        isProduced = true;
        System.out.println(Thread.currentThread().getName() + " produced " + message);
        notifyAll();
    }

    public synchronized String consume() throws InterruptedException {
        while (!isProduced) {
            wait();
        }
        String message = this.message;
        isProduced = false;
        System.out.println(Thread.currentThread().getName() + " consumed " + message);
        notifyAll();
        return message;
    }
}

class Producer implements Runnable {
    private Data data;

    public Producer(Data data) {
        this.data = data;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                data.produce("Message " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private Data data;

    public Consumer(Data data) {
        this.data = data;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                String message = data.consume();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

