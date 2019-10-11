package com.yb.cheung.ibatis.test;

import com.yb.cheung.ibatis.configuration.Configuration;
import com.yb.cheung.ibatis.dao.DeptDao;
import com.yb.cheung.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class TestMain {

    private SqlSessionFactory sqlSessionFactory;

    private Configuration configuration;

    @Before
    public void before(){
        configuration = new Configuration("mapper");
        sqlSessionFactory = new SqlSessionFactory(configuration);
    }

    @After
    public void after(){
        configuration.close();
    }

    @Test
    public void test(){
        DeptDao deptDao = (DeptDao) sqlSessionFactory.getMapper(DeptDao.class);
        List list = deptDao.findAll();
        System.out.println(list.toString());
    }

    /**
     *  JDBC的执行流程，mybatis其实就是对ibatis的封装，而ibatis的底层封装了JDBC的操作
     */
    public static void main(String[] args)  {
        try {
            // 1.加载JDBC的驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2.通过驱动管理器获取connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
            // 3.通过Connection获取statement
            Statement statement = connection.createStatement();
            // 4.statement相当于数据库操作对象，statement去执行sql语句
            statement.execute("select * from user;");
            // 5.获取数据库返回的结果集
            ResultSet resultSet = statement.getResultSet();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
