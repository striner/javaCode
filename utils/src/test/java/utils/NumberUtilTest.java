package utils;

import common.utils.NumberUtil;
import org.junit.Test;

public class NumberUtilTest {
    @Test
    public void test1() {
        System.out.println(NumberUtil.parseStringToInteger("242"));
        System.out.println(NumberUtil.formatMoney(78896824231.7646, 2));
        System.out.println(NumberUtil.roundUpFormatDouble(216415.312153));
    }
}
