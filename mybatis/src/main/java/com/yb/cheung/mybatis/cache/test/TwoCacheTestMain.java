package com.yb.cheung.mybatis.cache.test;

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

public class TwoCacheTestMain {private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    private SqlSession sqlSession2;

    private SqlSession sqlSession3;

    @Before
    public void before() throws Exception{
        InputStream in = Resources.getResourceAsStream("myBatis-config.xml");
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        Reader reader = new InputStreamReader(in);
        sqlSessionFactory = factoryBuilder.build(reader);
    }


    /*@After
    public void after(){
        try {
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    @Test
    public void test(){

        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao.findById(10));
        sqlSession.close();

        sqlSession2 = sqlSessionFactory.openSession();
        DeptDao dao2 = sqlSession2.getMapper(DeptDao.class);
        System.out.println(dao2.findById(10));

        sqlSession3 = sqlSessionFactory.openSession();
        DeptDao dao3 = sqlSession3.getMapper(DeptDao.class);
        System.out.println(dao3.findById(10));*/

        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));
        DeptDao dao2 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao2.findById(10));

        sqlSession.close();

        sqlSession2 = sqlSessionFactory.openSession();
        DeptDao dao3 = sqlSession2.getMapper(DeptDao.class);
        System.out.println(dao3.findById(10));*/

        /**
         * 条件：
         *  配置文件中，<setting name="cacheEnabled" value="true"/> 开启二级缓存
         *  mapper文件中，select标签中添加useCache 默认是true
         */
        //useCache对一级缓存的影响：1.useCache="true" 没有影响 2.useCache="false" 没有影响
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));

        DeptDao dao2 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao2.findById(10));*/

        //useCache对二级缓存的影响：1.useCache="true" 没有影响 2.useCache="false" 有影响
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));
        sqlSession.close();

        sqlSession2 = sqlSessionFactory.openSession();
        DeptDao dao2 = sqlSession2.getMapper(DeptDao.class);
        System.out.println(dao2.findById(10));*/


        /**
         * 条件：
         *  配置文件中，<setting name="cacheEnabled" value="true"/>
         *  mapper文件中，sql标签中添加flushCache 默认是false
         */

        //【查询】flushCache对一级缓存的影响：1.flushCache="true" 刷新缓存 2.flushCache="false" 不会刷新缓存
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));

        DeptDao dao2 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao2.findById(10));*/

        //【查询】flushCache对二级缓存的影响：1.flushCache="true" 刷新缓存 2.flushCache="false" 不会刷新缓存
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));
        sqlSession.close();

        sqlSession2 = sqlSessionFactory.openSession();
        DeptDao dao2 = sqlSession2.getMapper(DeptDao.class);
        System.out.println(dao2.findById(10));*/

        //【添加、修改、删除】flushCache对一级缓存的影响：1.flushCache="true" || flushCache="false" 刷新缓存
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));
        DeptDao dao2 = sqlSession.getMapper(DeptDao.class);
        dao2.delById(10);
        DeptDao dao3 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao3.findById(10));
        DeptDao dao4 = sqlSession.getMapper(DeptDao.class);
        dao4.deptAdd(new Dept(10,"aaa","bbb"));
        sqlSession2.commit();*/

        //【添加、修改、删除】flushCache对二级缓存的影响：1.flushCache="true" 刷新缓存 2.flushCache="false" 不会刷新缓存
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));
        DeptDao dao2 = sqlSession.getMapper(DeptDao.class);
        dao2.delById(10);
        sqlSession.commit();
        sqlSession.close();

        sqlSession2 = sqlSessionFactory.openSession();
        DeptDao dao3 = sqlSession2.getMapper(DeptDao.class);
        System.out.println(dao3.findById(10));
        DeptDao dao4 = sqlSession2.getMapper(DeptDao.class);
        dao4.deptAdd(new Dept(10,"aaa","bbb"));
        sqlSession2.commit();
        */

        /**
         * 条件：
         *  配置文件中，<setting name="cacheEnabled" value="true"/>
         *  mapper文件中，select标签中添加flushCache 默认是false
         */

        //【查询、添加、修改、删除】flushCache对一级缓存的影响：1.sqlSession.clearCache 刷新缓存
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));
        sqlSession.clearCache();
        DeptDao dao2 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao2.findById(10));*/


        //【查询、添加、修改、删除】flushCache对二级缓存的影响：1.sqlSession.clearCache 不会影响session查询结果保存到二级缓存中
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao1 = sqlSession.getMapper(DeptDao.class);
        System.out.println(dao1.findById(10));
        sqlSession.clearCache();
        sqlSession.close();

        sqlSession2 = sqlSessionFactory.openSession();
        DeptDao dao2 = sqlSession2.getMapper(DeptDao.class);
        System.out.println(dao2.findById(10));*/
    }
}
