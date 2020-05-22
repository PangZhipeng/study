package com.company.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * 通道  节点之间的连接  负责缓冲区的传输 本身不传输数据
 * @author zhipeng.pang
 * @version 1.0
 * @date 2020/5/22 14:23
 */
public class TestChannel {
    public static void main(String[] args) {
        // 利用通道完成文件的复制
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fisChannel = null;
        FileChannel fosChannel = null;
        try {
            fis = new FileInputStream("C:\\Users\\zhipeng.pang\\Desktop\\Images\\assistant\\logs\\spring.log");
            fos = new FileOutputStream("C:\\Users\\zhipeng.pang\\Desktop\\Images\\assistant\\logs\\spring.log");

            //获取通道
            fisChannel = fis.getChannel();
            fosChannel = fos.getChannel();

            //分配缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (fisChannel.read(byteBuffer) != -1){
                byteBuffer.flip();
                fosChannel.write(byteBuffer);
                byteBuffer.clear();
            }

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }
}
