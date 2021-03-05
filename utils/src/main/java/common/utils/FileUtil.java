/**
 * @author striner
 * @create 2018/12/1 21:40
 */
package common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.http.HttpServletResponse;

/**
 * 封装一些基本的文件操作
 *
 * Created by mulin on 2018/12/02..
 */
public class FileUtil {

    public static void writeFile(String fileName, String content) {
        writeFile(fileName, content, "utf-8");
    }

    public static void writeFile(String fileName, String content, String charset) {
        try {
            createFolder(fileName, true);   //若路径不存在会报错（FileNotFoundException），故需先创建文件路径
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), charset));

            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String fileName, InputStream is) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        byte[] bs = new byte[512];
        int n = 0;
        while ((n = is.read(bs)) != -1) {
            fos.write(bs, 0, n);
        }
        is.close();
        fos.close();
    }

    public static String readFile(String fileName) {
        try {
            File file = new File(fileName);
            String charset = getCharset(file);
            StringBuffer sb = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charset));
            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            in.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isExistFileDir(String dir) {
        boolean isExist = false;
        File fileDir = new File(dir);
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            if ((files != null) && (files.length != 0)) {
                isExist = true;
            }
        }
        return isExist;
    }

    public static String getCharset(File file) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1)
                return charset;
            if ((first3Bytes[0] == -1) && (first3Bytes[1] == -2)) {
                charset = "UTF-16LE";
                checked = true;
            } else if ((first3Bytes[0] == -2) && (first3Bytes[1] == -1)) {
                charset = "UTF-16BE";
                checked = true;
            } else if ((first3Bytes[0] == -17) && (first3Bytes[1] == -69) && (first3Bytes[2] == -65)) {
                charset = "UTF-8";
                checked = true;
            }
            bis.reset();

            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read < 240) {
                        if ((128 > read) || (read > 191)) {
                            if ((192 <= read) && (read <= 223)) {
                                read = bis.read();
                                if ((128 > read) || (read > 191)) {
                                    break;
                                }

                            } else if ((224 <= read) && (read <= 239)) {
                                read = bis.read();
                                if ((128 <= read) && (read <= 191)) {
                                    read = bis.read();
                                    if ((128 <= read) && (read <= 191)) {
                                        charset = "UTF-8";
                                    }
                                }
                            }
                        }
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;
    }

    public static byte[] readByte(InputStream is) {
        try {
            byte[] r = new byte[is.available()];
            is.read(r);
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] readByte(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            byte[] r = new byte[fis.available()];
            fis.read(r);
            fis.close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean writeByte(String fileName, byte[] b) {
        try {
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(fileName));
            fos.write(b);
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteFile(String path) {
        return deleteFile(new File(path));
    }

    public static boolean deleteFile(File file) {
        return file.delete();
    }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 将对象序列化到文档中
     *
     * @param obj
     * @param fileName
     */
    public static void serializeToFile(Object obj, String fileName) {
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(obj);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文档中的数据反序列化成对象
     *
     * @param fileName
     * @return
     */
    public static Object deserializeFromFile(String fileName) {
        try {
            File file = new File(fileName);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            Object obj = in.readObject();
            in.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String inputStream2String(InputStream input, String charset) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(input, charset));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line + "\n");
        }
        return buffer.toString();
    }

    public static String inputStream2String(InputStream input) throws IOException {
        return inputStream2String(input, "utf-8");
    }

    public static File[] getFiles(String path) {
        File file = new File(path);
        return file.listFiles();
    }

    public static void createFolderFile(String path) {
        createFolder(path, true);
    }

    public static void createFolder(String path, boolean isFile) {
        if (isFile) {
            path = path.substring(0, path.lastIndexOf(File.separator));
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void createFolder(String pathName) {
        File dir = new File(pathName);
        dir.mkdir();
    }

    public static void renameFolder(String path, String newName) {
        File file = new File(path);
        if (file.exists()) {
            file.renameTo(new File(newName));
        }
    }

    /**
     * 仅获取该路径下的所有文件夹
     *
     * @param dir
     * @return
     */
    public static ArrayList<File> getDiretoryOnly(File dir) {
        ArrayList dirs = new ArrayList();
        if ((dir != null) && (dir.exists()) && (dir.isDirectory())) {
            File[] files = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isDirectory()) {
                        return true;
                    }
                    return false;
                }
            });
            for (int i = 0; i < files.length; i++) {
                dirs.add(files[i]);
            }
        }
        return dirs;
    }

    /**
     * 仅获取该路径下的所有文件
     *
     * @param dir
     * @return
     */
    public static ArrayList<File> getFileOnly(File dir) {
        ArrayList dirs = new ArrayList();
        File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (file.isFile()) {
                    return true;
                }
                return false;
            }
        });
        for (int i = 0; i < files.length; i++) {
            dirs.add(files[i]);
        }
        return dirs;
    }

    public static boolean copyFile(String from, String to) {
        File fromFile = new File(from);
        File toFile = new File(to);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(fromFile);
            fos = new FileOutputStream(toFile);
            byte[] buf = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buf)) != -1) {
                fos.write(buf, 0, bytesRead);
            }
            fos.flush();
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 文件备份
     *
     * @param filePath
     */
    public static void backupFile(String filePath) {
        String backupName = filePath + ".bak";
        File file = new File(backupName);
        if (file.exists()) {
            file.delete();
        }
        copyFile(filePath, backupName);
    }

    /**
     * 获得文件后缀名
     *
     * @param file
     * @return
     */
    public static String getFileExt(File file) {
        if (file.isFile()) {
            return getFileExt(file.getName());
        }
        return "";
    }

    public static String getFileExt(String fileName) {
        int pos = fileName.lastIndexOf(".");
        if (pos > -1) {
            return fileName.substring(pos + 1).toLowerCase();
        }
        return "";
    }

    public static void copyDir(String fromDir, String toDir) {
        new File(toDir).mkdirs();
        File[] file = new File(fromDir).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                String fromName = file[i].getAbsolutePath();
                String toFile = toDir + "/" + file[i].getName();
                copyFile(fromName, toFile);
            }
            if (file[i].isDirectory())
                copyDir(fromDir + "/" + file[i].getName(), toDir + "/" + file[i].getName());
        }
    }

    public static String getFileSize(File file) throws IOException {
        if (file.isFile()) {
            FileInputStream fis = new FileInputStream(file);
            int size = fis.available();
            fis.close();
            return getSize(size);
        }
        return "";
    }

    public static String getSize(double size) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (size > 1048576.0D) {
            double ss = size / 1048576.0D;
            return df.format(ss) + " M";
        }
        if (size > 1024.0D) {
            double ss = size / 1024.0D;
            return df.format(ss) + " KB";
        }
        return size + " bytes";
    }

    public static void downLoadFile(HttpServletResponse response, String fullPath, String fileName) throws IOException {
        OutputStream outp = response.getOutputStream();
        File file = new File(fullPath);
        if (file.exists()) {
            response.setContentType("APPLICATION/OCTET-STREAM");
            String fileDisplay = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileDisplay);
            FileInputStream in = null;
            try {
                outp = response.getOutputStream();
                in = new FileInputStream(fullPath);
                byte[] b = new byte[1024];
                int i = 0;
                while ((i = in.read(b)) > 0) {
                    outp.write(b, 0, i);
                }
                outp.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (outp != null) {
                    outp.close();
                    outp = null;
                    response.flushBuffer();
                }
            }
        } else {
            outp.write("文件不存在!".getBytes("utf-8"));
        }
    }

    public static String getParentDir(String baseDir, String currentFile) {
        File f = new File(currentFile);
        String parentPath = f.getParent();
        String path = parentPath.replace(baseDir, "");
        return path.replace(File.separator, "/");
    }

    public static String readFromProperties(String fileName, String key) {
        String value = "";
        InputStream stream = null;
        try {
            stream = new BufferedInputStream(new FileInputStream(fileName));
            Properties prop = new Properties();
            prop.load(stream);
            value = prop.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public static boolean saveProperties(String fileName, String key, String value) {
        StringBuffer sb = new StringBuffer();
        boolean isFound = false;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
            String str;
            while ((str = in.readLine()) != null) {
                if (str.startsWith(key)) {
                    sb.append(key + "=" + value + "\r\n");
                    isFound = true;
                } else {
                    sb.append(str + "\r\n");
                }
            }

            if (!isFound) {
                sb.append(key + "=" + value + "\r\n");
            }
            writeFile(fileName, sb.toString(), "utf-8");
            return true;
        } catch (Exception ex) {
            boolean bool1;
            ex.printStackTrace();
            return false;
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static String getMd5ByFile(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }


    /**
     * 将文件保存到硬盘指定目录下
     *
     * @param in
     * @param filePath
     * @throws IOException
     */
    public static void saveToDisk(InputStream in, String filePath) throws IOException {

        File file = new File(filePath);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fos.flush();
        fos.close();

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

