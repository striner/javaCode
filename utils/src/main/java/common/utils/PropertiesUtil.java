package common.utils;

import common.enumeration.errorMessageEnum.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 读取properties文件
     *
     * @param file 配置文件所在路径
     * @throws IOException
     */
    public static Properties fromFile(String file) throws IOException {
        Properties properties = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            properties.load(is);
        } catch (IOException e) {
            logger.error(ErrorMessage.GET_PROPERTIES_FILE_FAILED.getErrorInfo());
            throw new IOException(ErrorMessage.GET_PROPERTIES_FILE_FAILED.getErrorInfo());
        } finally {
            IOUtil.closeQuietly(is);
        }
        return properties;
    }


    /**
     * 读取properties文件
     *
     * 先在classpath根路径下找，找不到再在当前类的同目录路径下找。
     * @param file 文件路径
     * @throws IOException
     */
    public static Properties fromPath(String file) throws IOException{
        Properties properties =  new Properties();
        try {
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
            if (stream == null) {
                stream = PropertiesUtil.class.getResourceAsStream(file);
            }
            properties.load(stream);
        } catch (IOException e) {
            logger.error(ErrorMessage.GET_PROPERTIES_FILE_FAILED.getErrorInfo());
            throw new IOException(ErrorMessage.GET_PROPERTIES_FILE_FAILED.getErrorInfo());
        }
        return properties;
    }

    /**
     * 根据指定路径获取properties, 带编码, 防止中文乱码
     *
     * @param path 配置文件所在路径
     * @param charset 编码格式
     * @throws IOException
     */
    public static Properties getPropertiesByPathAndCharset(String path, String charset) throws IOException{
        Properties properties = new Properties();
        Reader reader = null;
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            reader = new InputStreamReader(is, Charset.forName(charset));
            properties.load(reader);
        } catch (IOException e) {
            logger.error(ErrorMessage.GET_PROPERTIES_FILE_FAILED.getErrorInfo());
            throw new IOException(ErrorMessage.GET_PROPERTIES_FILE_FAILED.getErrorInfo());
        } finally {
            IOUtil.closeQuietly(is, reader);
        }
        return properties;
    }


    /**
     * 将流转换为properties
     *
     * @param stream InputStream
     * @return Properties
     * @throws IOException
     */
    public static Properties fromStream(InputStream stream) throws IOException {
        Properties dest = new Properties();
        Properties src = new Properties();
        src.load(stream);

        // 如果key value为字符串，需要trim一下
        for (Map.Entry<Object, Object> entry : src.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();

            Object newKey = key;
            Object newValue = value;
            if (newKey instanceof String) {
                newKey = key.toString().trim();
            }

            if (newValue instanceof String) {
                newValue = value.toString().trim();
            }
            dest.put(newKey, newValue);
        }
        return dest;
    }


    /**
     * 获取配置文件的属性值
     *
     * @param configPath 文件路径
     * @param key
     * @return String
     * @throws IOException
     */
    public static String fromFile(String configPath, String key) throws IOException{
        Properties pro = PropertiesUtil.fromFile(configPath);
        return pro.getProperty(key);
    }


    /**
     * 获取配置文件的多个属性值
     *
     * @param configPath 文件路径
     * @param keys
     * @return List<String>
     * @throws IOException
     */
    public static List<String> fromFile(String configPath, String... keys) throws IOException{
        Properties pro = PropertiesUtil.fromFile(configPath);
        List<String> list = new ArrayList<>();
        for(String key : keys) {
            list.add(pro.getProperty(key));
        }
        return list;
    }


    /**
     * 获取配置文件的属性值
     *
     * @param configPath 文件路径
     * @param key
     * @return String
     * @throws IOException
     */
    public static String fromPath(String configPath, String key) throws IOException{
        Properties pro = PropertiesUtil.fromPath(configPath);
        return pro.getProperty(key);
    }


    /**
     * 获取配置文件的多个属性值
     *
     * @param configPath 文件路径
     * @param keys
     * @return List<String>
     * @throws IOException
     */
    public static List<String> fromPath(String configPath, String... keys) throws IOException{
        Properties pro = PropertiesUtil.fromPath(configPath);
        List<String> list = new ArrayList<>();
        for(String key : keys) {
            list.add(pro.getProperty(key));
        }
        return list;
    }
}
