package utils;

import common.utils.PropertiesUtil;
import org.junit.Test;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtilTest {
    @Test
    public void test1() {
        try {
            String dbConfigPath = "D:\\git\\codes\\javaCode\\utils\\src\\main\\resources\\db.properties";
            Properties pro = PropertiesUtil.fromFile(dbConfigPath);
            System.out.println(pro.getProperty("db.driver"));
            System.out.println(pro.getProperty("db.url"));
            System.out.println(pro.getProperty("db.userName"));
            System.out.println(pro.getProperty("db.password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
      public void test2() {
        try {
            String dbConfigPath = "db.properties";
            Properties pro = PropertiesUtil.fromPath(dbConfigPath);
            System.out.println(pro.get("db.driver"));
        } catch (Exception io) {
            io.printStackTrace();
        }
    }


    @Test
    public void test3() {
        System.out.println(PropertiesUtil.class.getResource(""));
        System.out.println(PropertiesUtil.class.getResource("/"));
        System.out.println(PropertiesUtil.class.getResource("/db.properties"));
        System.out.println("\n --------- \n");
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        System.out.println(propertiesUtil.getClass());
        System.out.println(propertiesUtil.getClass().getClassLoader());
        System.out.println(propertiesUtil.getClass().getClassLoader().getResource(""));
        System.out.println(propertiesUtil.getClass().getClassLoader().getResource("/"));  //不支持,name参数表示的是从classpath下获取的资源
        System.out.println(propertiesUtil.getClass().getClassLoader().getResource("db.properties"));
        System.out.println(propertiesUtil.getClass().getClassLoader().getResourceAsStream(""));
        System.out.println(propertiesUtil.getClass().getClassLoader().getResourceAsStream("db.properties"));
    }

    @Test
    public void test4() throws IOException{
        System.out.println(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        System.out.println(PropertiesUtil.fromStream(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties")));
    }
}
