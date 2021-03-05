package common.utils;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import common.enumeration.fileTypeEnum.FileType;
import common.enumeration.errorMessageEnum.ErrorMessage;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.http.util.TextUtils;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.zip.*;

/**
 * 解压缩工具类
 *
 * 该版本可解压的文件格式有：.zip  .rar  .tar  .bz2  .gz  .tar.gz  tar.bz2
 * create by mulin on 2018/09/10
 */
public class DeCompressUtil {

    private static Logger logger = LoggerFactory.getLogger(DeCompressUtil.class);

    /**
     * 获取文件真实类型
     *
     * @param file 要获取类型的文件。
     * @return 文件类型枚举。
     */
    public static FileType getFileType(File file) throws IOException {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] head = new byte[4];
            if (-1 == inputStream.read(head)) {
                return FileType.UNKNOWN;
            }
            int headHex = 0;
            for (byte b : head) {
                headHex <<= 8;
                headHex |= b;
            }
            switch (headHex) {
                case 0x504B0304:
                    return FileType.ZIP;
                case 0x776f7264:
                    return FileType.TAR;
                case -0x51:
                    return FileType._7Z;
                case 0x425a6839:
                    return FileType.BZ2;
                case -0x74f7f8:
                    return FileType.GZ;
                case 0x52617221:
                    return FileType.RAR;
                default:
                    return FileType.UNKNOWN;
            }
        } catch (Exception e) {
            logger.error("DeCompressUtil-getFileType " + ErrorMessage.GET_FILE_TYPE_ERROR.getErrorInfo());
            throw new IOException(ErrorMessage.GET_FILE_TYPE_ERROR.getErrorInfo());
        } finally {
            IOUtil.closeQuietly(inputStream);
        }
    }


    /**
     * 解压缩
     *
     * 该方法不支持tar.gz和tar.bz2格式的解压。
     * 如需解压tar.gz格式的压缩包，请使用unTarGz(String sourcePath, String destDir)方法，
     * 如需解压tar.bz2格式的压缩包，请使用unTarBz2(String sourcePath, String destDir)方法。
     * @param sourcePath 需要解压缩的源压缩文件
     * @param destDir 目标文件地址
     * @throws Exception
     */
    public static void deCompress(String sourcePath, String destDir) throws Exception{
        //保证文件夹路径最后是"/"或者"\"
        char lastChar = destDir.charAt(destDir.length()-1);
        if(lastChar!='/' && lastChar!='\\'){
            destDir += File.separator;
        }

        //根据类型，进行相应的解压缩
        switch (getFileType(new File(sourcePath))) {
            case ZIP:
                unZip(sourcePath, destDir);
                break;
            case RAR:
                unRar(sourcePath, destDir);
                break;
            case TAR:
                unTar(sourcePath, destDir);
                break;
            case BZ2:
                unBz2(sourcePath, destDir);
                break;
            case GZ:
                unGz(sourcePath, destDir);
                break;
            default:
                throw new Exception("该压缩格式不支持解压缩！");
        }
    }


    public static synchronized void unRar(String sourcePath, String destDir) throws Exception {
        decompressRar(sourcePath, destDir);
    }

    public static synchronized void unZip(String sourcePath, String destDir) throws IOException {
        decompressZip(sourcePath, destDir);
    }

    public static synchronized void unTar(String sourcePath, String destDir) throws Exception {
        decompressTar(sourcePath, destDir);
    }

    public static synchronized void unBz2(String sourcePath, String destDir) throws IOException {
        decompressBz2(sourcePath, destDir);
    }

    public static synchronized void unGz(String sourcePath, String destDir) throws IOException {
        decompressGz(sourcePath, destDir);
    }

    public static synchronized void unTarGz(String sourcePath, String destDir) throws Exception {
        decompressTarGz(sourcePath, destDir);
    }

    public static synchronized void unTarBz2(String sourcePath, String destDir) throws IOException {
        decompressTarBz2(sourcePath, destDir);
    }



    /**
     * 参数检查
     *
     * @param srcPath 需要被解压的文件所在原路径
     * @param destDir 需要被存储的目标文件地址
     * @throws FileNotFoundException
     */
    public static void checkParameters(String srcPath, String destDir) throws FileNotFoundException {
        if (TextUtils.isEmpty(srcPath) || TextUtils.isEmpty(destDir)) {
            throw new IllegalArgumentException(ErrorMessage.PATH_OR_DIR_IllRGAL.getErrorInfo());
        }
        File destFolder = new File(destDir);
        if (!destFolder.exists()) {
            if (!destFolder.mkdirs()) {
                throw new FileNotFoundException(ErrorMessage.MKDIRS_FAILED.getErrorInfo());
            }
        }
    }


    /**
     * 构建目录
     *
     * @param outputDir 输出目录
     * @param subDir    子目录
     */
    public static void createDirectory(String outputDir, String subDir) {
        File file = new File(outputDir);
        if (!(subDir == null || subDir.trim().equals(""))) {//子目录不为空
            file = new File(outputDir + File.separator + subDir);
        }
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }
    }



    /**
     * ------------------------- zip文件的解压缩 -------------------------
     */




    /**
     * 解压ZIP文件到指定文件夹
     *
     * @param zipPath 源文件
     * @param destDir 目标文件夹路径
     * @throws IOException
     */
    public static void decompressZip(String zipPath, String destDir) throws IOException {
        checkParameters(zipPath, destDir);

        ZipInputStream zis = null;
        BufferedOutputStream bos = null;
        try {
            File file = new File(zipPath);
            zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null && !ze.isDirectory()) {

                //得到解压文件在当前存储的绝对路径
                String filePath = destDir + File.separator + ze.getName();
                if (ze.isDirectory()) {
                    new File(filePath).mkdirs();
                } else {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = zis.read(buffer)) != -1) {
                        baos.write(buffer, 0, count);
                    }
                    byte[] bytes = baos.toByteArray();
                    File entryFile = new File(filePath);
                    //创建父目录
                    if (!entryFile.getParentFile().exists()) {
                        if (!entryFile.getParentFile().mkdirs()) {
                            throw new FileNotFoundException(ErrorMessage.MKDIRS_FAILED.getErrorInfo());
                        }
                    }
                    //写文件
                    bos = new BufferedOutputStream(new FileOutputStream(entryFile));
                    bos.write(bytes);
                    bos.flush();
                }
            }
        } finally {
            IOUtil.closeQuietly(zis, bos);
        }
    }




    /**
     * ------------------------- rar文件的解压缩 -------------------------
     */

    /**
     * 根据原始rar路径，解压到指定文件夹下.
     *
     * @param rarPath 原始rar路径
     * @param destDir 解压到的文件夹
     */
    public static void decompressRar(String rarPath, String destDir) throws Exception {

        //参数检查
        checkParameters(rarPath, destDir);

        Archive a = null;
        try {
            a = new Archive(new File(rarPath));
            if (a != null) {
                FileHeader fh = a.nextFileHeader();
                while (fh != null) {
                    // 防止文件名中文乱码问题的处理
                    String fileName = fh.getFileNameW().isEmpty() ? fh.getFileNameString() : fh.getFileNameW();
                    if (fh.isDirectory()) { // 文件夹
                        File fol = new File(destDir + File.separator + fileName);
                        fol.mkdirs();
                    } else { // 文件
                        File out = new File(destDir + File.separator + fileName.trim());
                        try {
                            if (!out.exists()) {
                                if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
                                    out.getParentFile().mkdirs();
                                }
                                out.createNewFile();
                            }
                            FileOutputStream os = new FileOutputStream(out);
                            a.extractFile(fh, os);
                            os.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            throw new IOException();
                        }
                    }
                    fh = a.nextFileHeader();
                }
                a.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }



    /**
     * ------------------------- tar文件的解压缩 -------------------------
     */

    /**
     * 解压缩tar文件
     *
     * @param tarPath 压缩包文件
     * @param destDir 目标文件夹
     */
    public static void decompressTar(String tarPath, String destDir) throws IOException {

        //参数检查
        checkParameters(tarPath, destDir);

        FileInputStream fis = null;
        OutputStream fos = null;
        TarInputStream tarInputStream = null;
        try {
            fis = new FileInputStream(new File(tarPath));
            tarInputStream = new TarInputStream(fis, 1024 * 2);
            // 创建输出目录
            createDirectory(destDir, null);

            while (true) {
                TarEntry entry = null;
                entry = tarInputStream.getNextEntry();
                if (entry == null) {
                    break;
                }
                createEntry(destDir, entry, fos, tarInputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        } finally {
            IOUtil.closeQuietly(fis, fos, tarInputStream);
        }
    }


    /**
     * 创建TarEntry
     *
     * @param targetPath     解压路径
     * @param entry          TarEntry
     * @param fos            OutputStream
     * @param tarInputStream TarInputStream
     * @throws IOException
     */
    private static void createEntry(String targetPath, TarEntry entry, OutputStream fos, TarInputStream tarInputStream) throws IOException {
        if (entry.isDirectory()) {
            createDirectory(targetPath, entry.getName()); // 创建子目录
        } else {
            fos = new FileOutputStream(new File(targetPath + File.separator + entry.getName()));
            int count;
            byte data[] = new byte[2048];
            while ((count = tarInputStream.read(data)) != -1) {
                fos.write(data, 0, count);
            }
            fos.flush();
        }
    }


    /**
     * ------------------------- bz2文件的解压缩 -------------------------
     */

    /**
     * 解压缩bz2文件
     *
     * @param bz2Path 压缩包文件
     * @param destDir 目标文件夹
     */
    public static void decompressBz2(String bz2Path, String destDir) throws IOException {
        //参数检查
        checkParameters(bz2Path, destDir);

        FileInputStream fis = null;
        OutputStream fos = null;
        BZip2CompressorInputStream bis = null;
        String suffix = ".bz2";
        try {
            File file = new File(bz2Path);
            fis = new FileInputStream(file);
            bis = new BZip2CompressorInputStream(fis);
            // 创建输出目录
            createDirectory(destDir, null);
            File tempFile = new File(destDir + File.separator + file.getName().replace(suffix, ""));
            fos = new FileOutputStream(tempFile);

            int count;
            byte data[] = new byte[2048];
            while ((count = bis.read(data)) != -1) {
                fos.write(data, 0, count);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        } finally {
            IOUtil.closeQuietly(fis, fos, bis);
        }
    }


    /**
     * ------------------------- tar.bz2文件的解压缩 -------------------------
     */

    /**
     * 解压缩tar.bz2文件
     *
     * @param tarBz2Path 压缩包文件
     * @param destDir 目标文件夹
     */
    public static void decompressTarBz2(String tarBz2Path, String destDir) throws IOException {

        //参数检查
        checkParameters(tarBz2Path, destDir);

        FileInputStream fis = null;
        OutputStream fos = null;
        BZip2CompressorInputStream bis = null;
        TarInputStream tis = null;
        try {
            File file = new File(tarBz2Path);
            fis = new FileInputStream(file);
            bis = new BZip2CompressorInputStream(fis);
            tis = new TarInputStream(bis, 1024 * 2);
            // 创建输出目录
            createDirectory(destDir, null);
            TarEntry entry;
            while ((entry = tis.getNextEntry()) != null) {
                createEntry(destDir, entry, fos, tis);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        } finally {
            IOUtil.closeQuietly(fis, fos, bis, tis);
        }
    }



    /**
     * ------------------------- tar.gz文件的解压缩 -------------------------
     */

    /**
     * 解压缩tar.gz文件
     *
     * @param tarGzPath 压缩包文件
     * @param destDir 目标文件夹
     */
    public static void decompressTarGz(String tarGzPath, String destDir) throws IOException {
        //参数检查
        checkParameters(tarGzPath, destDir);


        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        GZIPInputStream gzipIn = null;
        TarInputStream tarIn = null;
        OutputStream out = null;
        try {
            File file = new File(tarGzPath);
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            gzipIn = new GZIPInputStream(bufferedInputStream);
            tarIn = new TarInputStream(gzipIn, 1024 * 2);

            // 创建输出目录
            createDirectory(destDir, null);

            TarEntry entry = null;
            while ((entry = tarIn.getNextEntry()) != null) {
                if (entry.isDirectory()) { // 是目录
                    createDirectory(destDir, entry.getName()); // 创建子目录
                } else { // 是文件
                    File tempFIle = new File(destDir + File.separator + entry.getName());
                    createDirectory(tempFIle.getParent() + File.separator, null);
                    out = new FileOutputStream(tempFIle);
                    int len = 0;
                    byte[] b = new byte[2048];

                    while ((len = tarIn.read(b)) != -1) {
                        out.write(b, 0, len);
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        } finally {
            IOUtil.closeQuietly(out, tarIn, gzipIn, bufferedInputStream, fileInputStream);
        }
    }



    /**
     * ------------------------- gz文件的解压缩 -------------------------
     */

    /**
     * 解压缩gz文件
     *
     * @param gzPath       压缩包文件
     * @param destDir 目标文件夹
     */
    public static void decompressGz(String gzPath, String destDir) throws IOException {
        //参数检查
        checkParameters(gzPath, destDir);

        FileInputStream fileInputStream = null;
        GZIPInputStream gzipIn = null;
        OutputStream out = null;
        String suffix = ".gz";
        try {
            File file = new File(gzPath);
            fileInputStream = new FileInputStream(file);
            gzipIn = new GZIPInputStream(fileInputStream);
            // 创建输出目录
            createDirectory(destDir, null);

            File tempFile = new File(destDir + File.separator + file.getName().replace(suffix, ""));
            out = new FileOutputStream(tempFile);
            int count;
            byte data[] = new byte[2048];
            while ((count = gzipIn.read(data)) != -1) {
                out.write(data, 0, count);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        } finally {
            IOUtil.closeQuietly(out, gzipIn, fileInputStream);
        }
    }
}