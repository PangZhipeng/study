package com.company.design.proxy;

import com.company.annotation.MyAfter;
import com.company.annotation.MyBefore;

/**
 * @author zhipeng.pang
 * @date 2022/10/20 11:31 ����
 */
public class MyProxyAspect {

    @MyBefore
    public void before (){
        System.out.println("----------  ������ʼǰdo ---------------");
    }

    @MyAfter
    public void after (){
        System.out.println("----------  ������ʼ�� ---------------");
    }
}
