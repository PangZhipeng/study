package com.company.mySpring.mySpringTest.service;

import com.company.design.proxy.UserInterface;
import com.company.mySpring.mySpring.InitializingBean;
import com.company.mySpring.mySpring.MyApplicationContext;
import com.company.mySpring.mySpring.annotation.Autowired;
import com.company.mySpring.mySpring.annotation.BeanNameAware;
import com.company.mySpring.mySpring.annotation.Component;
import com.company.mySpring.mySpring.annotation.Scope;

import javax.annotation.PostConstruct;

@Component("myCustomUser123123")
@Scope(MyApplicationContext.SCOPE_SINGLETON)
public class UserService implements InitializingBean, BeanNameAware, UserInterface {

    private OrderService orderService;

    public UserService() {

    }

    @PostConstruct
    public void initBefore(){
        System.out.println("post construct");
    }


    @Override
    public void afterPropertiesSet() {

    }

    @Override
    public void setName(String name) {
        System.out.println("beanµÄÃû×ÖÊÇ: " + name);
    }


    @Override
    public void doSomething() {
        System.out.println("userservice dosomething");
    }

    public void test(){
        System.out.println(orderService);
    }
}
