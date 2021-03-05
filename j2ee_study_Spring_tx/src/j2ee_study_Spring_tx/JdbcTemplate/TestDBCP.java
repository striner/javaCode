package j2ee_study_Spring_tx.JdbcTemplate;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDBCP {
	
	@Test
	public void demo01(){
		User user = new User();
		user.setId(1);
		user.setUsername("ccc");
		user.setPassword("333");
		
		String xmlPath = "j2ee_study_Spring_tx/JdbcTemplate/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		
		//获得目标类
		UserDao userDao = (UserDao) applicationContext.getBean("userDaoId");
		userDao.update(user);
	}

}
