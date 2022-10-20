package com.company.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Channel;

/**
 * @author zhipeng.pang
 * @version 1.0
 * @date 2020/5/22 13:21
 */
public class TestBuffer {
    public static void main(String[] args) {

        while (true){
            ByteBuffer by =ByteBuffer.allocate(1024);
        }

//        String s1 = "abcde";
//
//        // 初始化一个缓冲区
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//        System.out.println("-----allocate byteBuffer-------");
//
//        //缓冲区的大小
//        System.out.println("capacity:  "+buffer.capacity());
//        //缓冲器可以操作数据的大小  limit之后的数据不能进行读写
//        System.out.println("limit:  "+buffer.limit());
//        //当前操作数据的位置
//        System.out.println("position:  "+buffer.position());
//        //   0 <= mark <= position <= limit <= capacity
//
//
//        //放入数据
//        buffer.put(s1.getBytes());
//
//        System.out.println("-----after put-------");
//
//        System.out.println("capacity:  "+buffer.capacity());
//        System.out.println("limit:  "+buffer.limit());
//        System.out.println("position:  "+buffer.position());
//
//        //切换读模式  底层就是改变了 几个值的索引位置
//        buffer.flip();
//
//        System.out.println("-----after flip-------");
//
//        System.out.println("capacity:  "+buffer.capacity());
//        System.out.println("limit:  "+buffer.limit());
//        System.out.println("position:  "+buffer.position());
//
//        // 数据仍然存在  重置 limit和position的值
//        buffer.clear();
//
//        System.out.println("-----after clear-------");
//
//        System.out.println("capacity:  "+buffer.capacity());
//        System.out.println("limit:  "+buffer.limit());
//        System.out.println("position:  "+buffer.position());
    }
}
