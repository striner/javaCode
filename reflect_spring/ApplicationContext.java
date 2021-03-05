package reflect_spring;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ApplicationContext {

	//缓存Spring容器中的Bean对象
	private Map<String, Object> beans = new HashMap<>();
	/*
	 * 利用配置文件初始化当前容器
	 * 利用xml配置文件,初始化全部的Bean对象
	 */
	public ApplicationContext(String xml) throws Exception {
		//利用DOM4j读取xml文件
		//解析XML文件内容,得到Bean的类名和Bean的id
		//根据类名动态加载类并且创建对象
		//将对象和对应的id添加到Map缓存中
		//从Resourse(classpath)中读取流
		InputStream in = getClass().getClassLoader().getResourceAsStream(xml);
		SAXReader reader = new SAXReader();   //相当于一个高级流,高级流需依附于低级流
		Document doc = reader.read(in);
		in.close();
		//解析xml:<beans><bean><bean>...<beans>
		Element root = doc.getRootElement();   //Element就是beans
		//读取根元素中全部的bean子元素.
		List<Element> list = root.elements("bean"); 
		for (Element e : list) {
			//e就是bean元素   属性: id, class
			String id = e.attributeValue("id");   //获取属性
			String className = e.attributeValue("class");
			//动态加载类,动态创建对象
			Class clazz = Class.forName(className);
			Object bean = clazz.newInstance();
			beans.put(id, bean);
		}
	}
	
	public Object getBean(String id) {
		//根据id在Map中查找对象,并返回对象
		return beans.get(id);
	}
	
	//泛型方法
	public <T> T getBean(String id, Class<T> cls) {
		return (T) beans.get(id);
	}
}
