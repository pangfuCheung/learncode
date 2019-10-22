package com.yb.cheung.mybatis.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.javassist.tools.reflect.Metaobject;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * OneInterceptor相当于代理实现类，因为我们对四大神器进行代理监听，所以intercept方法就是代理实现类
 * 需要执行的代码
 *
 * Signature：
 *  type：是被监听对象方法的返回值类型
 *  method：是被监听对象方法
 *  args：是被监听对象方法的参数类型，因为java中有方法重载，防止找不到目标方法
 *
 *  综上：OneInterceptor是对StatementHandler对象中参数为Statement对象的parameterize方法进行监听
 */

/*
//此签名声明的方法是修改参数
@Intercepts(value = {@Signature(
        type= StatementHandler.class,
        method = "parameterize",
        args = {Statement.class}
)})*/
@Intercepts(value = {@Signature(
        type= StatementHandler.class,
        method = "prepare",
        args = {Connection.class,Integer.class}
)})
public class OneInterceptor implements Interceptor {

    /**
     * 【任务】为当前目标对象生成代理对象
     *
     *      监听对象（代理对象） =  Proxy.newInstanceProxy(ClassLoader,InterfacesArry,代理实现类)
     * @param target 四大神器的实例对象
     * @return 监听对象（代理对象）
     */
    public Object plugin(Object target) {
        System.out.println("被监听的神器： ----------------- "+ target);
        //Plugin 它继承了InvocationHandler对象，它实际实例了一个代理监听对象然后返回
        Object proxyObject = Plugin.wrap(target,this);
        return proxyObject;
    }

    /**
     * 【任务】：对拦截的神器对象及其行为进行执行控制，此处拦截的实体类和方法是在Signature这个注解中进行声明
     *          相当于invoke方法；invoke(Object proxy, Method method, Object[] args)
     *
     * @param invocation
     *          封装了被拦截的神器对象 StatementHandler对象
     *          封装了被拦截的神器行为 prepare；实际上就是神器需要执行的方法
     * @return 就是当前被拦截的神器行为的执行结果
     * @throws Throwable
     */
    public Object intercept(Invocation invocation) throws Throwable {


        //2.获得当前被拦截的神器行为//1.获取当前被拦截的神器对象
        Object object = invocation.getTarget();
        System.out.println("  当前被拦截的神器对象 " + object);

        Method methodObj = invocation.getMethod();
        System.out.println("  当前被拦截的神器对象 " + methodObj);

        //3.执行被拦截的神器行为的执行结果
        Object result = invocation.proceed();
        System.out.println(" 执行结果 " + result);

        System.out.println(" OneInterceptor`s is run ......... ");

        //Demo：目标通过拦截器开发，实现在给sql赋值的时候，我们需要改变参数的值
        //1.获取拦截的目标对象
        Object preparedStatementHandler = invocation.getTarget();
        //2.使用反射封装工具类，读取被拦截的目标对象的属性信息
        MetaObject metaobject = SystemMetaObject.forObject(preparedStatementHandler);
        //3.读取拦截的目标对象中的【parameterHandler.parameterObject】
        Object param = metaobject.getValue("parameterHandler.parameterObject");
        //4.修改sql语句的赋值参数
        System.out.println(" 在sql语句进行赋值之前，拦截得到实参" + param);
        metaobject.setValue("parameterHandler.parameterObject",20);
        //5.获取prepareedStatement中需要执行的sql语句
        String sqlObj = (String)metaobject.getValue("boundSql.sql");
        System.out.println(" 被执行的原sql语句： " + sqlObj);
        metaobject.setValue("parameterHandler.parameterObject","广州");
        metaobject.setValue("boundSql.sql","select * from dept where loc=?");
        //放行拦截的方法
        Object result1 = invocation.proceed();
        return result1;
    }

    /**
     * 【任务】：为拦截对象的相关属性赋值的
     * @param properties
     */
    public void setProperties(Properties properties) {
        String driver = properties.getProperty("driver");
        System.out.println(" OneInterceptor 被设置的参数 "  + driver);
    }
}
