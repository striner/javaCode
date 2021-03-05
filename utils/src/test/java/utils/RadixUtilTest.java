package utils;

import common.utils.RadixUtil;
import org.junit.Test;

public class RadixUtilTest {

    @Test
    public void test1() {
        byte[] bytes = new byte[]{1, 2, 3, 'a', 'b', 'A', 15, 16};
        for(char ch : RadixUtil.byteToHexChar(bytes)) {
            System.out.print(ch);
        }
        System.out.println();
        for(char ch : RadixUtil.byteToHexChar(bytes, false)) {
            System.out.print(ch);
        }
        System.out.println();

        System.out.println(RadixUtil.byteToHexString(bytes));
        System.out.println(RadixUtil.byteToHexString(bytes, false));

        String str1 = "123abA1516";
        System.out.println(RadixUtil.stringToHexString(str1));
    }

    @Test
    public void test2() {
        String str1 = "31323361624131353136";

        System.out.println(RadixUtil.hexStringToString(str1));

        for(byte by : RadixUtil.hexCharToByte(new char[]{'0', '1', '0', '2', '0', '3', '6', '1', '6', '2', '4', '1', '0', 'f', '1', '0'})) {
            System.out.print(by);
        }
        System.out.println();
        for(byte by : RadixUtil.hexCharToByte(new char[]{'0', '1', '0', '2', '0', '3', '6', '1', '6', '2', '4', '1', '0', 'F', '1', '0'})) {
            System.out.print(by);
        }
    }

    @Test
    public void test3() {
        System.out.println(RadixUtil.binaryStringToHexString("11010011"));
        System.out.println(RadixUtil.binaryStringToHexString("11010011", false));
        System.out.println(RadixUtil.binaryBytesToHexString(new byte[]{15, 127, 0, 'a'}));
        System.out.println(RadixUtil.binaryBytesToHexString(new byte[]{15, 127, 0, 'a'}, false));
        System.out.println(RadixUtil.hexStringToBinaryString("D3"));
        System.out.println(RadixUtil.hexStringToBinaryString("d3"));
        System.out.println(RadixUtil.hexStringToBinaryByte(new byte[]{'d', '3'}));
        System.out.println(RadixUtil.hexStringToBinaryByte(new byte[]{'D', '3'}));
    }

    @Test
    public void test4() {
//        System.out.println(RadixUtil.hexStringToBinaryString("AC123.2a"));
        System.out.println(RadixUtil.baseNumConver("AC123.2a", 16, 2));
    }
}
