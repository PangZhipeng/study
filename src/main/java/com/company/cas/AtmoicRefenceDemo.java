package com.company.cas;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.concurrent.atomic.AtomicReference;


class User{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtmoicRefenceDemo {
    public static void main(String[] args) {
        User z3 = new User("zhangsan",3);
        User li4 = new User("li4",4);
        User w5 = new User("wang5",5);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        atomicReference.compareAndSet(w5,li4);
        System.out.println(atomicReference.get().toString());
    }
}
