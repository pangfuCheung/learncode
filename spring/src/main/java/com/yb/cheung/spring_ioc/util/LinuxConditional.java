package com.yb.cheung.spring_ioc.util;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 自定义判断条件
 * 1.如果当前环境是window环境则注册Student
 * 2.如果当前环境是Linux环境则注册Teacher
 */
public class LinuxConditional implements Condition {

    /**
     *
     * @param conditionContext          Spring容器的上下文环境，判断系统
     * @param annotatedTypeMetadata     当前注解@Conditional修饰的类型信息
     * @return
     */
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String sysname = conditionContext.getEnvironment().getProperty("os.name");
        System.out.println(" 当前系统名称 " + sysname);
        if (sysname.contains("Linux")){
            return true;
        }

        return false;
    }
}
