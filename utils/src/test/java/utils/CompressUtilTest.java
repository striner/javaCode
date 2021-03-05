package utils;

import common.utils.CompressUtil;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CompressUtilTest {

    @Test
    public void test1() throws IOException {
        CompressUtil.compressZip("E:\\striner\\测试.txt", "E:\\striner\\测试22.zip");
    }

    @Test
    public void test2() throws IOException {
        CompressUtil.compressZip("E:\\striner\\bbb", "E:\\striner\\aaa\\bbb.zip");
    }

    @Test
    public void bzip2() throws IOException {
        InputStream in = Files.newInputStream(Paths.get("archive.tar"));
        OutputStream fout = Files.newOutputStream(Paths.get("archive.tar.gz"));
        BufferedOutputStream out = new BufferedOutputStream(fout);
        BZip2CompressorOutputStream bzOut = new BZip2CompressorOutputStream(out);
        final byte[] buffer = new byte[2048];
        int n = 0;
        while (-1 != (n = in.read(buffer))) {
            bzOut.write(buffer, 0, n);
        }
        bzOut.close();
        in.close();
    }

    @Test
    public void gzip() throws IOException {
        InputStream in = Files.newInputStream(Paths.get("archive.tar"));
        OutputStream fout = Files.newOutputStream(Paths.get("archive.tar.gz"));
        BufferedOutputStream out = new BufferedOutputStream(fout);
        GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(out);
        final byte[] buffer = new byte[2048];
        int n = 0;
        while (-1 != (n = in.read(buffer))) {
            gzOut.write(buffer, 0, n);
        }
        gzOut.close();
        in.close();
    }
}
