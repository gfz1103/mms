package com.buit.mms.cmo.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/12 15:47
 */
public class QueryOperationApplyReq extends PageQuery {

    @NotNull
    @ApiModelProperty("申请类型，1：门诊，2：住院")
    private Integer sqlx;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String startDate;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String endDate;
    @ApiModelProperty("手术医生")
    private Integer ssys;
    @ApiModelProperty("病人科室")
    private Integer brks;
    @ApiModelProperty("审批状态，1：已审批，0：未审批，-1：全部")
    private Integer spbz;
    @ApiModelProperty("手术安排，1：已安排，0：未安排，-1：全部")
    private Integer ssap;
    @ApiModelProperty("病人id")
    private Integer brid;
    @ApiModelProperty(hidden = true)
    private Integer sqys;

    public Integer getSsap() {
        return ssap;
    }

    public void setSsap(Integer ssap) {
        this.ssap = ssap;
    }

    public Integer getBrid() {
        return brid;
    }

    public void setBrid(Integer brid) {
        this.brid = brid;
    }

    public Integer getSqys() {
        return sqys;
    }

    public void setSqys(Integer sqys) {
        this.sqys = sqys;
    }

    public Integer getSqlx() {
        return sqlx;
    }

    public void setSqlx(Integer sqlx) {
        this.sqlx = sqlx;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getSsys() {
        return ssys;
    }

    public void setSsys(Integer ssys) {
        this.ssys = ssys;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public Integer getSpbz() {
        return spbz;
    }

    public void setSpbz(Integer spbz) {
        this.spbz = spbz;
    }
}
