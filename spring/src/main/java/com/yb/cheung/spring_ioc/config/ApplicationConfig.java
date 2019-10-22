package com.yb.cheung.spring_ioc.config;

import com.yb.cheung.spring_ioc.beans.Dept;
import com.yb.cheung.spring_ioc.beans.Student;
import com.yb.cheung.spring_ioc.beans.Teacher;
import com.yb.cheung.spring_ioc.dao.DeptDao;
import com.yb.cheung.spring_ioc.util.*;
import com.yb.cheung.spring_ioc.value.MyBeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * 相当于spring的一个配置文件，ApplicationConfig是一个spring组件，相当于把ApplicationConfig这个Java类注册到spring中
 */
@Configuration

/**
 * @CompanetScan注解
 */
//1.默认规则
//@ComponentScan(value = "com.yb.cheung.spring_ioc")
//2.指定不加载某些具有一定规则的类
/*@ComponentScan(value = "com.yb.cheung.spring_ioc",
        excludeFilters={
        @ComponentScan.Filter(
                //表示按照注解进行过滤         //表示指定类被过滤，不加载到Spring容器中
                //type = FilterType.ANNOTATION,value = {Controller.class, Service.class}
                //表示只加载指定的类型的类进行过滤
                //type = FilterType.ASSIGNABLE_TYPE,value = {DeptDao.class}
                //根据自定义规则进行过滤
                //type = FilterType.CUSTOM ,value = {}
        )
})*/

/**
 * @Import
 */
//@Import(value = {Student.class,Teacher.class})
//@Import(value = {MyImportSelector.class})
//@Import(value = {MyImportBeanDefinitionRegistrar.class})
@ComponentScan(value = "com.yb.cheung.spring_ioc.value")
public class ApplicationConfig {

    /**
     * @Bean注解
     */

    /*@Bean(value = {"stu1","stu2"})
    public Student student1() {
        return new Student();
    }

    @Bean
    @Lazy   //这个注解专用于单例模式的对象，此时该bean不会在spring启动时被创建，而是被获取的时候被创建
    public Teacher teacher(){
        return new Teacher();
    }*/

    /**
     * @Conditional
     * 1.如果当前环境是window环境则注册Student
     * 2.如果当前环境是Linux环境则注册Teacher
     */
    /*@Conditional(value = {WindowConditional.class})
    @Bean
    public Student student() {
        return new Student();
    }

    @Conditional(value = {LinuxConditional.class})
    @Bean
    public Teacher teacher(){
        return new Teacher();
    }*/

    /**
     *  FactoryBean创建对象
     */
    /*@Bean
    public MyFactoryBean myFactoryBean(){
        return new MyFactoryBean();
    }*/

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){
        return new MyBeanPostProcessor();
    }
}
