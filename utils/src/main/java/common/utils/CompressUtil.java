package common.utils;

import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.http.client.entity.GzipCompressingEntity;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩工具类
 *
 * 该版本只支持.zip格式的压缩。
 * Create by mulin on 2018/12/14.
 */
public class CompressUtil {
    /**
     * 文件压缩成ZIP文件
     *
     * @param zipPath  源文件的绝对路径，可以为文件或文件夹
     * @param destPath 目标文件的绝对路径
     * @throws Exception 解压失败
     */
    public static void compressZip(String zipPath, String destPath) throws IOException {
        CheckedOutputStream cos = null;
        ZipOutputStream zos = null;
        try {
            File srcFile = new File(zipPath);
            // 对目标文件做CRC32校验，确保压缩后的zip包含CRC32值
            cos = new CheckedOutputStream(new FileOutputStream(destPath), new CRC32());
            //装饰一层ZipOutputStream，使用zos写入的数据会被压缩
            zos = new ZipOutputStream(cos);
            zos.setLevel(9);//设置压缩级别 0-9,0表示不压缩，1表示压缩速度最快，9表示压缩后文件最小；默认为6，速率和空间上得到平衡。
            if (srcFile.isFile()) {
                compressZipFile("", srcFile, zos);
            } else if (srcFile.isDirectory()) {
                compressFolder("", srcFile, zos);
            }
        } finally {
            IOUtil.closeQuietly(zos);
        }
    }

    /**
     * 压缩文件夹
     *
     * @param prefix
     * @param srcFolder
     * @param zos
     * @throws IOException
     */
    private static void compressFolder(String prefix, File srcFolder, ZipOutputStream zos) throws IOException {
        String new_prefix = prefix + srcFolder.getName() + "/";
        File[] files = srcFolder.listFiles();
        //支持空文件夹
        if (files.length == 0) {
            compressZipFile(prefix, srcFolder, zos);
        } else {
            for (File file : files) {
                if (file.isFile()) {
                    compressZipFile(new_prefix, file, zos);
                } else if (file.isDirectory()) {
                    compressFolder(new_prefix, file, zos);
                }
            }
        }
    }

    /**
     * 压缩文件和空目录
     *
     * @param prefix
     * @param src
     * @param zos
     * @throws IOException
     */
    private static void compressZipFile(String prefix, File src, ZipOutputStream zos) throws IOException {
        //若是文件,那肯定是对单个文件压缩
        //ZipOutputStream在写入流之前，需要设置一个zipEntry
        //注意这里传入参数为文件在zip压缩包中的路径，所以只需要传入文件名即可
        String relativePath = prefix + src.getName();
        if (src.isDirectory()) relativePath += "/"; //处理空文件夹
        ZipEntry entry = new ZipEntry(relativePath);
        //写到这个zipEntry中，可以理解为一个压缩文件
        zos.putNextEntry(entry);
        InputStream is = null;
        try {
            if (src.isFile()) {
                is = new FileInputStream(src);
                byte[] buffer = new byte[1024 << 3];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    zos.write(buffer, 0, len);
                }
            }
            //该文件写入结束
            zos.closeEntry();
        } finally {
            IOUtil.closeQuietly(is);
        }
    }
}
