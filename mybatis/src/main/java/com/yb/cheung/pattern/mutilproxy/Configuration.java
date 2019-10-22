package com.yb.cheung.pattern.mutilproxy;

import org.apache.ibatis.io.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private List plugins = new ArrayList();

    public List getPlugins() {
        return plugins;
    }

    public void initPlugins() throws Exception{
        InputStream in = Resources.getResourceAsStream("myBatis-config.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(new InputStreamReader(in));
        traverseDocument(document.getRootElement());
        List list = getPlugins();
        System.out.println(list);
    }

    public void traverseDocument(Element rootElement){
        List<Attribute> attributeList = rootElement.attributes();
        for (int i=0;attributeList != null && attributeList.size()>0 && attributeList.size() > i;i++){
            Attribute attribute = attributeList.get(i);
            String name = attribute.getName();
            String value = attribute.getValue();
            if("interceptor".equals(name)){
                System.out.println(name);
                System.out.println(value);
                try {
                    Class intercepter = Class.forName(value);

                    plugins.add(intercepter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        List<Element> elementList = rootElement.elements();
        for (int i=0;elementList != null && elementList.size() > 0 && elementList.size() > i;i++){
            Element element = elementList.get(i);
            traverseDocument(element);
        }
    }

/*    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.initPlugins();
    }*/

}
