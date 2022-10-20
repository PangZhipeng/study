package com.company.design.proxy;

import com.company.annotation.MyAfter;
import com.company.annotation.MyBefore;

/**
 * @author zhipeng.pang
 * @date 2022/10/20 11:31 周四
 */
public class MyProxyAspect {

    @MyBefore
    public void before (){
        System.out.println("----------  方法开始前do ---------------");
    }

    @MyAfter
    public void after (){
        System.out.println("----------  方法开始后 ---------------");
    }
}
