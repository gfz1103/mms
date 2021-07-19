package com.buit.mms.followup.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
 * @Description
 * @Author tianjunhao
 * @Date 2020/12/17 9:28
 */
public class FollowUpStatisticsResp implements Serializable {
    @ApiModelProperty("科室名称")
    private String ksmc;
    @ApiModelProperty("出院人数")
    private Integer cyrs;
    @ApiModelProperty("随访总人数")
    private Integer sfzrs;
    @ApiModelProperty("成功随访人数")
    private Integer cgsfrs;
    @ApiModelProperty("失访人数")
    private Integer sfrs;
    @ApiModelProperty("随访率")
    private Double sfl;
    @ApiModelProperty("随访成功率")
    private Double sfcgl;
    @ApiModelProperty("满意人数")
    private Integer myrs;
    @ApiModelProperty("不满意人数")
    private Integer bmyrs;
    @ApiModelProperty("患者满意度")
    private Double hzmyd;

    public String getKsmc() {
        return ksmc;
    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }

    public Integer getCyrs() {
        return cyrs;
    }

    public void setCyrs(Integer cyrs) {
        this.cyrs = cyrs;
    }

    public Integer getSfzrs() {
        return sfzrs;
    }

    public void setSfzrs(Integer sfzrs) {
        this.sfzrs = sfzrs;
    }

    public Integer getCgsfrs() {
        return cgsfrs;
    }

    public void setCgsfrs(Integer cgsfrs) {
        this.cgsfrs = cgsfrs;
    }

    public Integer getSfrs() {
        return sfrs;
    }

    public void setSfrs(Integer sfrs) {
        this.sfrs = sfrs;
    }

    public Double getSfl() {
        return sfl;
    }

    public void setSfl(Double sfl) {
        this.sfl = sfl;
    }

    public Double getSfcgl() {
        return sfcgl;
    }

    public void setSfcgl(Double sfcgl) {
        this.sfcgl = sfcgl;
    }

    public Integer getMyrs() {
        return myrs;
    }

    public void setMyrs(Integer myrs) {
        this.myrs = myrs;
    }

    public Integer getBmyrs() {
        return bmyrs;
    }

    public void setBmyrs(Integer bmyrs) {
        this.bmyrs = bmyrs;
    }

    public Double getHzmyd() {
        return hzmyd;
    }

    public void setHzmyd(Double hzmyd) {
        this.hzmyd = hzmyd;
    }
}
