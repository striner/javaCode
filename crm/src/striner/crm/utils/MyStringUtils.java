package striner.crm.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyStringUtils {

	public static String getMD5Value(String value) {
		MessageDigest messageDigest;
		try {
			//1.获得jdk提供信息摘要算法工具类
			messageDigest = MessageDigest.getInstance(value);
			//2.加密
			byte[] md5ValueByteArray = messageDigest.digest(value.getBytes());
			//3.将十进制转换成十六进制
			BigInteger bigInteger = new BigInteger(1,md5ValueByteArray); //数字1表示符号位为证书,0为负数
			return bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
