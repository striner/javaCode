package common.utils;

import common.enumeration.errorMessageEnum.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Closeable;
import java.io.IOException;

/**
 * Create by mulin on 2018/12/13.
 */
public class IOUtil {

    private static Logger logger = LoggerFactory.getLogger(IOUtil.class);


    /**
     * 关闭一个或多个流对象
     *
     * @param closeables 可关闭的流对象列表
     * @throws IOException
     */
    public static void close(Closeable... closeables) throws IOException {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        }
    }


    /**
     * 关闭一个或多个流对象
     *
     * @param closeables 可关闭的流对象列表
     * @throws IOException
     */
    public static void closeQuietly(Closeable... closeables) throws IOException {
        try {
            close(closeables);
        } catch (IOException e) {
            logger.error(ErrorMessage.IOFILE_CLOSE_ERROR.getErrorInfo());
            throw new IOException(ErrorMessage.IOFILE_CLOSE_ERROR.getErrorInfo());
        }
    }
}
