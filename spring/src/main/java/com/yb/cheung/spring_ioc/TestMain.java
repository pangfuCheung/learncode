package com.yb.cheung.spring_ioc;

import com.yb.cheung.spring_ioc.config.ApplicationConfig;
import com.yb.cheung.spring_ioc.value.Dog;
import com.yb.cheung.spring_ioc.value.Properties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        //1.获得Spring容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        System.out.println("spring 启动了.....");

        //2.获得在spring容器已经注册bean对象
        String beanNames[] = context.getBeanDefinitionNames();
        for (int i=0;i<beanNames.length;i++){
            System.out.println(beanNames[i]);
        }

        /**
         * 1）默认时，以@Bean来修饰的bean对象对应的关键字（key）是【方法名】
         * 2）如果在@Bean中指定bean对象对应关键@Bean(value={"stu1","stu2"})
         *    此时bean对象对应的关键字（key）是stu1或stu2
         * 3）所有通过@Bean修饰生成的bean对象默认的情况下都是单例
         */
        /*Student student1 = (Student)context.getBean("stu1");
        Student student2 = (Student)context.getBean("stu1");
        System.out.println(student1);
        System.out.println(student2);*/

        /**
         * 4）对于单例的bean对象，可以通过@Lazy延缓对象被创建的时机
         */
        /*Student student1 = (Student)context.getBean("stu1");
        System.out.println(student1);*/

        /**
         *  FactoryBean创建对象
         */
        /*Student student =  (Student)context.getBean("myFactoryBean");
        System.out.println(student);*/


        /**
         * @Import
         */
        /*Teacher teacher = (Teacher) context.getBean("yb");
        System.out.println(teacher);*/

        /**
         * @Value
         * 基本数据
         * SPEL
         * 外部属性文件
         */
        /*Properties properties = (Properties) context.getBean("properties");
        System.out.println(properties);*/
        /*Dog dog = (Dog)context.getBean("dog");
        System.out.println(dog);*/

    }

}
