package common.utils;

/**
 * 进制转换工具类
 *
 * create by mulin on 2018/09/08
 * This class is mainly used for operations related to radix.
 */
public class RadixUtil {

    /**
     * 用于建立十六进制字符的输出的小写字符数组
     */
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    /**
     * 用于建立十六进制字符的输出的大写字符数组
     */
    private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    /**
     * 用于建立进制字符的输出
     */
    private static final String NUMBER_STRING= "0123456789ABCDEF";
    /**
     * 支持的最小进制
     */
    public static int MIN_RADIX = 2;
    /**
     * 支持的最大进制
     */
    public static int MAX_RADIX = 16;



    /*
        Conversion of the sixteen mechanism. -------------------------------
     */

    /**
     * 将字节数组转换为十六进制字符数组（小写格式）
     *
     * @param data byte[]
     * @return 十六进制char[]
     */
    public static char[] byteToHexChar(byte[] data) {
        return byteToHexChar(data, true);
    }


    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data byte[]
     * @param toLowerCase  true 传换成小写格式 ，  false 传换成大写格式
     * @return 十六进制char[]
     */
    public static char[] byteToHexChar(byte[] data, boolean toLowerCase) {
        return byteToHexChar(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }


    /**
     * 将字节数组转换为十六进制字符串（小写格式）
     *
     * @param data byte[]
     * @return 十六进制String
     */
    public static String byteToHexString(byte[] data) {
        return byteToHexString(data, true);
    }


    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data byte[]
     * @param toLowerCase  true 传换成小写格式 ，  false 传换成大写格式
     * @return 十六进制String
     */
    public static String byteToHexString(byte[] data, boolean toLowerCase) {
        return byteToHexString(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }


    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data byte[]
     * @param toDigits 用于控制输出的char[]
     * @return 十六进制String
     */
    private static String byteToHexString(byte[] data, char[] toDigits) {
        return new String(byteToHexChar(data, toDigits));
    }


    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data byte[]
     * @param toDigits 用于控制输出的char[]
     * @return 十六进制char[]
     */
    private static char[] byteToHexChar(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }


    /**
     * 字符串转换成十六进制字符串
     */
    public static String stringToHexString(String str) {
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(DIGITS_UPPER[bit]);
            bit = bs[i] & 0x0f;
            sb.append(DIGITS_UPPER[bit]);
        }
        return sb.toString();
    }


    /**
     * 十六进制字符串转换字符串
     *
     * @param hexString
     * @return String
     */
    public static String hexStringToString(String hexString) {
        byte[] baKeyword = new byte[hexString.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            baKeyword[i] = (byte) (0xff & Integer.parseInt(hexString.substring(i * 2, i * 2 + 2), 16));
        }
        return new String(baKeyword);
    }


    /**
     * 将十六进制字符数组转换为字节数组
     *
     * @param hexData 十六进制char[]
     * @return byte[]
     * @throws RuntimeException 如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
     */
    public static byte[] hexCharToByte(char[] hexData) {

        int len = hexData.length;

        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }

        byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = hexStringToInt(hexData[j], j) << 4;
            j++;
            f = f | hexStringToInt(hexData[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }


    /**
     * 将十六进制字符转换成一个整数
     *
     * @param ch 十六进制char
     * @param index 十六进制字符在字符数组中的位置
     * @return 一个整数
     * @throws RuntimeException 当ch不是一个合法的十六进制字符时，抛出运行时异常
     */
    private static int hexStringToInt(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }


    /**
     * 把十六进制字符串转换成字节数组
     *
     * @param hexString
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        int len = (hexString.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hexString.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }


    private static int toByte(char c) {
        byte b = (byte) DIGITS_UPPER.toString().indexOf(c);
        return b;
    }



    /*
        Commonly used hex conversion. -------------------------------
     */

    /**
     * 将二进制转换为十六进制(小写)
     *
     * @param bString 二进制字符串
     * @return
     */
    public static String binaryStringToHexString(String bString) {
        return binaryStringToHexString(bString, true);
    }

    /**
     * 将二进制转换为十六进制(小写)
     *
     * @param bString 二进制字符串
     * @param toLowerCase  true 传换成小写格式 ，  false 传换成大写格式
     * @return
     */
    public static String binaryStringToHexString(String bString, Boolean toLowerCase) {
        if (bString == null || bString.equals("") || bString.length() % 8 != 0) {
            return null;
        }
        StringBuffer tmp = new StringBuffer();
        int iTmp = 0;
        for (int i = 0; i < bString.length(); i += 4) {
            iTmp = 0;
            for (int j = 0; j < 4; j++) {
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
            }
            if (toLowerCase) {
                tmp.append(Integer.toHexString(iTmp));
            }else{
                tmp.append(Integer.toHexString(iTmp).toUpperCase());
            }
        }
        return tmp.toString();
    }


    /**
     * 将二进制转换为十六进制字符输出（小写）
     *
     * @param bytes
     * @return  String
     */
    public static String binaryBytesToHexString(byte[] bytes) {
        return binaryBytesToHexString(bytes, true);
    }


    /**
     * 将二进制转换为十六进制字符输出
     *
     * @param bytes
     * @param toLowerCase  true 传换成小写格式 ，  false 传换成大写格式
     * @return  String
     */
    public static String binaryBytesToHexString(byte[] bytes, boolean toLowerCase) {
        StringBuilder s = new StringBuilder(bytes.length * 2);
        if (toLowerCase) {
            for (byte byt : bytes) {
                s.append(DIGITS_LOWER[(byt >>> 4) & 0x0f]);  //字节高4位
                s.append(DIGITS_LOWER[byt & 0x0f]);   //字节低4位
            }
        } else {
            for (byte byt : bytes) {
                s.append(DIGITS_UPPER[(byt >>> 4) & 0x0f]);  //字节高4位
                s.append(DIGITS_UPPER[byt & 0x0f]);   //字节低4位
            }
        }

        return s.toString();
    }


    /**
     * 十六进制转二进制
     *
     * @param hexString
     * @return
     */
    public static String hexStringToBinaryString(String hexString) {
        if (hexString == null || hexString.equals("") ) {
            return null;
        }
        StringBuffer bString = new StringBuffer();
        String tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString.append(tmp.substring(tmp.length() - 4));
        }
        return bString.toString();
    }


    /**
     * 十六进制转二进制
     * @param hexStr
     * @return
     */
    public static String hexStringToBinaryByte(byte[] hexStr) {
        return hexStringToBinaryString(new String(hexStr));
    }



    /*
        Infrequently used hex conversion. -------------------------------
     */

    /**
     * 十进制转其他进制
     *
     * @param dec 需要转换的数字
     * @param toRadix 输出进制。当不在转换范围内时，此参数会被设定为 2。
     * @return 指定输出进制的数字
     */
    private static String dec2Any(long dec, int toRadix) {
        if (toRadix < MIN_RADIX || toRadix > MAX_RADIX) {
            toRadix = 2;
        }
        if (toRadix == 10) {
            return String.valueOf(dec);
        }
        // -Long.MIN_VALUE 转换为 2 进制时长度为65
        char[] buf = new char[65];
        int charPos = 64;
        boolean isNegative = (dec < 0);
        if (!isNegative) {
            dec = -dec;
        }
        while (dec <= -toRadix) {
            buf[charPos--] = DIGITS_UPPER[(int) (-(dec % toRadix))];
            dec = dec / toRadix;
        }
        buf[charPos] = DIGITS_UPPER[(int) (-dec)];
        if (isNegative) {
            buf[--charPos] = '-';
        }
        return new String(buf, charPos, (65 - charPos));
    }


    /**
     * 其他进制转十进制
     *
     * @param number 输入数字
     * @param fromRadix 输入进制  只能在 2 和16 之间（包括 2 和 16）。
     * @return 十进制数字
     */
    public static long any2Dec(String number, int fromRadix) {
        long dec = 0;
        long digitValue = 0;
        int len = number.length() - 1;
        for (int t = 0; t <= len; t++) {
            digitValue = NUMBER_STRING.indexOf(number.toUpperCase().charAt(t));
            dec = dec * fromRadix + digitValue;
        }
        return dec;
    }


    /**
     * 任意进制转任意进制
     *
     * @param number 需要转换的数字 本身的进制由 fromRadix 指定
     * @param fromRadix 输入进制 只能在 2 和16 之间（包括 2 和 16）
     * @param toRadix 输出进制 只能在 2 和16 之间（包括 2 和 16）
     * @return  指定输出进制的数字
     */
    public static String comNumConver(String number, int fromRadix, int toRadix) {
        long dec = any2Dec(number, fromRadix);
        return dec2Any(dec, toRadix);
    }


    /**
     * 任意进制转任意进制
     *
     * @param number 需要转换的数字  本身的进制由 fromRadix 指定
     * @param fromRadix 输入进制 只能在 2\8\10\16 中间取值，否则返回原值
     * @param toRadix 输出进制 只能在 2\8\10\16 中间取值，否则返回原值
     * @return  指定输出进制的数字
     */
    public static String baseNumConver(String number, int fromRadix, int toRadix) {
        boolean isFromRadixBase = (fromRadix == 2 || fromRadix == 8 || fromRadix == 10 || fromRadix == 16);
        boolean isToRadixBase = (toRadix == 2 || toRadix == 8 || toRadix == 10 || toRadix == 16);
        // 进行转换并返回
        if (isFromRadixBase && isToRadixBase) {
            return comNumConver(number, fromRadix, toRadix);
        }
        // 返回原值
        return number;
    }


    /**
     * 任意进制的byte数组转任意进制的数
     *
     * @param bytes 需要转换的byte数组  本身的进制由 fromRadix 指定
     * @param fromRadix 输入进制 只能在 2\8\10\16 中间取值，否则返回原值
     * @param toRadix 输出进制 只能在 2\8\10\16 中间取值，否则返回原值
     * @return  指定输出进制的数字
     */
    public static int byteConverToNumber(byte[] bytes, int fromRadix, int toRadix) {
        String byteNumStr = byteToHexString(bytes);
        String numVal = baseNumConver(byteNumStr, fromRadix, toRadix);
        return Integer.parseInt(numVal);
    }
}
