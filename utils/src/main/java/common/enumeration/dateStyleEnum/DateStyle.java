package common.enumeration.dateStyleEnum;

/**
 * A enumeration class of dateStyle.
 * create by mulin on 2018/08/26
 *
 * 这里只枚举了常用的时间格式，DateUtil工具类同时也提供了直接传时间格式的方法。
 */
public enum DateStyle {

    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
    YYYY_MM_DD("yyyy-MM-dd"),
    YYYY_MM("yyyy-MM"),

    YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss"),
    YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm"),
    YYYY_MM_DD_EN("yyyy/MM/dd"),
    YYYY_MM_EN("yyyy/MM"),

    YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss"),
    YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm"),
    YYYY_MM_DD_CN("yyyy年MM月dd日"),
    YYYY_MM_CN("yyyy年MM月"),

    YYYY_MM_DD_HH_MM_SS_CN_D("yyyy.MM.dd HH:mm:ss"),
    YYYY_MM_DD_HH_MM_CN_D("yyyy.MM.dd HH:mm"),
    YYYY_MM_DD_CN_D("yyyy.MM.dd"),
    YYYY_MM_CN_D("yyyy.MM"),

    HH_MM_SS("HH:mm:ss"),
    HH_MM("HH:mm"),

    MM_DD_HH_MM_SS("MM-dd HH:mm:ss"),
    MM_DD_HH_MM("MM-dd HH:mm"),
    MM_DD("MM-dd"),

    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss"),
    MM_DD_HH_MM_EN("MM/dd HH:mm"),
    MM_DD_EN("MM/dd"),

    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss"),
    MM_DD_HH_MM_CN("MM月dd日 HH:mm"),
    MM_DD_CN("MM月dd日");

    private String value;

    DateStyle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DateStyle getByVal(String val) {
        for (DateStyle type : DateStyle.values()) {
            if (type.getValue().equals(val)) {
                return type;
            }
        }
        return null;
    }
}
