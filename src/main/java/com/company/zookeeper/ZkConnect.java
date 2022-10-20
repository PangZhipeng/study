package com.company.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryForever;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhipeng.pang
 * @date 2022/10/12 11:21 ����
 */
@SuppressWarnings("ALL")
public class ZkConnect {

    public final static String CONNECT_STRING = "127.0.0.1:2181";

    private final static Integer SESSION_TIME_OUT = 5000;

    private final static Integer CONNECT_TIME_OUT = 5000;

    /**
     * zk�ٷ��ͻ��� ����
     * @return
     */
    public static ZooKeeper getConnect(){
        ZooKeeper zooKeeper = null;
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(CONNECT_STRING, CONNECT_TIME_OUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("state: " + watchedEvent.getState());
                    System.out.println("type: " + watchedEvent.getType());
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return zooKeeper;
    }

    public static CuratorFramework getCuratorClient(){

        RetryPolicy retryPolicy = new RetryForever(1);

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_STRING)
                .sessionTimeoutMs(SESSION_TIME_OUT)
                .connectionTimeoutMs(CONNECT_TIME_OUT)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();
        curatorFramework.start();
        return curatorFramework;
    }
}
