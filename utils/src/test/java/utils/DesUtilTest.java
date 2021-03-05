package utils;

import common.utils.DESUtil;
import org.junit.Test;

public class DESUtilTest {

    @Test
    public void test1() throws Exception{
        String source = "DES测试";
        System.out.println("加密前: " + source);
        System.out.println("加密后: " + DESUtil.encrypt(source));
        System.out.println("解密后: " + DESUtil.decrypt(DESUtil.encrypt(source)));

        System.out.println("------------------");

        String key = "A1B2C3D4";
        System.out.println("加密前: " + source);
        System.out.println("加密后: " + DESUtil.encrypt(source, key));
        System.out.println("解密后: " + DESUtil.decrypt(DESUtil.encrypt(source, key), key));
    }
}
