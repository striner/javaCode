package utils;

import common.utils.MD5Util;
import org.junit.Test;

public class MD5UtilTest {
    @Test
    public void test1() {
        System.out.println(MD5Util.getMD5Str("MD5测试"));
        System.out.println(MD5Util.makeMd5Sum(new byte[]{1, 11, 36, 2}));
        System.out.println(MD5Util.bytes2Hex(new byte[]{1, 11, 127, 36, 2}));
    }
}
