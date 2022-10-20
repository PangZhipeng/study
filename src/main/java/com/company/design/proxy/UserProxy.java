package com.company.design.proxy;

/**
 * @author zhipeng.pang
 * @date 2022/10/20 10:53 周四
 */
public class UserProxy implements UserInterface{

    private User user;

    private UserProxy(){
    }

    public UserProxy(User user){
        this.user = user;
    }

    @Override
    public void doSomething() {
        System.out.println("代理对象静态doSomething");
        user.doSomething();
    }
}
