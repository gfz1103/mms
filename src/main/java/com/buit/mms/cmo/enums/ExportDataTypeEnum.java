package com.buit.mms.cmo.enums;

import com.buit.his.export.enums.ExportFileTypeEnum;

import java.util.Arrays;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/8/18 10:56
 */
public enum ExportDataTypeEnum {

    operationApplyData("手术申请单",JasperPathConditionEnum.operationApplyPathCond),
    operationNotifyApplyData("手术通知单打印数据",JasperPathConditionEnum.operationNotifyPathCond),
    ;

    ExportFileTypeEnum[] supportType;

    JasperPathConditionEnum jasperCond;
    String desc;
    String jasperPath;

    ExportDataTypeEnum(String desc, JasperPathConditionEnum jasper, ExportFileTypeEnum... supportType){
        this.supportType=supportType;
        this.desc=desc;
        this.jasperCond=jasper;
    }

    ExportDataTypeEnum(String desc, JasperPathConditionEnum jasper){
        this.supportType=new ExportFileTypeEnum[]{ExportFileTypeEnum.html,ExportFileTypeEnum.pdf,ExportFileTypeEnum.word,ExportFileTypeEnum.excel,ExportFileTypeEnum.xml,ExportFileTypeEnum.csv};
        this.desc=desc;
        this.jasperCond=jasper;
    }

    ExportDataTypeEnum(String desc, String jasperPath, ExportFileTypeEnum... supportType){
        this.supportType=supportType;
        this.desc=desc;
        this.jasperPath=jasperPath;
        this.jasperCond=JasperPathConditionEnum.defaultJasperPath;
    }

    ExportDataTypeEnum(String desc, ExportFileTypeEnum... supportType){
        this.supportType=supportType;
        this.desc=desc;
        this.jasperPath="";
        this.jasperCond=JasperPathConditionEnum.defaultJasperPath;
    }

    ExportDataTypeEnum(String desc, String jasperPath){
        this.supportType=new ExportFileTypeEnum[]{ExportFileTypeEnum.html,ExportFileTypeEnum.pdf,ExportFileTypeEnum.word,ExportFileTypeEnum.excel,ExportFileTypeEnum.xml,ExportFileTypeEnum.csv};
        this.desc=desc;
        this.jasperPath=jasperPath;
        this.jasperCond=JasperPathConditionEnum.defaultJasperPath;
    }

    public JasperPathConditionEnum getJasperCond() {
        return jasperCond;
    }

    public boolean contains(ExportFileTypeEnum fileType){
        return Arrays.asList(supportType).contains(fileType);
    }

    public ExportFileTypeEnum[] getSupportType() {
        return supportType;
    }

    public String getDesc() {
        return desc;
    }

    public String getJasperPath() {
        return jasperPath;
    }
}
