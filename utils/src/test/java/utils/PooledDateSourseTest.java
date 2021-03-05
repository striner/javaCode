package utils;

import common.utils.PooledDataSource;
import org.junit.Test;

public class PooledDateSourseTest {
    @Test
    public void test() {

        try{
            new PooledDataSource().init("D:\\git\\codes\\javaCode\\utils\\src\\main\\resources\\db.properties");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}

