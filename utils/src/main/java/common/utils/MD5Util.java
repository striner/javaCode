package common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static final ThreadLocal<MessageDigest> MESSAGE_DIGEST_CACHE = new ThreadLocal<MessageDigest>();
    private static char md5Chars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 获取一个字符串的md5码
     *
     * @param str 源串
     * @return md5码
     */
    public static String getMD5Str(String str) {
        try {
            MessageDigest messagedigest = getMessageDigest();
            messagedigest.update(str.getBytes("UTF-8"));
            return bufferToHex(messagedigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static MessageDigest getMessageDigest() throws NoSuchAlgorithmException {
        MessageDigest messagedigest = MESSAGE_DIGEST_CACHE.get();
        if (messagedigest == null) {
            messagedigest = MessageDigest.getInstance("MD5");
            MESSAGE_DIGEST_CACHE.set(messagedigest);
        }
        return messagedigest;
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = md5Chars[(bt & 0xf0) >> 4];
        char c1 = md5Chars[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    /**
     * 32位小写MD5
     */
    public static String parseStrToMd5L32(String str) {
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(Integer.toHexString(bt));
            }
            reStr = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }

    /**
     * 32位大写MD5
     */
    public static String parseStrToMd5U32(String str) {
        String reStr = parseStrToMd5L32(str);
        if (reStr != null) {
            reStr = reStr.toUpperCase();
        }
        return reStr;
    }

    /**
     * 16位小写MD5
     */
    public static String parseStrToMd5U16(String str) {
        String reStr = parseStrToMd5L32(str);
        if (reStr != null) {
            reStr = reStr.toUpperCase().substring(8, 24);
        }
        return reStr;
    }

    /**
     * 16位大写MD5
     */
    public static String parseStrToMd5L16(String str) {
        String reStr = parseStrToMd5L32(str);
        if (reStr != null) {
            reStr = reStr.substring(8, 24);
        }
        return reStr;
    }

    public static String makeMd5Sum(byte[] srcContent) {
        if (srcContent == null) {
            return null;
        }

        String strDes = null;

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(srcContent);
            // to HexString
            strDes = bytes2Hex(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] byteArray) {
        StringBuilder strBuf = new StringBuilder();
        for (byte aByteArray : byteArray) {
            if (aByteArray >= 0 && aByteArray < 16) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(aByteArray & 0xFF));
        }
        return strBuf.toString();
    }

}
