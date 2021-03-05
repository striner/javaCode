package common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Create by mulin on 2018/12/26.
 */
public class FormatUtil {


    /**
     * 判断是否为整数(包括负数)
     */
    public static boolean isNumber(Object arg) {
        return NumberBo(0, toString(arg));
    }


    /**
     * 判断是否为小数(包括整数,包括负数)
     */
    public static boolean isDecimal(Object arg) {
        return NumberBo(1, toString(arg));
    }


    /**
     * 判断是否为空 ,为空返回true
     */
    public static boolean isEmpty(Object arg) {
        return toStringTrim(arg).length() == 0 ? true : false;
    }


    /**
     * Object 转换成 Int 转换失败 返回默认值 0
     * 使用:toInt(值,默认值[选填])
     */
    public static int toInt(Object... args) {
        int def = 0;
        if (args != null) {
            String str = toStringTrim(args[0]);
            // 判断小数情况。舍弃小数位
            int stri = str.indexOf('.');
            str = stri > 0 ? str.substring(0, stri) : str;
            if (args.length > 1) {
                def = Integer.parseInt(args[args.length - 1].toString());
            }
            if (isNumber(str)) {
                return Integer.parseInt(str);
            }
        }
        return def;
    }


    /**
     * Object 转换成 Long 转换失败 返回默认值 0
     * 使用:toLong(值,默认值[选填])
     */
    public static long toLong(Object... args) {
        Long def = 0L;
        if (args != null) {
            String str = toStringTrim(args[0]);
            if (args.length > 1) {
                def = Long.parseLong(args[args.length - 1].toString());
            }
            if (isNumber(str)) {
                return Long.parseLong(str);
            }
        }
        return def;
    }


    /**
     * Object 转换成 Double 转换失败 返回默认值 0
     * 使用:toDouble(值,默认值[选填])
     */
    public static double toDouble(Object... args) {
        double def = 0;
        if (args != null) {
            String str = toStringTrim(args[0]);
            if (args.length > 1) {
                def = Double.parseDouble(args[args.length - 1].toString());
            }
            if (isDecimal(str)) {
                return Double.parseDouble(str);
            }
        }
        return def;
    }


    /**
     * Object 转换成 BigDecimal
     * 若转换失败 则返回默认值 0
     * 使用:toDecimal(值,默认值[选填])
     * 【特别注意】: new BigDecimal(Double) 会有误差，得先转String
     * 【特别注意】: 由于精度问题，不能涉及到其他数据类型 :float,double..浮点数类型
     * 【特别注意】：涉及到金额的承载及操作，不允许用浮点数！！
     */
    public static BigDecimal toDecimal(Object... args) {
        BigDecimal def = BigDecimal.ZERO;
        if (args != null) {
            String str = toStringTrim(args[0]);
            if (args.length > 1) {
                def = new BigDecimal(args[args.length - 1].toString());
            }
            if (isDecimal(str)) {
                return new BigDecimal(str);
            }
        }
        return def;
    }


    /**
     * Object 转换成 Boolean
     * 若转换失败 则返回默认值 false
     * 使用:toBoolean(值,默认值[选填])
     * true=[1,true,"true","ok","yes"]
     */
    public static boolean toBoolean(Object bo) {
        String bool = toString(bo);
        if (isEmpty(bool) || (!bool.equals("1") && !bool.equalsIgnoreCase("true")
                && !bool.equalsIgnoreCase("ok") && !bool.equalsIgnoreCase("yes"))) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Object 转换成 String
     * 为null则返回空字符
     * 使用:toString(值,默认值[选填])
     */
    public static String toString(Object... args) {
        String def = "";
        if (args != null) {
            if (args.length > 1) {
                def = toString(args[args.length - 1]);
            }
            Object obj = args[0];
            if (obj == null) {
                return def;
            }
            return obj.toString();
        } else {
            return def;
        }
    }


    /**
     * Object 转换成 String [去除所有空格];
     * 为null 返回空字符
     * 使用:toStringTrim(值,默认值[选填])
     */
    public static String toStringTrim(Object... args) {
        String str = toString(args);
        return str.replaceAll("\\s*", "");
    }


    /**
     * 数字左边补0
     * obj:要补0的数
     * length:补0后的长度
     */
    public static String leftPad(Object obj, int length) {
        return String.format("%0" + length + "d", toInt(obj));
    }


    /**
     * 小数 转 百分数
     */
    public static String toPercent(Double str) {
        StringBuffer sb = new StringBuffer(Double.toString(str * 100.0000d));
        return sb.append("%").toString();
    }


    /**
     * 百分数 转 小数
     */
    public static Double toPercent2(String str) {
        if (str.charAt(str.length() - 1) == '%')
            return Double.parseDouble(str.substring(0, str.length() - 1)) / 100.0000d;
        return 0d;
    }


    /**
     * String 转 Money
     */
    public static String toMoney(Object str, String MoneyType) {
        DecimalFormat df = new DecimalFormat(MoneyType);
        if (isDecimal(str))
            return df.format(toDecimal(str)).toString();
        return df.format(toDecimal("0.00")).toString();
    }


    /**
     * List集合去除重复值 只能用于基本数据类型。
     */
    public static List delMoreList(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        return newList;
    }


    /**
     * 字符串格式化
     * 使用占位符：{}
     */
    public static String formatParams(String message, Object[] params) {
        StringBuffer msg = new StringBuffer();
        String temp = "";
        for (int i = 0; i < params.length + 1; i++) {
            int j = message.indexOf("{}") + 2;
            if (j > 1) {
                temp = message.substring(0, j);
                temp = temp.replaceAll("\\{\\}", FormatUtil.toString(params[i]));
                msg.append(temp);
                message = message.substring(j);
            } else {
                msg.append(message);
                message = "";
            }
        }
        return msg.toString();
    }


    /**
     * 字符串格式化
     * 使用占位符：${xxx}  xxx为key
     */
    public static String formatParams(String message, Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            message = message.replaceAll("\\$\\{" + entry.getKey() + "\\}", entry.getValue());
        }
        return message;
    }


    /**
     * 判断是否是数字
     * type： 0为整数，1为小数
     */
    private static boolean NumberBo(int type, Object obj) {
        if (isEmpty(obj)) {
            return false;
        }
        int points = 0;
        int chr = 0;
        String str = toString(obj);
        for (int i = str.length(); --i >= 0; ) {
            chr = str.charAt(i);
            if (chr < 48 || chr > 57) { // 判断数字
                if (i == 0 && chr == 45) // 判断 - 号
                    return true;
                if (i >= 0 && chr == 46 && type == 1) { // 判断 . 号
                    ++points;
                    if (points <= 1) {
                        continue;
                    }
                }
                return false;
            }
        }
        return true;
    }


    /**
     * 转为JSON串
     */
    public static String toJSONString(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }


    /**
     * 获取UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
