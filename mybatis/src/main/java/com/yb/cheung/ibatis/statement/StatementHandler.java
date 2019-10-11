package com.yb.cheung.ibatis.statement;

import com.yb.cheung.ibatis.configuration.Configuration;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatementHandler {

    private Configuration configuration;

    private Statement statement;

    private String method;

    public StatementHandler(Configuration configuration,String method){
        this.method = method;
        this.configuration = configuration;
    }

    public Object execute() throws Exception{
        Map sqlMap = configuration.getSqlMap();
        Connection connection = configuration.getConnection();
        statement = connection.createStatement();
        statement.execute(sqlMap.get(method).toString());
        ResultSet resultSet = statement.getResultSet();
        List list = new ArrayList();
        while (resultSet.next()){
            Class classFile = Class.forName((String) configuration.getTypeMap().get(method));
            Field fields[] = classFile.getDeclaredFields();
            Object classObj = classFile.newInstance();
            for (int i=0;i<fields.length;i++){
                Field field = fields[i];
                String name = field.getName();
                Class typeClass = field.getType();
                field.setAccessible(true);
                String resultValue = resultSet.getString(name);
                if (Integer.class == typeClass){
                    field.set(classObj,Integer.valueOf(resultValue));
                } else if (String.class == typeClass){
                    field.set(classObj,String.valueOf(resultValue));
                }
            }
            list.add(classObj);
        }
        close();
        return list;
    }

    public void close(){
        try {
            if (statement != null){
                statement.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
