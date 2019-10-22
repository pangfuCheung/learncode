package com.yb.cheung.spring_ioc.util;

import com.yb.cheung.spring_ioc.beans.Student;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

//@Component
public class MyFactoryBean implements FactoryBean<Student> {

    //通知Spring容器，当前Student类的实例对象创建方式
    public Student getObject() throws Exception {
        return new Student();
    }

    //通知Spring容器，当前管理的bean对象在Spring容器对应的类型
    public Class<?> getObjectType() {
        return Student.class;
    }

    //true：单例 false：prototype
    public boolean isSingleton() {
        return false;
    }
}
