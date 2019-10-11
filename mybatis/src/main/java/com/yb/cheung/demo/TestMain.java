package com.yb.cheung.demo;

import com.yb.cheung.demo.dao.DeptDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 *  mybatis的执行流程：
 *      1.根据配置文件myBatis-config.xml加载初始化参数
 *      2.通过configuration去build一个SqlSessionFactory
 *      3.获取一个SqlSession
 *      4.最后通过SqlSession获取一个接口的代理类，去执行SQL语句，返回执行结果
 */
public class TestMain {

    private SqlSession session;

    @Before
    public void before() throws Exception{
        InputStream is = Resources.getResourceAsStream("myBatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(new InputStreamReader(is));
        session = sessionFactory.openSession();
    }


    @After
    public void after(){
        try {
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void test(){
        DeptDao dao = session.getMapper(DeptDao.class);
        List list = dao.findAll();
        System.out.println(list.get(0));
        System.out.println(list.get(1));

    }

}


