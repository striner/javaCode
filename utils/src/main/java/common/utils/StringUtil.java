package common.utils;

import common.enumeration.errorMessageEnum.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by mulin on 2018/09/07
 * This class is mainly used for operations related to strings.
 */
public class StringUtil {

    private static Logger log = LoggerFactory.getLogger(StringUtil.class);


    /*
         String segmentation. -------------------------------
     */

    /**
     * 根据逗号分割字符串
     *
     * @param str
     * @return String[]
     */
    public static String[] splitStringByComma(String str) {
        if(StringUtil.isEmpty(str)) {
            throw new NullPointerException(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
        }
        return str.split(",");
    }


    /**
     * 根据小数点分割字符串
     *
     * @param str
     * @return String[]
     */
    public static String[] splitStringByPoint(String str) {
        if(StringUtil.isEmpty(str)) {
            throw new NullPointerException(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
        }
        return str.split("\\.");
    }


    /**
     * 根据空格(一个或多个)分割字符串
     *
     * @param str
     * @return String[]
     */
    public static String[] splitStringBySpace(String str) {
        if(StringUtil.isEmpty(str)) {
            throw new NullPointerException(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
        }
        return str.split("\\s+");
    }


    /**
     * 根据分号分割字符串
     *
     * @param str
     * @return String[]
     */
    public static String[] splitStringBySemicolon(String str) {
        if(StringUtil.isEmpty(str)) {
            throw new NullPointerException(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
        }
        return str.split(";");
    }


    /**
     * 根据冒号分割字符串
     *
     * @param str
     * @return String[]
     */
    public static String[] splitStringByColon(String str) {
        if(StringUtil.isEmpty(str)) {
            throw new NullPointerException(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
        }
        return str.split(":");
    }


    /**
     * 根据自定义的正则分割字符串
     *
     * @param str
     * @return String[]
     */
    public static String[] splitStringCustomized(String str, String regex) {
        if(StringUtil.isEmpty(str)) {
            throw new NullPointerException(ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo());
        }
        return str.split(regex);
    }


    /**
     * 过滤掉所有空白字符
     *
     * @param str
     * @return
     */
    public static String trimAll(String str) {
        return str.replaceAll("\\s*", "");
    }


    /**
     * 空格与特殊字符的过滤
     * 过滤掉所有空格、换行、特殊符号。
     * @param str
     * @return
     */
    public static String validateString(String str) {
        return validateStringByChar(str, false);
    }


    /**
     * 空格与特殊字符的过滤
     *
     * @param str
     * @param allowChar true为允许特殊字符的存在 false为不允许特殊字符的存在
     * @return
     */
    public static String validateStringByChar(String str, Boolean allowChar) {
        if (!isEmpty(str)) {
            Pattern p = Pattern.compile("\\s|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");

            if (!allowChar) {
                String regEx = "[`~!@#$%^&*()+=|{}':;,\\[\\].<>/?~！@#￥%……&*（）——+|{}\\\\【】‘；：”“’。，、？\'\"]";
                Pattern p1 = Pattern.compile(regEx);
                Matcher m1 = p1.matcher(str);
                return m1.replaceAll("").trim();
            } else {
                return str.trim();
            }
        }
        return null;
    }


    /**
     * 空格与特殊字符的过滤
     *
     * @param str
     * @param allowBlank true为字符串中间允许空格的存在 false为不允许空格的存在
     * @return
     */
    public static String validateStringByBlank(String str, boolean allowBlank) {
        if (str != null) {
            if (!allowBlank) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(str);
                str = m.replaceAll("");
            }
            String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern p1 = Pattern.compile(regEx);
            Matcher m1 = p1.matcher(str);
            str = m1.replaceAll("").trim();
        }
        return str;
    }



    /*
         String segmentation. -------------------------------
     */

    /**
     * 获取字符串str后number个字符
     *
     * @param str
     * @param number
     * @return String
     */
    public static String getStartingChars(String str, int number) {
        if (isEmpty(str)) {
            return null;
        }
        String string = str.trim();
        if (str.length() <= number) {
            log.info(ErrorMessage.PATAMETER_NUMBER_TOO_LARGE.getErrorInfo());
            return str;
        }

        return string.substring(0, number);
    }


    /**
     * 获取字符串str前number个字符
     *
     * @param str
     * @param number
     * @return String
     */
    public static String getLastChars(String str, int number) {
        if (isEmpty(str)) {
            return null;
        }
        String string = str.trim();
        if (str.length() <= number) {
            log.info(ErrorMessage.PATAMETER_NUMBER_TOO_LARGE.getErrorInfo());
            return str;
        }

        return string.substring(string.length() - number, string.length());
    }


    /**
     * 获取字符串str前len位数
     *
     * @param str
     * @param length
     * @return String
     */
    public static String getStartingString(String str, int length) {
        if (isEmpty(str)) {
            return null;
        }
        if (length <= 0) {
            return "";
        }
        if (str.length() <= length) {
            log.error(ErrorMessage.PATAMETER_LENGTH_TOO_LARGE.getErrorInfo());
            return str;
        } else {
            return str.substring(0, length);
        }
    }


    /**
     * 获取字符串str后len位数
     *
     * @param str
     * @param len
     * @return String
     */
    public static String getLastString(String str, int len) {
        if (isEmpty(str)) {
            return null;
        }
        if (len <= 0) {
            return "";
        }
        if (str.length() <= len) {
            log.error(ErrorMessage.PATAMETER_LENGTH_TOO_LARGE.getErrorInfo());
            return str;
        } else {
            return str.substring(str.length() - len, str.length());
        }
    }


    /**
     * 控制字符串长度在maxLength内
     *
     * @param str
     * @param maxLength
     * @return String
     */
    public static String noMoreThan(String str, Integer maxLength) {
        if (str == null) {
            return null;
        }

        if (str.length() > maxLength) {
            return str.substring(0, maxLength);
        }

        return str;
    }



    /*
         Other common methods for strings. -------------------------------
     */

    /**
     * 是否为条形码的判断
     *
     * @param str
     * @return
     */
    public static Boolean isBarcode(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        // 验证条形码
        p = Pattern.compile("[A-Za-z0-9]+");
        m = p.matcher(str);
        b = m.matches();
        return b;
    }


    /**
     * 是否该字段里只有中英文和数字
     *
     * @param str 需要校验字段
     * @return 返回true/false
     */
    public static boolean isLetterDigitChinese(String str) {
        for (char c : str.toCharArray()) {
            if (!isLetterDigitChinese(c)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 是否是字母、数字、中文的判断
     * @param c
     * @return boolean
     */
    private static boolean isLetterDigitChinese(char c) {
        // 中文
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }

        // 字母
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        }

        // 数字
        return Character.isDigit(c);
    }


    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        Character firstChar = str.charAt(0);
        String tail = str.substring(1);
        str = Character.toLowerCase(firstChar) + tail;
        return str;
    }


    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        Character firstChar = str.charAt(0);
        String tail = str.substring(1);
        str = Character.toUpperCase(firstChar) + tail;
        return str;
    }


    /**
     * 使用给定的 charset 将此 String 编码到 byte 序列，并将结果存储到新的 byte 数组。
     *
     * @param content 字符串对象
     * @param charset 编码方式
     * @return 所得 byte 数组
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }

        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalArgumentException("Not support:" + charset, ex);
        }
    }


    /**
     * 判断字符串是否被包含于Set集合中
     *
     * @param s
     * @param set
     * @return boolean
     */
    public static boolean isContainsForSet(String s, Set<String> set) {
        for (String element : set) {
            if (element.contains(s)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断字符串是否被包含于List集合中
     *
     * @param s
     * @param list
     * @return boolean
     */
    public static boolean isContainsForList(String s, List<String> list) {
        for (String element : list) {
            if (element.contains(s)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断两字符串是否相同
     * Strings equality check are case insensitive.
     *
     * @param str1
     * @param str2
     * @return true if equal else false
     */
    public static boolean isEqualIgnoreCase(String str1, String str2) {
        return str1.trim().equalsIgnoreCase(str2.trim());
    }


    /**
     * 字符串拼加
     *
     * @param stringBuilder
     * @param strToAppend
     * @return
     */
    public static StringBuilder appendIfNotNull(StringBuilder stringBuilder, String strToAppend) {
        if (stringBuilder == null) {
            return null;
        }
        if (strToAppend != null) {
            stringBuilder.append(", ").append(strToAppend);
        }
        return stringBuilder;
    }


    /**
     * 判空
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return (str == null) || str.trim().equals("");
    }

}