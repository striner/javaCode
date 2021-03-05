package common.enumeration.dateStyleEnum;

/**
 * A enumeration class of monthStyle.
 * Create by mulin on 2018/09/01.
 */
public enum MonthStyle {

    JANUARY("一月", "January", "Jan.", 1),
    FEBUARY("二月", "Febuary", "Jan.", 2),
    MARCH("三月", "March", "Mar.", 3),
    APRIL("四月", "April", "Apr.", 4),
    MAY("五月", "May", "May", 5),
    JUNE("六月", "June", "Jun.", 6),
    JULY("七月", "July", "Jul.", 7),
    AUGUST("八月", "August", "Aug.", 8),
    SEPTEMBER("九月", "September", "Sep.", 9),
    OCTOBER("十月", "October", "Oct.", 10),
    NOVEMBER("十一月", "November", "Nov.", 11),
    DECEMBER("十二月", "December", "Dec.", 12);

    private String name_cn;
    private String name_en;
    private String name_enShort;
    private int number;

    MonthStyle(String name_cn, String name_en, String name_enShort, int number) {
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

    
    public static MonthStyle getByName(String name) {
        for (MonthStyle month : MonthStyle.values()) {
            if (month.getName().equals(name)) {
                return month;
            }
        }
        return null;
    }

    public static MonthStyle getByNameCN(String name) {
        for (MonthStyle month : MonthStyle.values()) {
            if (month.getChineseName().equals(name)) {
                return month;
            }
        }
        return null;
    }

    public static MonthStyle getByShortName(String name) {
        for (MonthStyle month : MonthStyle.values()) {
            if (month.getShortName().equals(name)) {
                return month;
            }
        }
        return null;
    }

    public static MonthStyle getByNumber(int number) {
        for (MonthStyle month : MonthStyle.values()) {
            if (month.getNumber() == number) {
                return month;
            }
        }
        return null;
    }
}