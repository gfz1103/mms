package com.buit.mms.safety.request;

import com.buit.commons.PageQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/29 13:17
 */
public class QueryReportInfoReq extends PageQuery {

    @ApiModelProperty("科室id")
    private Integer ksid;
    @ApiModelProperty("报告开始日期，yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bgrqStart;
    @ApiModelProperty("报告结束日期，yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bgrqEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("发生开始日期，yyyy-MM-dd")
    private LocalDate fsrqStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("发生结束日期，yyyy-MM-dd")
    private LocalDate fsrqEnd;
    @ApiModelProperty("事件等级")
    private List<Integer> sjlb;
    @ApiModelProperty("事件性质")
    private Integer sjxz;

    public Integer getKsid() {
        return ksid;
    }

    public void setKsid(Integer ksid) {
        this.ksid = ksid;
    }

    public LocalDate getBgrqStart() {
        return bgrqStart;
    }

    public void setBgrqStart(LocalDate bgrqStart) {
        this.bgrqStart = bgrqStart;
    }

    public LocalDate getBgrqEnd() {
        return bgrqEnd;
    }

    public void setBgrqEnd(LocalDate bgrqEnd) {
        this.bgrqEnd = bgrqEnd;
    }

    public LocalDate getFsrqStart() {
        return fsrqStart;
    }

    public void setFsrqStart(LocalDate fsrqStart) {
        this.fsrqStart = fsrqStart;
    }

    public LocalDate getFsrqEnd() {
        return fsrqEnd;
    }

    public void setFsrqEnd(LocalDate fsrqEnd) {
        this.fsrqEnd = fsrqEnd;
    }

    public List<Integer> getSjlb() {
        return sjlb;
    }

    public void setSjlb(List<Integer> sjlb) {
        this.sjlb = sjlb;
    }

    public Integer getSjxz() {
        return sjxz;
    }

    public void setSjxz(Integer sjxz) {
        this.sjxz = sjxz;
    }
}
