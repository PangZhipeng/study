package com.company.design.proxy;

/**
 * @author zhipeng.pang
 * @date 2022/10/20 10:52 ����
 */
public class User implements UserInterface{

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void doSomething() {
        System.out.println("�û�: "+ name +"     ����һЩ��");
    }
}
