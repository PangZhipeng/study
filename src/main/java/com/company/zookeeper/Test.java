package com.company.zookeeper;

import com.alibaba.fastjson.JSONObject;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhipeng.pang
 * @date 2022/10/12 11:21 ����
 */
public class Test {

    public static void main(String[] args) throws Exception{
//        // zk ����
//        long start = System.currentTimeMillis();
//        ZooKeeper zooKeeper = ZkConnect.getConnect();
//        System.out.println("���Ӻ�ʱ��" + (System.currentTimeMillis() - start));

//        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        String create = zooKeeper.create(path, format.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//        System.out.println(create);
//        System.out.println("��ʱ�ڵ㴴�����");
//        for (int i = 0; i < 30; i++) {
//            Thread.sleep(1000L);
//        }
//        System.out.println("�ڵ�����");

        // �����ڵ�
        String path = "/java_create";
        CuratorFramework curatorClient = ZkConnect.getCuratorClient();
        String testNode = curatorClient.create()
                .creatingParentsIfNeeded()
                .withProtection()
                .withMode(CreateMode.EPHEMERAL)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(path,"aaa".getBytes(StandardCharsets.UTF_8));
        System.out.println(testNode);
        for (int i = 0; i < 10; i++) {
            System.out.println("------------------");
            Stat stat = new Stat();
            byte[] bytes = curatorClient.getData()
                    .storingStatIn(stat)
                    .forPath(testNode);
            System.out.println(JSONObject.toJSONString(stat));
            System.out.println(new String(bytes));
            curatorClient.setData().forPath(testNode, ("aaa" + i).getBytes(StandardCharsets.UTF_8));
        }

        Thread.sleep(Integer.MAX_VALUE);
    }
}
