package com.yb.cheung.mybatis.interceptor.test;

import com.alibaba.fastjson.JSON;
import com.yb.cheung.ibatis.dao.DeptDao;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.management.MXBean;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class TestMain {

    private SqlSession sqlSession;

    @Before
    public void before() throws Exception{
        InputStream in = Resources.getResourceAsStream("myBatis-config.xml");
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        Reader reader = new InputStreamReader(in);
        SqlSessionFactory sqlSessionFactory = factoryBuilder.build(reader);
        sqlSession = sqlSessionFactory.openSession();
    }


    @After
    public void after(){
        try {
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test(){

        DeptDao dao = sqlSession.getMapper(DeptDao.class);
        System.out.println(JSON.toJSONString(dao.findById(10)));

    }

}
