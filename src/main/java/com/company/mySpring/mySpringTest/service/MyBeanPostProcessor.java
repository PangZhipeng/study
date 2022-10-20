package com.company.mySpring.mySpringTest.service;

import com.company.mySpring.mySpring.annotation.BeanPostProcessor;
import com.company.mySpring.mySpring.annotation.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhipeng.pang
 * @date 2022/10/20 18:37 周四
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) {
        return BeanPostProcessor.super.postProcessorBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) {
        Object proxyInstance = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("aop before 参数校验");
                method.invoke(bean,args);
                System.out.println("aop after 打印日志");
                return null;
            }
        });
        return proxyInstance;
    }
}
