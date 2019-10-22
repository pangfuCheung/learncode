package com.yb.cheung.spring_ioc.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = {"classpath:/config.properties"})
@Component
public class Properties {

    //使用基本数据为属性赋值
    @Value("allen")
    private String name;

    //使用SPEL为属性赋值
    @Value("#{8-4}")
    private int age;

    //读取外部properties属性文件的内容
    @Value("${jdbc.home}")
    private String home;

    @Override
    public String toString() {
        return "Properties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", home='" + home + '\'' +
                '}';
    }
}
