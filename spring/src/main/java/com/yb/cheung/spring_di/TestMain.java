package com.yb.cheung.spring_di;

import com.yb.cheung.spring_di.bean.Dept;
import com.yb.cheung.spring_di.util.ApplicationContext;
import org.junit.Test;

public class TestMain {

    @Test
    public void test(){
        //Dept dept = new Dept();
        ApplicationContext context = new ApplicationContext();
        Dept dept = (Dept) context.getBean("dept");
        /*

        System.out.println(dept.hashCode());

        Dept dept1 = (Dept) context.getBean("dept");
        System.out.println(dept1.hashCode());*/

        /*DeptService service = (DeptService)context.getBean("myService");
        DeptDao dao =  service.getDao();
        dao.save();*/
        context.close();
        System.out.println(dept);
    }


}
