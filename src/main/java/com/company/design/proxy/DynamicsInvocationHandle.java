package com.company.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhipeng.pang
 * @date 2022/10/20 11:10 周四
 */
public class DynamicsInvocationHandle<T> implements InvocationHandler {

    private T target;

    private DynamicsInvocationHandle(){}

    public DynamicsInvocationHandle(T target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 类比 spring aop
        // 动态获取before 和after 方法
        Class<MyProxyAspect> myProxyAspectClass = MyProxyAspect.class;
        myProxyAspectClass.getMethods();

        return method.invoke(target, args);
    }
}
