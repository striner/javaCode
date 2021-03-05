package utils;

import common.utils.FileUtil;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class FileUtilTest{

    @Test
    public void test1() {
        System.out.println(FileUtil.isExistFileDir("e:\\striner"));
        System.out.println(FileUtil.getCharset(new File("e:\\striner\\test1.txt")));
        System.out.println(FileUtil.readFile("e:\\striner\\test1.txt"));
    }

    @Test
    public void test2() {
        System.out.println(FileUtil.deleteFile("e:\\striner\\aaa"));
        System.out.println(FileUtil.deleteDir(new File("e:\\striner\\aaa")));
    }

    @Test
    public void test3() {
        FileUtil.writeFile("e:\\striner\\test\\test1.txt", "测试用");
    }

    @Test
    public void test4() {
        A a = new A();
        FileUtil.serializeToFile(a, "a.txt");
        System.out.println(FileUtil.deserializeFromFile("a.txt"));
    }

    @Test
    public void test5() {
        for (byte b: FileUtil.readByte("e:\\striner\\test11.txt")) {
            System.out.print(b + " ");
        }
        System.out.println();

        byte[] i= new byte[]{'1', '2', '3'};
        System.out.println(FileUtil.writeByte("e:\\striner\\test11.txt", i));
        System.out.println(i.length);
        for (byte b: FileUtil.readByte("e:\\striner\\test11.txt")) {
            System.out.print(b + " ");
        }
    }

    @Test
    public void test6() throws IOException {
        FileInputStream fis = new FileInputStream(new File("e:\\striner\\test11.txt"));
        System.out.println(FileUtil.inputStream2String(fis));
    }

    @Test
    public void test7() {
        for (File file : FileUtil.getFiles("e:\\striner")) {
            System.out.println(file);
        }
    }

    @Test
    public void test8() {
        FileUtil.createFolder("e:\\striner\\folder2");
        FileUtil.createFolder("e:\\striner\\folder2\\test", false);
    }

    @Test
    public void test9() {
        for(File file : FileUtil.getDiretoryOnly(new File("e:\\striner"))) {
            System.out.println(file);
        }
        System.out.println(" ---- ");
        for(File file : FileUtil.getFileOnly(new File("e:\\striner"))) {
            System.out.println(file);
        }
    }

    @Test
    public void test10() throws IOException{
        System.out.println(FileUtil.getFileExt("e:\\striner\\aaa\\ccc\\xxx.txt"));
        System.out.println(FileUtil.getFileExt(new File("e:\\striner\\aaa\\ccc\\xxx.txt")));
        System.out.println(FileUtil.getFileSize(new File("e:\\striner\\aaa\\bbb.txt")));
        System.out.println(FileUtil.getParentDir("", "e:\\striner\\aaa\\ccc\\xxx.txt"));
        System.out.println(FileUtil.readFromProperties("D:\\git\\codes\\javaCode\\utils\\src\\main\\resources\\log4j.properties", "log4j.appender.file"));
        System.out.println(FileUtil.getMd5ByFile(new File("e:\\striner\\aaa\\bbb.txt")));
    }

    @Test
    public void test11() throws IOException{
        FileUtil.copyDir("e:\\striner\\aaa", "e:\\striner\\bbb");
        FileUtil.saveProperties("D:\\git\\codes\\javaCode\\utils\\src\\main\\resources\\log4j.properties", "test", "test");
        FileUtil.saveToDisk(new FileInputStream(new File("e:\\striner\\aaa\\bbb.txt")), "e:\\striner\\test1.txt");
    }
}

class A implements Serializable {

    private static final long serialVersionUID = 1L;

    public String n = "test";

    public A(String n) {
        super();
        this.n = n;
    }

    public A() {
        super();
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "A [n=" + n + "]";
    }
}