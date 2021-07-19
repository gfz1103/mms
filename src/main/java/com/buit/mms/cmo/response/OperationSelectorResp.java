package com.buit.mms.cmo.response;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/11 14:36
 */
public class OperationSelectorResp {

    @ApiModelProperty(value="费用序号")
    private Integer fyxh;
    @ApiModelProperty(value="费用名称")
    private String fymc;
    @ApiModelProperty(value="fydj")
    private BigDecimal fydj;
    @ApiModelProperty(value="拼音代码")
    private String pydm;
    @ApiModelProperty(value = "费用单位")
    private String fydw;
    @ApiModelProperty(value="费用归并")
    private Integer fygb;
    @ApiModelProperty(value="手术内码")
    private Integer ssnm;
    @ApiModelProperty(value="手术等级")
    private Integer ssdj;

    public Integer getFyxh() {
        return fyxh;
    }

    public void setFyxh(Integer fyxh) {
        this.fyxh = fyxh;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public BigDecimal getFydj() {
        return fydj;
    }

    public void setFydj(BigDecimal fydj) {
        this.fydj = fydj;
    }

    public String getPydm() {
        return pydm;
    }

    public void setPydm(String pydm) {
        this.pydm = pydm;
    }

    public String getFydw() {
        return fydw;
    }

    public void setFydw(String fydw) {
        this.fydw = fydw;
    }

    public Integer getFygb() {
        return fygb;
    }

    public void setFygb(Integer fygb) {
        this.fygb = fygb;
    }

    public Integer getSsnm() {
        return ssnm;
    }

    public void setSsnm(Integer ssnm) {
        this.ssnm = ssnm;
    }

    public Integer getSsdj() {
        return ssdj;
    }

    public void setSsdj(Integer ssdj) {
        this.ssdj = ssdj;
    }
}
