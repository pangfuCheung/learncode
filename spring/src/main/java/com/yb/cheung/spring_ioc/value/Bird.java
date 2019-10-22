package com.yb.cheung.spring_ioc.value;

import org.springframework.stereotype.Component;

@Component
public class Bird {

    private int age;

    @Override
    public String toString() {
        return "Bird{" +
                "age=" + age +
                '}';
    }
}
