package com.buit.mms.cmo.response;

import com.buit.mms.cmo.model.OptSssq;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/6 13:52
 */
public class OperationApplyResp extends OptSssq {

    private String brxm;
    private Integer brxb;
    private String brnl;
    private Timestamp csny;
    private Timestamp ryrq;
    private Integer bq;
    private String ch;
    private Integer ks;
    private String szzc;
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
    @ApiModelProperty("手术安排，0：未安排，1：已安排")
    private Integer ssap;

    public Integer getSsap() {
        return ssap;
    }

    public void setSsap(Integer ssap) {
        this.ssap = ssap;
    }

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

    public Timestamp getRyrq() {
        return ryrq;
    }

    public void setRyrq(Timestamp ryrq) {
        this.ryrq = ryrq;
    }

    public String getSzzc() {
        return szzc;
    }

    public void setSzzc(String szzc) {
        this.szzc = szzc;
    }

    public Integer getBq() {
        return bq;
    }

    public void setBq(Integer bq) {
        this.bq = bq;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public Integer getKs() {
        return ks;
    }

    public void setKs(Integer ks) {
        this.ks = ks;
    }

    public Timestamp getCsny() {
        return csny;
    }

    public void setCsny(Timestamp csny) {
        this.csny = csny;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public Integer getBrxb() {
        return brxb;
    }

    public void setBrxb(Integer brxb) {
        this.brxb = brxb;
    }

    public String getBrnl() {
        return brnl;
    }

    public void setBrnl(String brnl) {
        this.brnl = brnl;
    }
}
