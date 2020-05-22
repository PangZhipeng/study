package com.company.threadTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
//        animal cat = new cat();
//        animal animal = new animal();
//        cat cat2 = (cat)animal;
//        //cat.call();
//        cat cat1 = (cat) cat;
//        cat2.fly();
        Integer cat1 = 1;
        Integer cat2 = 2;
        Integer cat3 = 3;
        Integer[] cats = {cat1,cat2,cat3};
        char[] vowelWorder = {'a', 'e', 'i', 'o', 'u','A', 'E', 'I', 'O', 'U'};

        String s = "over";
        System.err.println(Arrays.asList(vowelWorder).contains(s.charAt(0)));
    }

    public static void sleep(animal animal){
        animal.call();
    }
}

class animal{
    public void call(){
        System.out.println("动物在叫...");
    }
}

class cat extends animal{
    String name;

    public cat(String name) {
        this.name = name;
    }

    @Override
    public void call(){
        System.out.println("猫在叫...");
    }

    public void fly(){
        System.out.println("猫在飞...");
    }
}
