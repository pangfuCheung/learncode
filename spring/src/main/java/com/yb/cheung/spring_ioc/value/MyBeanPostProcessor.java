package com.yb.cheung.spring_ioc.value;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {


    //BeanPostProcessor接口的【自定义实现类】可以在当前Spring容器所有的Bean对象【初始化】前后调用
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        try {
            if(bean.getClass() == Dog.class){
                Field field = bean.getClass().getDeclaredField("age");
                field.setAccessible(true);
                field.set(bean,15);
            }else if (bean.getClass() == Bird.class){
                Field field = bean.getClass().getDeclaredField("age");
                field.setAccessible(true);
                field.set(bean,9);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }
}
