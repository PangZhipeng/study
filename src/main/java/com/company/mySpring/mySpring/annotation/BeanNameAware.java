package com.company.mySpring.mySpring.annotation;

/**
 * spring初始化类之后的类名回调事件
 * @author zhipeng.pang
 * @date 2022/10/20 17:47 周四
 */
public interface BeanNameAware {
    public void setName(String name);
}
