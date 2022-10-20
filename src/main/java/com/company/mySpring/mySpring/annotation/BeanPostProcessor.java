package com.company.mySpring.mySpring.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhipeng.pang
 * @date 2022/10/20 18:33 ÷‹Àƒ
 */
public interface BeanPostProcessor {

    default Object postProcessorBeforeInitialization(Object bean,String beanName){
        return bean;
    }

    default Object postProcessorAfterInitialization(Object bean,String beanName){
        return bean;
    }
}
