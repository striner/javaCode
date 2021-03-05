package utils;

import common.utils.DateUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void test1() throws Exception {
        System.out.println("自定义样式： " + DateUtil.getDateByFormat(DateUtil.getSimpleDateFormat("yy.MM.dd - HH:mm:ss")));
        System.out.println("当前日期： " + DateUtil.getCurrentDate());
        System.out.println("当前日期： " + DateUtil.getCurrentDateEN());
        System.out.println("当前日期： " + DateUtil.getCurrentDateCN());
        System.out.println("当前年月日： " + DateUtil.getCurrentDateYMD());
        System.out.println("当前年月日： " + DateUtil.getCurrentDateYMDEN());
        System.out.println("当前年月日： " + DateUtil.getCurrentDateYMDCN());
        System.out.println("当前时分秒： " + DateUtil.getCurrentDateHMS());
        System.out.println("当前时分： " + DateUtil.getCurrentDateHM());
        System.out.println("当前年份+月份： " + DateUtil.getCurrentYear() + "/" +  DateUtil.getCurrentMonth());
        System.out.println("当前月： " + DateUtil.getCurrentMonthInfo());
        System.out.println("周数： " + DateUtil.getCurrentWeek());
        System.out.println("时间戳： " + DateUtil.getTimeString());
    }

    @Test
    public void test2() throws Exception {
        System.out.println(DateUtil.isContainTargetDate(new Date(2018/11/2), new Date(2015/11/2), new Date(2017/2/15)));
        System.out.println("格式转换测试： " + DateUtil.format(DateUtil.getTomorrowDate(), "yyyy # MM # dd"));
        System.out.println("获取月份： " + DateUtil.getMonthString(DateUtil.stringToDate("11.2.2")));
        System.out.println("获取年份： " + DateUtil.getYearString(DateUtil.stringToDate("1.1")));   // 位数不够则默认补零
        System.out.println("前一天： " + DateUtil.getYesterdayString());
        System.out.println("前一天： " + DateUtil.getYesterdayDate());
        System.out.println("后一天： " + DateUtil.getTomorrowString());
        System.out.println("后一天： " + DateUtil.getTomorrowDate());
        System.out.println("天数增加测试： " + DateUtil.addDay("2018/11/29", 20));
        System.out.println("年份增加测试： " + DateUtil.addYear(new Date(), 20));
        System.out.println("天数差值测试： " + DateUtil.getIntervalDays("2028/12/1", "2025.2.10"));
        System.out.println("组装测试： " + DateUtil.endDate(new Date()));
        System.out.println("组装测试： " + DateUtil.beginDate(new Date(), "yyyy/MM/dd"));
    }
}
