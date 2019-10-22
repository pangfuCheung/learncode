package com.yb.cheung.spring_di.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationContext {

	private static Document config;

	private Class classFile = null;

	private static Map<String,Object> staticContainer = new HashMap();

	static {
		try {
			//IOC容器启动的时候，加载配置文件
			SAXReader saxReader = new SAXReader();
			config = saxReader.read("src/main/resources/applicationContext.xml");
			createBean();
			System.out.println(" 加载配置文件 applicationContext.xml ......");
			System.out.println(" 加载容器....... ");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	//在容器启动时，加载single类
	private static void createBean() throws Exception{
		System.out.println(" 初始化单例对象 ...... ");
		String xPath = "//bean[@scope='single']";
		List<Element> elementList = null;
		//1.找到配置文件中所有的bean标签
		elementList = config.selectNodes(xPath);
		//2.遍历所有bean标签
		for (Element element:elementList){
			String classPath = element.attributeValue("class");
			Object obeject = Class.forName(classPath).newInstance();
			staticContainer.put(classPath,obeject);
		}
	}

	//1.根据开发人员根据ID编号返回对象bean
	public Object getBean(String beanId){
		String xPath = "//bean[@id='"+beanId+"']";
		Object object = null;
		List<Element> list = null;

		list = config.selectNodes(xPath);
		Element element = list.get(0);
		String classPath = element.attributeValue("class");

		object = staticContainer.get(classPath);

		//通过反射机制创建Bean对象
		try {
			classFile = Class.forName(classPath);
			if(object == null){
				object = classFile.newInstance();
			}
			//初始化
			initBean(object,element);
		}catch (Exception e){
			e.printStackTrace();
		}
		return object;
	}

	//2.IOC容器对bean进行初始化处理
	private void initBean(Object bean,Element beanElement){
		System.out.println(" IOC容器对bean进行初始化处理...... ");
		//检索当前标签是否有子标签
		List<Element> properList = beanElement.elements("property");
		if (properList == null || properList.size() == 0){
			return;
		}

		//开始赋值
		for (Element property:properList){
			//读取name属性和value属性的值
			String fieldName =property.attributeValue("name");
			String value = property.attributeValue("value");
			String ref = property.attributeValue("ref");
			Field field = null;
			String typeName = null;
			try {
				field = classFile.getDeclaredField(fieldName);
				//获取当前属性的数据类型名
				typeName = field.getType().getName();
				if(ref != null){
					di(bean,ref,field);
				}
				setValue(bean,field,typeName,value);
			}catch (Exception e){
				e.printStackTrace();
			}
		}

	}

	//根据属性类型进行赋值实现
	private void setValue(Object bean,Field field,String typeName,String value){
		//可以对私有属性进行操作
		field.setAccessible(true);
		try {
			if("java.lang.String".equals(typeName)){
				field.set(bean,value);
			}else if ("java.lang.Integer".equals(typeName)){
				field.set(bean,Integer.valueOf(value));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	//二、依赖注入的实现
	private void di(Object bean,String ref,Field field) throws Exception{
		//1.定位ref关联的bean标签
		String xPath = "//bean[@id='"+ref+"']";
		Element element = null;
		element = (Element)config.selectNodes(xPath).get(0);
		//2.获取类文件的路径
		String classPath = element.attributeValue("class");
		//3.去staticMap索要对应的bean
		Object object = staticContainer.get(classPath);
		if(object == null){	//说明它不是一个单例
			object = Class.forName(classPath).newInstance();
		}
		field.setAccessible(true);
		field.set(bean,object);
	}

	//三、关闭容器
	public void close(){
		staticContainer.clear();
	}

}




