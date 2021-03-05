package common.utils;

import common.enumeration.errorMessageEnum.ErrorMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import javax.crypto.Cipher;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;

/**
 * RSAUtil.
 *
 * 注意：
 *  1.RSA是一种非对称加密算法，加密算法复杂，速度慢，故不适合用来加密大量数据。
 *  2.可用于公钥加密，私钥解密，或私钥加密，公钥验证。
 *  3.一般用来做签名校验和加密密钥（通过RSA加密AES的密钥，传输给接收方，接收方再解密得到AES密钥。双方用DES对传输的大量数据进行加解密。）
 *  4.为了数据安全着想，每次获取的默认密钥（未给定密钥参数生成的密钥）都是不一样的。
 * Create by mulin on 2018/12/21.
 */
public class RSAUtil {
    private static Logger logger = Logger.getLogger(RSAUtil.class);

    public static final String SIGN_ALGORITHMS = "RSA";
    private static final int KEY_SIZE = 1024;



    /*
         Get keys. -------------------------------
     */

    /**
     * 生成并返回RSA密钥对。
     *
     * @return KeyPair
     */
    public static synchronized KeyPair generateKeyPair() {
        KeyPairGenerator keyPairGen = null;
        KeyPair oneKeyPair = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance(SIGN_ALGORITHMS);
            SecureRandom random = new SecureRandom();
            random.setSeed(("" + System.currentTimeMillis() * Math.random() * Math.random()).getBytes(Charset.forName("UTF-8")));
            keyPairGen.initialize(KEY_SIZE, random);
            oneKeyPair = keyPairGen.generateKeyPair();
            return oneKeyPair;
        } catch (InvalidParameterException ex) {
            logger.error("KeyPairGenerator does not support a key length of " + KEY_SIZE + ".");
            throw new InvalidParameterException();
        } catch (NullPointerException ex) {
            logger.error(ErrorMessage.KEY_PAIR_GEN_IS_NULL.getErrorInfo());
            throw new NullPointerException();
        } catch (NoSuchAlgorithmException ne) {
            logger.error(ErrorMessage.NO_SUCH_ALGORITHM.getErrorInfo() + "KeyPair return null. | Algorithm:" + SIGN_ALGORITHMS);
            return null;
        }
    }


    /**
     * 获取RSA密钥对
     *
     * @return String[] 十六进制字符串数组
     */
    public static String[] getRSAKeyPair() throws Exception {
        KeyPair rsaKeyPair = null;
        try {
            logger.info("Generating a pair of RSAUtil key ... ");
            rsaKeyPair = generateKeyPair();
            PublicKey rsaPublic = rsaKeyPair.getPublic();
            PrivateKey rsaPrivate = rsaKeyPair.getPrivate();

            String[] privateAndPublic = new String[2];
            privateAndPublic[0] = RadixUtil.binaryBytesToHexString(rsaPrivate.getEncoded());
            privateAndPublic[1] = RadixUtil.binaryBytesToHexString(rsaPublic.getEncoded());
            logger.info(KEY_SIZE + "-bit RSAUtil key generated..");

            return privateAndPublic;
        } catch (Exception e) {
            logger.error(ErrorMessage.GET_RSAKEYPAIR_FAILED.getErrorInfo() + e.getMessage(), e);
            throw new Exception();
        }
    }


    /**
     * 获取公钥
     *
     * @return RSAPublicKey
     */
    public static RSAPublicKey getDefaultPublicKey() {
        KeyPair keyPair = generateKeyPair();
        if (keyPair != null) {
            return (RSAPublicKey) keyPair.getPublic();
        }
        return null;
    }


    /**
     * 获取私钥
     *
     * @return RSAPrivateKey
     */
    public static RSAPrivateKey getDefaultPrivateKey() {
        KeyPair keyPair = generateKeyPair();
        if (keyPair != null) {
            return (RSAPrivateKey) keyPair.getPrivate();
        }
        return null;
    }


    /**
     * 根据给定的系数和专用指数构造一个RSA专用的公钥对象。
     *
     * @param modulus        系数。
     * @param publicExponent 专用指数。
     * @return RSA专用公钥对象。
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) {
        KeyFactory keyFactory = null;
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        try {
            keyFactory = KeyFactory.getInstance(SIGN_ALGORITHMS);
            return (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException ex) {
            logger.error(ErrorMessage.INVALID_PARAMETER.getErrorInfo());
            throw new InvalidParameterException();
        } catch (NullPointerException ex) {
            logger.error(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
            throw new NullPointerException();
        } catch (NoSuchAlgorithmException ne) {

            logger.error(ErrorMessage.NO_SUCH_ALGORITHM.getErrorInfo() + "KeyPair return null. | Algorithm:" + SIGN_ALGORITHMS);
            return null;
        }
    }

    /**
     * 根据给定的系数和专用指数构造一个RSA专用的私钥对象。
     *
     * @param modulus         系数。
     * @param privateExponent 专用指数。
     * @return RSA专用私钥对象。
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) {
        KeyFactory keyFactory = null;
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        try {
            keyFactory = KeyFactory.getInstance(SIGN_ALGORITHMS);
            return (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
        } catch (InvalidKeySpecException ex) {
            logger.error(ErrorMessage.INVALID_PARAMETER.getErrorInfo());
            throw new InvalidParameterException();
        } catch (NullPointerException ex) {
            logger.error(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
            throw new NullPointerException();
        } catch (NoSuchAlgorithmException ne) {
            logger.error(ErrorMessage.NO_SUCH_ALGORITHM.getErrorInfo() + "KeyPair return null. | Algorithm:" + SIGN_ALGORITHMS);
            return null;
        }
    }


    /**
     * 根据给定的16进制系数和专用指数字符串构造一个RSA专用的私钥对象。
     *
     * @param hexModulus         系数。
     * @param hexPrivateExponent 专用指数。
     * @return RSA专用私钥对象。
     */
    public static RSAPrivateKey getRSAPrivateKey(String hexModulus, String hexPrivateExponent) throws Exception {
        if (StringUtil.isEmpty(hexModulus) || StringUtil.isEmpty(hexPrivateExponent)) {
            logger.error(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
            throw new NullPointerException();
        }

        byte[] modulus = null;
        byte[] privateExponent = null;
        try {
            modulus = RadixUtil.hexStringToBytes(hexModulus);
            privateExponent = RadixUtil.hexStringToBytes(hexPrivateExponent);
        } catch (Exception ex) {
            logger.error(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
            throw new Exception();
        }
        if (modulus != null && privateExponent != null) {
            return generateRSAPrivateKey(modulus, privateExponent);
        }
        return null;
    }


    /**
     * 根据给定的16进制系数和专用指数字符串构造一个RSA专用的公钥对象。
     *
     * @param hexModulus        系数。
     * @param hexPublicExponent 专用指数。
     * @return RSA专用公钥对象。
     */
    public static RSAPublicKey getRSAPublicKey(String hexModulus, String hexPublicExponent) {
        if (StringUtil.isEmpty(hexModulus) || StringUtil.isEmpty(hexPublicExponent)) {
            logger.error(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
            throw new NullPointerException();
        }

        byte[] modulus = null;
        byte[] publicExponent = null;
        try {
            modulus = RadixUtil.hexStringToBytes(hexModulus);
            publicExponent = RadixUtil.hexStringToBytes(hexPublicExponent);
        } catch (IllegalArgumentException ex) {
            logger.error(ErrorMessage.FORMAT_INCORRECT_IllEGAL.getErrorInfo());
            throw new IllegalArgumentException();
        }

        if (modulus != null && publicExponent != null) {
            return generateRSAPublicKey(modulus, publicExponent);
        }
        return null;
    }



    /*
         Encrypt or decrypt. -------------------------------
     */

    /**
     * 使用指定的公钥加密数据。
     *
     * @param publicKey 给定的公钥。
     * @param data      要加密的数据。
     * @return 加密后的数据。
     */

    public static byte[] encrypt(PublicKey publicKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(SIGN_ALGORITHMS);
        ci.init(Cipher.ENCRYPT_MODE, publicKey);
        return ci.doFinal(data);
    }


    /**
     * 使用指定的私钥解密数据。
     *
     * @param privateKey 给定的私钥。
     * @param data       要解密的数据。
     * @return 原数据。
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(SIGN_ALGORITHMS);
        ci.init(Cipher.DECRYPT_MODE, privateKey);
        return ci.doFinal(data);
    }


    /**
     * 使用给定的公钥加密给定的字符串。
     *
     * @param publicKey 给定的公钥。
     * @param plaintext 字符串。
     * @return 给定字符串的密文。
     */
    public static String encryptString(PublicKey publicKey, String plaintext) throws Exception {
        if (publicKey == null || plaintext == null) {
            logger.error(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
            throw new NullPointerException();
        }
        byte[] data = plaintext.getBytes();
        try {
            byte[] en_data = encrypt(publicKey, data);
            return new String(RadixUtil.binaryBytesToHexString(en_data));
        } catch (Exception ex) {
            throw new Exception();
        }
    }


    /**
     * 使用默认的公钥加密给定的字符串。
     *
     * @param plaintext 字符串
     * @return 给定字符串的密文
     */
    public static String encryptString(String plaintext) throws Exception {
        if (plaintext == null) {
            logger.error(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
            return null;
        }
        byte[] data = plaintext.getBytes();
        KeyPair keyPair = generateKeyPair();
        try {
            byte[] en_data = encrypt(keyPair.getPublic(), data);
            return new String(RadixUtil.binaryBytesToHexString(en_data));
        } catch (NullPointerException ex) {
            logger.error(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
            throw new NullPointerException();
        } catch (Exception ex) {
            throw new Exception();
        }
    }


    /**
     * 使用默认的私钥解密给定的字符串。
     *
     * @param encryptText 密文。
     * @return 原文字符串。
     */
    public static String decryptString(String encryptText) throws Exception {
        if (StringUtil.isEmpty(encryptText)) {
            return null;
        }
        KeyPair keyPair = generateKeyPair();
        try {
            byte[] en_data = RadixUtil.hexStringToBytes(encryptText);
            byte[] data = decrypt(keyPair.getPrivate(), en_data);
            return new String(data);
        } catch (NullPointerException ex) {
            logger.error(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
            throw new NullPointerException();
        } catch (Exception ex) {
            logger.error(String.format("\"%s\" Decryption failed. Cause: %s", encryptText, ex.getMessage()));
            throw new Exception();
        }
    }



    /*
         Sign or verify. -------------------------------
     */

    /**
     * 获取身份签名
     *
     * @param privateKey
     * @param src
     * @param encode
     * @return String
     * @throws Exception
     */
    public static String sign(PrivateKey privateKey, String src, String encode) throws Exception {
        try {
            Signature sigEng = Signature.getInstance(SIGN_ALGORITHMS);
            sigEng.initSign(privateKey);
            sigEng.update(src.getBytes(encode));  //更新要由字节签名或验证的数据。
            byte[] signature = sigEng.sign();
            return RadixUtil.binaryBytesToHexString(signature);
        } catch (Exception e) {
            String info = "sign failed: " + src + " | " + e.getMessage();
            logger.error(info, e);
            throw new Exception(info);
        }
    }


    /**
     * 校验
     *
     * @param publicKey
     * @param sign
     * @param src
     * @param encode
     * @throws Exception
     */
    public static void verify(PublicKey publicKey, String sign, String src, String encode) throws Exception {
        try {
            if (StringUtils.isBlank(sign) || StringUtils.isBlank(src)) {
                throw new NullPointerException(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
            }
            Signature sigEng = Signature.getInstance(SIGN_ALGORITHMS);
            sigEng.initVerify(publicKey);
            sigEng.update(src.getBytes(encode));
            byte[] sign1 = RadixUtil.hexStringToBytes(sign);   //签名数据
            if (!sigEng.verify(sign1)) {  //数字签名
                throw new Exception(ErrorMessage.VERIFY_SIGNATURE_FAIL.getErrorInfo());
            }
        } catch (Exception e) {
            String info = "verify failed: " + sign + " | " + src + " | " + e.getMessage();
            logger.error(info, e);
            throw new Exception(info);
        }
    }
}
