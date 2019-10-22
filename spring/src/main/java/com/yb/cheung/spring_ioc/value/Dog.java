package com.yb.cheung.spring_ioc.value;

import org.springframework.stereotype.Component;

@Component
public class Dog {

    private int age;

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                '}';
    }
}
