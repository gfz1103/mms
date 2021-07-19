package com.buit.mms.safety.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 13:46
 */
public class QueryReportListReq extends PageQuery {

    @NotNull
    @ApiModelProperty("科室id")
    private Integer ksid;
    @ApiModelProperty("报告开始日期，yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bgrqStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("报告结束日期，yyyy-MM-dd")
    private LocalDate bgrqEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("发生开始日期，yyyy-MM-dd")
    private LocalDate fsrqStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("发生结束日期，yyyy-MM-dd")
    private LocalDate fsrqEnd;
    @ApiModelProperty("状态，0：未上报/未审批，1：已上报/已审批")
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
