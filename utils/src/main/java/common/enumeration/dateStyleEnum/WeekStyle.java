package common.enumeration.dateStyleEnum;

/**
 * A enumeration class of weekStyle.
 * Create by mulin on 2018/08/26.
 */
public enum WeekStyle {
    MONDAY("星期一", "Monday", "Mon.", 1),
    TUESDAY("星期二", "Tuesday", "Tues.", 2),
    WEDNESDAY("星期三", "Wednesday", "Wed.", 3),
    THURSDAY("星期四", "Thursday", "Thur.", 4),
    FRIDAY("星期五", "Friday", "Fri.", 5),
    SATURDAY("星期六", "Saturday", "Sat.", 6),
    SUNDAY("星期日", "Sunday", "Sun.", 7);

    private String name_cn;
    private String name_en;
    private String name_enShort;
    private int number;

    WeekStyle(String name_cn, String name_en, String name_enShort, int number) {
        this.name_cn = name_cn;
        this.name_en = name_en;
        this.name_enShort = name_enShort;
        this.number = number;
    }

    public String getChineseName() {
        return name_cn;
    }

    public String getName() {
        return name_en;
    }

    public String getShortName() {
        return name_enShort;
    }

    public int getNumber() {
        return number;
    }


    public static WeekStyle getByName(String name) {
        for (WeekStyle week : WeekStyle.values()) {
            if (week.getName().equals(name)) {
                return week;
            }
        }
        return null;
    }

    public static WeekStyle getByNameCN(String name) {
        for (WeekStyle week : WeekStyle.values()) {
            if (week.getChineseName().equals(name)) {
                return week;
            }
        }
        return null;
    }

    public static WeekStyle getByShortName(String name) {
        for (WeekStyle week : WeekStyle.values()) {
            if (week.getShortName().equals(name)) {
                return week;
            }
        }
        return null;
    }

    public static WeekStyle getByNumber(int number) {
        for (WeekStyle week : WeekStyle.values()) {
            if (week.getNumber() == number) {
                return week;
            }
        }
        return null;
    }
}
