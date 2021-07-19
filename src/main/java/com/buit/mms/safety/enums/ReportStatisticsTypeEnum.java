package com.buit.mms.safety.enums;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/29 13:44
 */
public enum ReportStatisticsTypeEnum {

    department("bgks"),
    category("sjlb"),
    event("sjxzid"),
    ;
    private String column;

    ReportStatisticsTypeEnum(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
