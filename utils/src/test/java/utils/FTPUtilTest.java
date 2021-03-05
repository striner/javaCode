package utils;

import common.utils.FTPUtil;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;
import java.io.*;
import java.util.List;
import java.io.IOException;


public class FTPUtilTest {

    @Test
    public void test() throws Exception {
        FTPUtil ftpUtil = new FTPUtil("UTF-8");
        ftpUtil.connect("192.168.1.198", 21, "striner", "murong123456");
        ftpUtil.upload("/home/striner/test1.txt", new File("E:/striner/test1.txt"));
        ftpUtil.download("/home/striner/test1.txt", new File("E:/striner/test11.txt"));
        ftpUtil.uploadDir("/home/striner/ccc", "E:/striner");
        ftpUtil.downloadDir("/home/striner/ccc", "E:/striner/aaa");
        ftpUtil.disconnect();
    }

    @Test
    public void test2() throws IOException{
        FTPUtil ftpUtil = new FTPUtil("UTF-8");
        ftpUtil.connect("192.168.1.198", 21, "striner", "murong123456");

        List<FTPFile>  files = ftpUtil.listFiles("/home/striner");
        for (FTPFile file : files) {
            System.out.println(file.getName());
        }

        ftpUtil.disconnect();
    }

    @Test
    public void test3() throws IOException {
        FTPUtil ftpUtil = new FTPUtil("UTF-8");
        ftpUtil.connect("192.168.1.198", 21, "striner", "murong123456");

        ByteArrayInputStream in = new ByteArrayInputStream("FTP测试".getBytes());
        ftpUtil.storeFile("/home/striner/xyz.txt", in);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ftpUtil.retrieveFile("/home/striner/xyz.txt", bos);
        String contentStr = new String(bos.toByteArray(),"utf8");
        System.out.println(contentStr);
        ftpUtil.disconnect();
    }
}
