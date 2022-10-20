package com.company.mySpring.mySpringTest;

import com.company.design.proxy.UserInterface;
import com.company.mySpring.mySpring.MyApplicationContext;
import com.company.mySpring.mySpringTest.service.OrderService;
import com.company.mySpring.mySpringTest.service.UserService;

/**
 * 手写模拟spring
 */
public class Main {

    public static void main(String[] args) throws Exception{
        // 扫描路径 并创建单例bean
        MyApplicationContext myApplicationContext = new MyApplicationContext(AppConfig.class);
        UserInterface userService = (UserInterface) myApplicationContext.getBean("myCustomUser123123");
        userService.doSomething();
    }
}
