package common.utils;

import common.Exception.IncorrectFormatException;
import common.enumeration.dateStyleEnum.DateStyle;
import common.enumeration.dateStyleEnum.MonthStyle;
import common.enumeration.dateStyleEnum.WeekStyle;
import common.enumeration.errorMessageEnum.ErrorMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by mulin on 2018/08/26
 *
 * The tool class function is to be improved,
 * If you have any questions or suggestions, please contact OA:linmu.
 *
 * The methods designed in this class include the following categories:
 * 1. Tool method used in this class. (line: 49)
 * 2. Some common judgment methods about time. (line: 113)
 * 3. Get the current time. (line:174)
 * 4. Get the specified time. (line: 373)
 * 5. Date format conversion. (line: 480)
 * 6. Addition and subtraction of dates. (line: 691)
 * 7. Operation on time difference. (line: 959)
 * 8. Date assembly. (line:1009)
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static String PARAMETER_EMPTY_ERROR = ErrorMessage.PARAMETER_EMPTY_ERROR.getErrorInfo();
    private static String FORMAT_INCORRECT_ERROR = ErrorMessage.FORMAT_INCORRECT_ERROR.getErrorInfo();

    private static long oneSecond = 1000;
    private static long oneMinute = oneSecond*60;
    private static long oneHour = oneMinute*60;
    private static long oneDay = oneHour*24;


     /*
         Tool method used in this class. -------------------------------
     */

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static ThreadLocal<Map<String, SimpleDateFormat>> sdfMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            logger.info(Thread.currentThread().getName()	+ " init pattern: " + Thread.currentThread());
            return new HashMap<>();
        }
    };


    /**
     * 获取传入的日期格式对应的SimpleDateFormat对象
     * 每个线程只会new一次pattern对应的sdf
     *
     * @param pattern
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getSimpleDateFormat(String pattern) {
        Map<String, SimpleDateFormat> tl = sdfMap.get();
        SimpleDateFormat sdf = tl.get(pattern);
        if (sdf == null) {
            logger.info(Thread.currentThread().getName()+" put new sdf of pattern " + pattern + " to map.");
            sdf = new SimpleDateFormat(pattern);
            tl.put(pattern, sdf);
        }
        return sdf;
    }


    /**
     * 获取日期字符串的日期风格。
     *
     * @param date 日期字符串
     * @return 日期风格
     * @throws Exception
     */
    public static DateStyle getDateStyle(String date) {

        if (StringUtils.isEmpty(date)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        for (DateStyle style : DateStyle.values()) {
            try {
                SimpleDateFormat format = getSimpleDateFormat(style.getValue());
                format.setLenient(false);  //若lenient为true，则SimpleDateFormat会比较宽松地验证日期，如2018/02/29会被接受，并转换成2018/03/01
                format.parse(date);
            } catch (Exception e) {
                continue;
            }
            return style;
        }

        return null;
    }



    /*
        Some common judgment methods about time. -------------------------------
     */

    /**
     * 判断字符串是否为日期字符串
     *
     * @param date 日期字符串
     * @return true or false
     */
    public static boolean isDate(String date) throws Exception {

        if (getDateStyle(date) != null) {
            return true;
        }
        return false;
    }


    /**
     * 判断目标时间是否在startDate和endDate之间
     *
     * @param startDate
     * @param endDate
     * @param targetDate
     * @return boolean
     * @throws Exception
     */
    public static boolean isContainTargetDate(Date startDate, Date endDate, Date targetDate) {
        if (startDate == null || endDate == null || targetDate == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        return targetDate.after(startDate) && targetDate.before(endDate) ? true:false;
    }


    /**
     * 判断两组时间段是否存在交集
     *
     * @param leftStartDate
     * @param leftEndDate
     * @param rightStartDate
     * @param rightEndDate
     * @return boolean
     * @throws Exception
     */
    public static boolean isIntersection(Date leftStartDate, Date leftEndDate, Date rightStartDate, Date rightEndDate) {
        if (leftStartDate == null || leftEndDate == null || rightStartDate == null || rightEndDate == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        return
        ((leftStartDate.getTime() >= rightStartDate.getTime()) && leftStartDate.getTime() < rightEndDate.getTime())
                        || ((leftStartDate.getTime() > rightStartDate.getTime()) && leftStartDate.getTime() <= rightEndDate.getTime())
                        || ((rightStartDate.getTime() >= leftStartDate.getTime()) && rightStartDate.getTime() < leftEndDate.getTime())
                        || ((rightStartDate.getTime() > leftStartDate.getTime()) && rightStartDate.getTime() <= leftEndDate.getTime());
    }



    /*
        Get the current time. -------------------------------
     */

    /**
     * 获得服务器时间 yyyy-MM-dd HH:mm:ss
     *
     * @return String
     * @throws Exception
     */
    public static String getCurrentDate() {
        return format(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
    }


    /**
     * 获得服务器时间 yyyy/MM/dd HH:mm:ss
     *
     * @return String
     * @throws Exception
     */
    public static String getCurrentDateEN() {
        return format(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS_EN.getValue());
    }


    /**
     * 获得服务器时间 yyyy年MM月dd日 HH:mm:ss
     *
     * @return String
     * @throws Exception
     */
    public static String getCurrentDateCN() {
        return format(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN.getValue());
    }


    /**
     * 获取当前年月日 2018-09-01
     *
     * @return String
     * @throws Exception
     */
    public static String getCurrentDateYMD() {
        return format(new Date(), DateStyle.YYYY_MM_DD.getValue());
    }


    /**
     * 获取当前年月日 2018/09/01
     *
     * @return String
     * @throws Exception
     */
    public static String getCurrentDateYMDEN() {
        return format(new Date(), DateStyle.YYYY_MM_DD_EN.getValue());
    }


    /**
     * 获取当前年月日 2018年09月01日
     *
     * @return String
     * @throws Exception
     */
    public static String getCurrentDateYMDCN() {
        return format(new Date(), DateStyle.YYYY_MM_DD_CN.getValue());
    }


    /**
     * 获得当前时分秒 16:52:12
     *
     * @return String
     * @throws Exception
     */
    public static String getCurrentDateHMS() {
        return format(new Date(), DateStyle.HH_MM_SS.getValue());
    }


    /**
     * 获得当前几点几分 16:52
     *
     * @return String
     * @throws Exception
     */
    public static String getCurrentDateHM() {
        return format(new Date(), DateStyle.HH_MM.getValue());
    }


    /**
     * 获取当前年份
     *
     * @return int
     * @throws Exception
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }


    /**
     * 获取当前月份
     *
     * @return int
     * @throws Exception
     */
    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH)+1;
    }


    /**
     * 获取当前月
     *
     * @return MonthStyle
     * @throws Exception
     */
    public static MonthStyle getCurrentMonthInfo() throws Exception {
        return MonthStyle.getByNumber(getCurrentMonth());
    }


    /**
     * 获取当前星期
     *
     * @return int
     * @throws Exception
     */
    public static int getCurrentWeek() {
        return Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
    }


    /**
     * 获取当前星期
     *
     * @return MonthStyle
     * @throws Exception
     */
    public static WeekStyle getCurrentWeekInfo() throws Exception {
        return WeekStyle.getByNumber(getCurrentWeek());
    }


    /**
     * 获取当前日号
     *
     * @return int
     * @throws Exception
     */
    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 获取当前时间的时间戳
     *
     * @return long 时间戳
     * @throws Exception
     */
    public static long getTimeString() {
        return System.currentTimeMillis();
    }


    /**
     * 根据自定义的样式获得当前时间
     *
     * @param format 样式
     * @return String
     * @throws Exception
     */
    public static String getDateByFormat(Format format) {
        if (format == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return format.format(new Date());
    }


    /**
     * 根据自定义的样式获得当前时间
     *
     * @param dateStyle
     * @return String
     * @throws Exception
     */
    public static String getDateByDateStyle(DateStyle dateStyle) {
        if (dateStyle.equals(null)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return format(new Date(), dateStyle.getValue());
    }


    /*
        Get the specified time. -------------------------------
     */

    /**
     * 将时间转换为自定义的格式
     * 每个线程只会有一个SimpleDateFormat
     *
     * @param date
     * @param pattern
     * @return String
     */
    public static String format(Date date, String pattern) {
        return getSimpleDateFormat(pattern).format(date);
    }


    /**
     * 将时间转换为自定义的格式
     *
     * @param dateStr
     * @param pattern
     * @return Date
     * @throws ParseException
     */
    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSimpleDateFormat(pattern).parse(dateStr);
    }


    /**
     * 根据date获取日期年份
     *
     * @param date 日期
     * @return String 年份
     * @throws Exception
     */
    public static String getYearString(Date date) {
        if (date.equals(null)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        return format(date, DateStyle.YYYY_MM_DD_EN.getValue()).substring(0, 4);
    }


    /**
     * 根据date获取日期月份  2018/09/01 --> 09
     *
     * @param date 日期
     * @return String 月份
     * @throws Exception
     */
    public static String getMonthString(Date date) {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return format(date, DateStyle.YYYY_MM_DD_EN.getValue()).substring(5, 7);
    }


    /**
     * 根据date获取日期月份  2018/09/01 --> 9
     *
     * @param date 日期
     * @return int 月份
     * @throws Exception
     */
    public static int getMonthNumber(Date date) throws Exception {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return Integer.parseInt(getMonthString(date));
    }


    /**
     * 根据date获取日期日号  2018/09/01 --> 01
     *
     * @param date 日期
     * @return String 日号
     * @throws Exception
     */
    public static String getDayString(Date date) {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return format(date, DateStyle.YYYY_MM_DD_EN.getValue()).substring(8, 10);
    }


    /**
     * 根据date获取日期日号  2018/09/01 --> 1
     *
     * @param date 日期
     * @return int 日号
     * @throws Exception
     */
    public static int getDayNumber(Date date) throws Exception {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return Integer.parseInt(getDayString(date));
    }



    /*
        Date format conversion. -------------------------------
     */

    /**
     * 将日期字符串转化为日期。  
     *
     * @param date 日期字符串
     * @return 日期
     * @throws Exception
     */
    public static Date stringToDate(String date) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle == null) {
            throw new IncorrectFormatException(FORMAT_INCORRECT_ERROR);
        }
        return stringToDate(date, dateStyle);
    }


    /**
     * 将日期字符串转化为日期。  
     *
     * @param date 日期字符串
     * @param pattern 日期格式
     * @return 日期
     * @throws Exception
     */
    public static Date stringToDate(String date, String pattern) throws Exception {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(pattern)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        return parse(date, pattern);
    }


    /**
     * 将日期字符串转化为日期。  
     *
     * @param date 日期字符串
     * @param dateStyle 日期风格
     * @return 日期
     * @throws Exception
     */
    public static Date stringToDate(String date, DateStyle dateStyle) throws Exception {
        if (StringUtils.isEmpty(date) || dateStyle == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return stringToDate(date, dateStyle.getValue());
    }


    /**
     * 将日期转化为日期字符串。
     *
     * @param date 日期
     * @return String 日期字符串
     * @throws Exception
     */
    public static String dateToString(Date date) throws Exception {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return dateToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS_EN.getValue());
    }



    /**
     * 将日期转化为日期字符串。  
     *
     * @param date 日期
     * @param pattern 日期格式
     * @return 日期字符串
     * @throws Exception
     */
    public static String dateToString(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern) || date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return getSimpleDateFormat(pattern).format(date);
    }


    /**
     * 将日期转化为日期字符串。
     *
     * @param date 日期
     * @param dateStyle 日期风格
     * @return 日期字符串
     * @throws Exception
     */
    public static String dateToString(Date date, DateStyle dateStyle) throws Exception {
        if (dateStyle == null || date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return dateToString(date, dateStyle.getValue());
    }


    /**
     * 将日期字符串转化为另一日期字符串。  
     *
     * @param date 旧日期字符串
     * @param newPattern 新日期格式
     * @return 新日期字符串
     * @throws Exception
     */
    public static String stringToString(String date, String newPattern) throws Exception {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(newPattern)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        DateStyle oldDateStyle = getDateStyle(date);
        return stringToString(date, oldDateStyle, newPattern);
    }


    /**
     * 将日期字符串转化为另一日期字符串。  
     *
     * @param date 旧日期字符串
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     * @throws Exception
     */
    public static String stringToString(String date, DateStyle newDateStyle) throws Exception {
        if (StringUtils.isEmpty(date) || newDateStyle == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        DateStyle oldDateStyle = getDateStyle(date);
        return stringToString(date, oldDateStyle, newDateStyle);
    }


    /**
     * 将日期字符串转化为另一日期字符串。  
     *
     * @param date 旧日期字符串
     * @param oldPattern 旧日期格式
     * @param newPattern 新日期格式
     * @return 新日期字符串
     * @throws Exception
     */
    public static String stringToString(String date, String oldPattern, String newPattern) throws Exception {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(oldPattern) || StringUtils.isEmpty(newPattern)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return dateToString(stringToDate(date, oldPattern), newPattern);
    }


    /**
     * 将日期字符串转化为另一日期字符串。  
     *
     * @param date 旧日期字符串
     * @param oldDteStyle 旧日期风格
     * @param newPattern 新日期格式
     * @return 新日期字符串
     * @throws Exception
     */
    public static String stringToString(String date, DateStyle oldDteStyle, String newPattern) throws Exception {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(newPattern) || oldDteStyle == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        return stringToString(date, oldDteStyle.getValue(), newPattern);
    }


    /**
     * 将日期字符串转化为另一日期字符串。  
     *
     * @param date 旧日期字符串
     * @param oldPattern 旧日期格式
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     * @throws Exception
     */
    public static String stringToString(String date, String oldPattern, DateStyle newDateStyle) throws Exception {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(oldPattern) || newDateStyle == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return stringToString(date, oldPattern, newDateStyle.getValue());
    }


    /**
     * 将日期字符串转化为另一日期字符串。  
     *
     * @param date 旧日期字符串
     * @param oldPattern 旧日期风格
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     * @throws Exception
     */
    public static String stringToString(String date, DateStyle oldPattern, DateStyle newDateStyle) throws Exception {
        if (StringUtils.isEmpty(date) || oldPattern == null || newDateStyle == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return stringToString(date, oldPattern.getValue(), newDateStyle.getValue());
    }



    /*
        Addition and subtraction of dates. -------------------------------
     */

    /**
     * 获取前一天的Date
     *
     * @return String
     * @throws Exception
     */
    public static String getYesterdayString() throws Exception {
        return dateToString(getYesterdayDate());
    }


    /**
     * 获取前一天的Date
     *
     * @return Date
     * @throws Exception
     */
    public static Date getYesterdayDate() throws Exception {
        return addDay(new Date(), -1);
    }


    /**
     * 获取后一天的Date
     *
     * @return String
     * @throws Exception
     */
    public static String getTomorrowString() throws Exception {
        return dateToString(getTomorrowDate());
    }


    /**
     * 获取后一天的Date
     *
     * @return Date
     * @throws Exception
     */
    public static Date getTomorrowDate() throws Exception {
        return addDay(new Date(), 1);
    }


    /**
     * 增加日期的年份。  
     *
     * @param date 日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期字符串
     */
    public static String addYear(String date, int yearAmount) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.YEAR, yearAmount);
    }


    /**
     * 增加日期的年份。  
     *
     * @param date 日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) throws Exception {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.YEAR, yearAmount);
    }


    /**
     * 增加日期的月份。  
     *
     * @param date 日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static String addMonth(String date, int monthAmount) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.MONTH, monthAmount);
    }


    /**
     * 增加日期的月份。  
     *
     * @param date 日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int monthAmount) throws Exception {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.MONTH, monthAmount);
    }


    /**
     * 增加日期的天数。  
     *
     * @param date 日期字符串
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int dayAmount) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.DATE, dayAmount);
    }


    /**
     * 增加日期的天数。  
     *
     * @param date 日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) throws Exception {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.DATE, dayAmount);
    }


    /**
     * 增加日期的小时。  
     *
     * @param date 日期字符串
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期字符串
     */
    public static String addHour(String date, int hourAmount) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }


    /**
     * 增加日期的小时。  
     *
     * @param date 日期
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int hourAmount) throws Exception {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }


    /**
     * 增加日期的分钟。  
     *
     * @param date 日期字符串
     * @param minuteAmount 增加数量。可为负数
     * @return 增加分钟后的日期字符串
     */
    public static String addMinute(String date, int minuteAmount) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }


    /**
     * 增加日期的分钟。  
     *
     * @param date 日期
     * @param minuteAmount 增加数量。可为负数
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int minuteAmount) throws Exception {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }


    /**
     * 增加日期的秒钟。  
     *
     * @param date 日期字符串
     * @param secondAmount 增加数量。可为负数
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int secondAmount) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.SECOND, secondAmount);
    }


    /**
     * 增加日期的秒钟。  
     *
     * @param date 日期
     * @param secondAmount 增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int secondAmount) throws Exception {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return addInteger(date, Calendar.SECOND, secondAmount);
    }


    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date 日期字符串
     * @param dateType 类型
     * @param amount 数值
     * @return 计算后日期字符串
     */
    private static String addInteger(String date, int dateType, int amount) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        DateStyle dateStyle = getDateStyle(date);
        Date myDate = stringToDate(date, dateStyle);
        myDate = addInteger(myDate, dateType, amount);
        return dateToString(myDate, dateStyle);
    }
    
    
    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date 日期
     * @param dateType 类型
     * @param amount 数值
     * @return 计算后日期
     */
    private static Date addInteger(Date date, int dateType, int amount) {
        if (date == null) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateType, amount);
        return calendar.getTime();
    }



    /*
        Operation on time difference. -------------------------------
     */

    /**
     * 获取两个日期相差的天数
     *
     * @param date 日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差天数。
     * @throws Exception
     */
    public static long getIntervalDays(String date, String otherDate) throws Exception {
        return getIntervalDays(stringToDate(date), stringToDate(otherDate));
    }


    /**
     * 计算两个日期相隔的天数
     *
     * @param date
     * @param otherDate
     * @return int
     * @throws Exception
     */
    public static long getIntervalDays(Date date, Date otherDate) {
        return (date.getTime()-otherDate.getTime())/oneDay;
    }


    /**
     * 获取期间的年龄
     *
     * @param date
     * @param otherDate
     * @return String
     * @return String
     */
    /*public static String getAge(Date date, Date otherDate) throws Exception {
        long dis = DateUtil.getIntervalDays(new Date(), otherDate);
        long year = dis / 365;
        long month = dis % 365 / 30;
        long day = dis % 365 % 31;
        String age = (year > 0 ? year + "岁" : "")
                + (month > 0 ? month + "个月" : "") + (day + "天");
        return age;
    }*/



    /*
        Date assembly. -------------------------------
     */

    /**
     * 在传进来的日期字符串结尾添加 00:00:00
     * 由于这里只涉及字符串的组装，默认传入的参数的时间格式存在于DateStyle枚举类中。
     *
     * @param str 日期字符串
     * @return Date
     */
    public static String beginDate(Date str) {
        if (str == null || str.equals(null)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        return dateToString(str, DateStyle.YYYY_MM_DD_EN.getValue()) + " 00:00:00";
    }


    /**
     * 在传进来的日期字符串结尾添加 23:59:59
     * 由于这里只涉及字符串的组装，默认传入的参数的时间格式存在于DateStyle枚举类中。
     *
     * @param str 日期字符串
     * @return Date
     */
    public static String endDate(Date str) {
        if (str == null || str.equals(null)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        return dateToString(str, DateStyle.YYYY_MM_DD_EN.getValue()) + " 23:59:59";
    }

    /**
     * 在传进来的日期字符串结尾添加 00:00:00
     * 由于这里只涉及字符串的组装，默认传入的参数的时间格式存在于DateStyle枚举类中。
     *
     * @param str 日期字符串
     * @param pattern 日期格式。  注意：这里只进行了字符串的简单拼装，日期格式中不要包含时、分、秒。
     * @return Date
     * @throws Exception
     */
    public static String beginDate(Date str, String pattern) {
        if (str == null || str.equals(null)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        return dateToString(str, pattern) + " 00:00:00";
    }


    /**
     * 在传进来的日期字符串结尾添加 23:59:59
     * 由于这里只涉及字符串的组装，默认传入的参数的时间格式存在于DateStyle枚举类中。
     *
     * @param str 日期字符串
     * @param pattern 日期格式。  注意：这里只进行了字符串的简单拼装，日期格式中不要包含时、分、秒。
     * @return Date
     * @throws Exception
     */
    public static String endDate(Date str, String pattern) {
        if (str == null || str.equals(null)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        return dateToString(str, pattern) + " 23:59:59";
    }


    /**
     * 在传进来的日期字符串结尾添加 00:00:00
     * 由于这里只涉及字符串的组装，默认传入的参数的时间格式存在于DateStyle枚举类中。
     *
     * @param str 日期字符串
     * @return Date
     */
    public static String beginDateStr(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }

        return str + " 00:00:00";
    }


    /**
     * 在传进来的日期字符串结尾添加 23:59:59
     * 由于这里只涉及字符串的组装，默认传入的参数的时间格式存在于DateStyle枚举类中。
     *
     * @param str 日期字符串
     * @return Date
     */
    public static String endDateStr(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new NullPointerException(PARAMETER_EMPTY_ERROR);
        }
        return str + " 23:59:59";
    }

}
