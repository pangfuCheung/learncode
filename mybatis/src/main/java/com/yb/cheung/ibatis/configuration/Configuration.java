package com.yb.cheung.ibatis.configuration;

import org.apache.ibatis.io.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {

    private Connection connection;

    private String path;

    private String daoName;

    private Map sqlMap = new HashMap();

    private Map typeMap = new HashMap();

    public Map getSqlMap() {
        return sqlMap;
    }

    public Map getTypeMap() {
        return typeMap;
    }

    public synchronized Connection getConnection() {
        return connection;
    }

    public Configuration(String path){
        this.path = path;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        }catch (Exception e){
            e.printStackTrace();
        }
        initConfiguration();
    }

    public void close(){
        try {
            if (connection != null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 初始化配置文件，此处我们只做简单的处理mapper文件，
     * 其它：
     *      plugins（配置拦截器）
     *      properties（一般存放数据链接和账号密码信息）
     *      environment（读取properties信息生成数据链接对象）
     *      transactionManager（事务管理，一般交给spring管理）
     *      dataSource：
     *                  dataSource 元素使用标准的 JDBC 数据源接口来配置 JDBC 连接对象的资源。数据源类型有三种：UNPOOLED，POOLED，JNDI。
     *                  UNPOOLED是没有数据库连接池的,没执行一次操作,打开一次数据库,关闭一次数据库.效率较为低下
     *                  POOLED是存在数据库连接池的,没有操作数据库从数据库连接池中拿取连接
     *                  JNDI这个数据源的实现是为了能在如 EJB 或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个 JNDI 上下文的引用。
     *      mappers：它是数据库mapper.xml
     *
     */
    public void initConfiguration() {
        try {
            //1.读取资源文件夹mapper，该文件夹的XML和DAO一一对应，XML中的sql标签的id和DAO中的方法一一对应，读取mapper文件是使用了dom4j技术
            URL url = Configuration.class.getClassLoader().getResource("");
            File fileFolder = new File(url.getPath() + path);
            String[] fileStrs = fileFolder.list();
            for (int i=0;fileStrs != null && fileStrs.length > i;i++){
                String fullDaoName = fileStrs[i];
                daoName = fullDaoName.substring(0,fullDaoName.indexOf("."));
                InputStream in = Resources.getResourceAsStream(path + "/" + fullDaoName);
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(new InputStreamReader(in));
                setSqlMapAndTypeMethod(document.getRootElement(),(String) null);
            }
            //System.out.println(sqlMap);
            //System.out.println(typeMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 利用递归遍历xml文件，获取dao对应xml文件的sql和返回值类型
     *
     * @param rootElement
     * @param methodName
     */
    public void setSqlMapAndTypeMethod(Element rootElement,String methodName){

        List<Attribute> attributeList = rootElement.attributes();
        for (int i=0;attributeList != null && attributeList.size()>0 && attributeList.size() > i;i++){
            Attribute attribute = attributeList.get(i);
            String name = attribute.getName();
            String value = attribute.getValue();
            if("id".equals(name)){
                methodName = daoName + "." +value;
                sqlMap.put(methodName,rootElement.getStringValue());
            } else if ("resultType".equals(name)){
                typeMap.put(methodName,value);
            }
        }

        List<Element> elementList = rootElement.elements();
        for (int i=0;elementList != null && elementList.size() > 0 && elementList.size() > i;i++){
            Element element = elementList.get(i);
            setSqlMapAndTypeMethod(element,methodName);
        }
    }

    public static void main(String[] args) {
        Configuration configuration = new Configuration("mapper");
        configuration.initConfiguration();
    }

}
