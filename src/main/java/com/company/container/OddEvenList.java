package com.company.container;

import java.util.ArrayList;
import java.util.List;

/**
 * 假设有一个数组 a，初始化如下：
 * int[] a = {1, 2, 3, 4, 5};
 * 现在有两个线程 A 和 B，
 * 线程 A 用 CopyOnWriteArrayList 将数组 a 转化成列表，并删除掉其中所有的奇数；
 * 线程 B 将数组 a 转化成列表，并删除掉其中所有的偶数。
 * 请写出代码，使得线程 A 和 B 能够正确地对数组 a 进行操作。
 */
public class OddEvenList {

    public static int[] a = {1, 2, 3, 4, 5};

    public static void main(String[] args) {
        new Thread(new OddThread()).start();
        new Thread(new EvenThread()).start();
    }

    public static  class OddThread implements Runnable{
        @Override
        public void run() {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                if (a[i] % 2 == 0){
                    list.add(a[i]);
                }
            }
            System.out.println(list);
        }
    }

    public static  class EvenThread implements Runnable{
        @Override
        public void run() {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                if (a[i] % 2 == 1){
                    list.add(a[i]);
                }
            }
            System.out.println(list);
        }
    }

    // 输入字符串转换为日期

}
