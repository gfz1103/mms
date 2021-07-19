package com.buit.mms.cmo.request;

import com.buit.mms.cmo.model.OptSssq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/6 10:12
 */
public class SaveOperationApplyReq extends OptSssq {

    private Integer jzxh;
    private String jzkh;
    private String brxm;
    @ApiModelProperty("乙肝结果,-1:阴性，1：阳性,0:未上报")
    private Integer ygjg;
    @ApiModelProperty("丙肝结果,-1:阴性，1：阳性,0:未上报")
    private Integer bgjg;
    @ApiModelProperty("梅毒结果,-1:阴性，1：阳性,0:未上报")
    private Integer mdjg;
    @ApiModelProperty("hiv结果,-1:阴性，1：阳性,0:未上报")
    private Integer hivjg;
    @ApiModelProperty("肝炎相关抗原,-1:阴性，1：阳性,0:未上报")
    private Integer gyxgky;

    public Integer getYgjg() {
        return ygjg;
    }

    public void setYgjg(Integer ygjg) {
        this.ygjg = ygjg;
    }

    public Integer getBgjg() {
        return bgjg;
    }

    public void setBgjg(Integer bgjg) {
        this.bgjg = bgjg;
    }

    public Integer getMdjg() {
        return mdjg;
    }

    public void setMdjg(Integer mdjg) {
        this.mdjg = mdjg;
    }

    public Integer getHivjg() {
        return hivjg;
    }

    public void setHivjg(Integer hivjg) {
        this.hivjg = hivjg;
    }

    public Integer getGyxgky() {
        return gyxgky;
    }

    public void setGyxgky(Integer gyxgky) {
        this.gyxgky = gyxgky;
    }

    public Integer getJzxh() {
        return jzxh;
    }

    public void setJzxh(Integer jzxh) {
        this.jzxh = jzxh;
    }

    public String getJzkh() {
        return jzkh;
    }

    public void setJzkh(String jzkh) {
        this.jzkh = jzkh;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }
}
