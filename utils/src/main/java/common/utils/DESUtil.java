package common.utils;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * DES加密。
 * create by mulin on 2018/11/28.
 *
 * 注：密钥必须为8位。默认密钥为A1B4C2D3。
 */
public class DESUtil {
    public static String key = "A1B4C2D3";
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    private static SecretKey keyGenerator(String keyStr) throws Exception {
        DESKeySpec desKey = new DESKeySpec(keyStr.getBytes("UTF-8")); //DESKeySpec
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        return keyFactory.generateSecret(desKey);
    }

    /**
     * 加密
     *
     * @param data
     * @param key 长度必须为8位
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        Key deskey = keyGenerator(key);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, iv);
        byte[] results = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.encodeBase64String(results);
    }

    /**
     * 加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception {
        return encrypt(data, key);
    }

    /**
     * 解密
     *
     * @param data
     * @param key 密钥长度为8位
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws Exception {
        Key deskey = keyGenerator(key);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, deskey, iv);
        return new String(cipher.doFinal(Base64.decodeBase64(data)));
    }


    /**
     * 解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decrypt(String data) throws Exception {
        return decrypt(data, key);
    }
}