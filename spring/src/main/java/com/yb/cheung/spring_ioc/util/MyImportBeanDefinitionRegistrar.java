package com.yb.cheung.spring_ioc.util;

import com.yb.cheung.spring_ioc.beans.Teacher;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param annotationMetadata        Import修饰的类
     * @param beanDefinitionRegistry    SpringBean的注册器
     */
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        //1.获得对象定义管理器
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Teacher.class);
        //2.创建当前Java类的实例对象
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        //3.通过Spring的Bean的注册器，将当前的实例对象添加到Spring容器中
        beanDefinitionRegistry.registerBeanDefinition("yb",beanDefinition);
    }
}
