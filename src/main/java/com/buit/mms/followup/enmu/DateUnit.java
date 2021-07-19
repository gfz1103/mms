package com.buit.mms.followup.enmu;

/**
 * 随访设置表天数单位枚举<br>
 * @author tianjunhao
 */
public enum DateUnit {
    DAY("天", 1),
    WEEK("周", 7),
    MONTH("月", 30),
    YEAR("年", 365);

    private String type;
    private int days;

    private DateUnit(String type, int days) {
        this.type = type;
        this.days = days;
    }

    public static int getDays(String type) {
        for (DateUnit c : DateUnit.values()) {
            if (c.getType().equals(type)) {
                return c.getDays();
            }
        }
        return 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
