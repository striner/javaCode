package reflect;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Demo1 {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		System.out.print("input Class name:");
		String className = in.nextLine();
		//动态加载类
		Class cls = Class.forName(className);
		//动态获取全部方法
		Method[] ary = cls.getDeclaredMethods();
		//动态检查方法的注解信息
		for (Method method : ary) {
			//检查一个方法的注解信息
			//method.getAnnotation(被检查的注解类型);
			Demo ann = method.getAnnotation(Demo.class);
			//返回注册类型,如果为空表示没有注解
			//不为空表示找到了被检查的注解Annotation
			if (ann != null) {
				System.out.println(method);
			}
		}
	}
}
