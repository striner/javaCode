package utils;

import common.utils.StringUtil;
import org.apache.tools.ant.util.LeadPipeInputStream;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtilTest {

    @Test
    public void test1() {
        String str = "2,:3  ,2;1.3,3;2 1";
        for(String string : StringUtil.splitStringByComma(str)) {
            System.out.println(string);
        }
        System.out.println(" -------------- ");
        for(String string : StringUtil.splitStringByPoint(str)) {
            System.out.println(string);
        }
        System.out.println(" -------------- ");
        for(String string : StringUtil.splitStringBySpace(str)) {
            System.out.println(string);
        }
        System.out.println(" -------------- ");
        for(String string : StringUtil.splitStringBySemicolon(str)) {
            System.out.println(string);
        }
        System.out.println(" -------------- ");
        for(String string : StringUtil.splitStringByColon(str)) {
            System.out.println(string);
        }
        System.out.println(" -------------- ");
        for(String string : StringUtil.splitStringCustomized(str, "\\,:")) {
            System.out.println(string);
        }
    }

    @Test
    public void test2() {
        String str = "11360607";
        System.out.println(StringUtil.isBarcode(str));
    }

    @Test
    public void test3() {
        String str = "wiwejhruewhrfiojewoi 89！@#￥%……&*（）——+|{}【】0ewf:e23e23fre.fewf/";
        System.out.println(StringUtil.validateString(str));
        System.out.println(StringUtil.validateStringByBlank(str, true));
        System.out.println(StringUtil.validateStringByChar(str, true));
    }

    @Test
    public void test4() {
        String str = "hasfhl测试sagduoahfufe32striner";
//        System.out.println(StringUtil.noMoreThan(str, 8));
        System.out.println(StringUtil.isLetterDigitChinese(str));
    }

    @Test
    public void test5() {
        String str = "abcde1 使用给定的 charset 将此 String 编码到 byte 序列，并将结果存储到新的 byte 数组。";

        for(byte b : StringUtil.getContentBytes(str, "utf8")) {
            System.out.print(b);
        }
    }

    @Test
    public void test6() {
        Set<String> set = new HashSet<>();
        set.add("qwe");
        set.add("asd");
        set.add("zxc");
        System.out.println(StringUtil.isContainsForSet("qwe", set));
        System.out.println(StringUtil.isContainsForSet("cvb", set));

        System.out.println(" ----- ");

        List<String> list = new ArrayList<>();
        list.add("ert");
        list.add("dfg");
        list.add("cvb");
        System.out.println(StringUtil.isContainsForList("cvb", list));
        System.out.println(StringUtil.isContainsForList("wer", list));
    }

    @Test
    public void test7() {
        String str1 = "asfdsafsa";
        String str2 = "asdfg";
        String str3 = "asdfg";

        System.out.println(StringUtil.isEqualIgnoreCase(str1, str2));
        System.out.println(StringUtil.isEqualIgnoreCase(str2, str3));
        System.out.println(StringUtil.appendIfNotNull(new StringBuilder(str1), str2));
        System.out.println(StringUtil.getStartingChars(str1, 5));
        System.out.println(StringUtil.getLastChars(str1, 5));
    }
}
