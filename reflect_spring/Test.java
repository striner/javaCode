package reflect_spring;

import java.util.Date;

public class Test {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ApplicationContext("ApplicationContext.xml");
		Date date = (Date) ctx.getBean("date");
		System.out.println(date);
	}
}
