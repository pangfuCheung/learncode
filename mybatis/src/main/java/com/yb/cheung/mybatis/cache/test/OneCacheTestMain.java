package com.yb.cheung.mybatis.cache.test;

import com.alibaba.fastjson.JSON;
import com.yb.cheung.ibatis.bean.Dept;
import com.yb.cheung.ibatis.dao.DeptDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class OneCacheTestMain {

    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    @Before
    public void before() throws Exception{
        InputStream in = Resources.getResourceAsStream("myBatis-config.xml");
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        Reader reader = new InputStreamReader(in);
        sqlSessionFactory = factoryBuilder.build(reader);
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

        /**
         * 一次会话过程中自动从一级缓存中读取数据
         * 会话过程中第一次发送请求，从数据库中的得到数据
         * 得到结果之后，mybatis自动将这个查询结果放入当前用户的一级缓存
         * 会话过程中第二次发送请求，得到的数据是从缓存中获取了结果
         */
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao = sqlSession.getMapper(DeptDao.class);
        //System.out.println(JSON.toJSONString(dao.findById(10)));
        System.out.println(dao.findById(10));
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));*/

        //缓存失效：

        /**
         * 1.如果同一个用户分别使用了两个sqlsession则会使得一级缓存失效
         */
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao = sqlSession.getMapper(DeptDao.class);
        //System.out.println(JSON.toJSONString(dao.findById(10)));
        System.out.println(dao.findById(10));
        sqlSession2 = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession2.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));*/

        /**
         * 2.在同一个sqlsession，使用查询条件不同，也会导致一级缓存失效
         */
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao2 = sqlSession.getMapper(DeptDao.class);
        //System.out.println(JSON.toJSONString(dao.findById(10)));
        System.out.println(dao2.findById(10));
        DeptDao dao3 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao3.findById(12));*/

        /**
         * 3.在两次查询之间，使用相同的修改数据，一级缓存失效
         */
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));
        DeptDao addDao = sqlSession.getMapper(DeptDao.class);
        Dept dept = new Dept(13,"深圳部门","深圳");
        addDao.deptAdd(dept);
        DeptDao dao2 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao2.findById(10));
        sqlSession.commit();*/

        /**
         * 4.在同一个sqlsession使用相同的查询条件，此时如果手动刷新缓存（清空缓存）导致一级缓存失败
         */
        sqlSession = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));

        sqlSession.clearCache();

        DeptDao dao2 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao2.findById(10));

    }

}
