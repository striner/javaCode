package utils;

import common.utils.DeCompressUtil;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class DeCompressUtilTest {

    @Test
    public void test1() throws IOException {
        System.out.println(DeCompressUtil.getFileType(new File("E:\\striner\\test1.rar")));
        System.out.println(DeCompressUtil.getFileType(new File("E:\\striner\\test1.zip")));
        System.out.println(DeCompressUtil.getFileType(new File("E:\\striner\\test1.txt")));
        System.out.println(DeCompressUtil.getFileType(new File("E:\\striner\\test1")));
    }

    @Test
    public void test2() throws Exception {
        DeCompressUtil.deCompress("E:\\striner\\测试22.zip", "E:\\striner\\folder2");
        DeCompressUtil.deCompress("E:\\striner\\bbb.rar", "E:\\striner\\folder2\\rartest");
    }

    @Test
    public void test3() throws Exception {
        DeCompressUtil.decompressRar("D:\\QQ\\FileRecv\\kk.rar", "D:\\QQ\\FileRecv");
        DeCompressUtil.unTarBz2("E:\\striner\\folder2\\glibc-2.4.tar.bz2", "E:\\striner\\folder2");
    }
}
