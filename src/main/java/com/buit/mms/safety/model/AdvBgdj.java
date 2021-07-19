package com.buit.mms.safety.model;

import io.swagger.annotations.ApiModelProperty;
import org.bouncycastle.util.Times;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 13:28
 */
public class AdvBgdj {

    private Integer id;
    @ApiModelProperty("机构id")
    @NotNull
    private Integer jgid;
    @ApiModelProperty("事件类别，医院字典：SJ0000000001")
    @NotNull
    private Integer sjlb;
    @ApiModelProperty("事件性质id")
    @NotNull
    private Integer sjxzid;
    @ApiModelProperty("发生时间")
    @NotNull
    private Timestamp fssj;
    @ApiModelProperty("事件描述")
    @NotNull
    private String sjms;
    @ApiModelProperty("发生地点")
    @NotNull
    private String fsdd;
    @ApiModelProperty("报告人工号")
    @NotNull
    private Integer bgrgh;
    @ApiModelProperty("报告科室")
    @NotNull
    private Integer bgks;
    @ApiModelProperty("报告时间")
    private Timestamp bgsj;
    @ApiModelProperty("事件原因")
    private String sjyy;
    @ApiModelProperty("制度流程，医院字典：SJ0000000002")
    @NotNull
    private Integer zdlc;
    @ApiModelProperty("文件编号")
    private String wjbh;
    @ApiModelProperty("再次几率，医院字典：SJ0000000003")
    @NotNull
    private Integer zcjl;
    @ApiModelProperty("其他说明")
    private String qtsm;
    @ApiModelProperty("处理结果")
    @NotNull
    private String cljg;
    @ApiModelProperty("责任科室")
    @NotNull
    private Integer zrks;
    @ApiModelProperty("责任负责人")
    @NotNull
    private Integer zrfzr;
    @ApiModelProperty("责任填报时间")
    private Timestamp zrtbsj;
    @ApiModelProperty("上级主管人意见")
    private String sjzgryj;
    @ApiModelProperty("上级主管部门")
    private Integer sjzgbm;
    @ApiModelProperty("上级主管负责人")
    private Integer sjzgfzr;
    @ApiModelProperty("上级主管填表事件")
    private Timestamp sjzgtbsj;
    @ApiModelProperty("医务科意见")
    private String ywkyj;
    @ApiModelProperty("上报标志，医院字典：SJ0000000004")
    private Integer sbbz;
    @ApiModelProperty("审核标志，医院字典：SJ0000000006")
    private Integer shbz;
    @ApiModelProperty("审核结果，医院字典：SJ0000000007")
    private Integer shjg;
    @ApiModelProperty("分析标志，医院字典：SJ0000000005")
    private Integer fxbz;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public Integer getSjlb() {
        return sjlb;
    }

    public void setSjlb(Integer sjlb) {
        this.sjlb = sjlb;
    }

    public Integer getSjxzid() {
        return sjxzid;
    }

    public void setSjxzid(Integer sjxzid) {
        this.sjxzid = sjxzid;
    }

    public Timestamp getFssj() {
        return fssj;
    }

    public void setFssj(Timestamp fssj) {
        this.fssj = fssj;
    }

    public String getSjms() {
        return sjms;
    }

    public void setSjms(String sjms) {
        this.sjms = sjms;
    }

    public String getFsdd() {
        return fsdd;
    }

    public void setFsdd(String fsdd) {
        this.fsdd = fsdd;
    }

    public Integer getBgrgh() {
        return bgrgh;
    }

    public void setBgrgh(Integer bgrgh) {
        this.bgrgh = bgrgh;
    }

    public Integer getBgks() {
        return bgks;
    }

    public void setBgks(Integer bgks) {
        this.bgks = bgks;
    }

    public Timestamp getBgsj() {
        return bgsj;
    }

    public void setBgsj(Timestamp bgsj) {
        this.bgsj = bgsj;
    }

    public String getSjyy() {
        return sjyy;
    }

    public void setSjyy(String sjyy) {
        this.sjyy = sjyy;
    }

    public Integer getZdlc() {
        return zdlc;
    }

    public void setZdlc(Integer zdlc) {
        this.zdlc = zdlc;
    }

    public String getWjbh() {
        return wjbh;
    }

    public void setWjbh(String wjbh) {
        this.wjbh = wjbh;
    }

    public Integer getZcjl() {
        return zcjl;
    }

    public void setZcjl(Integer zcjl) {
        this.zcjl = zcjl;
    }

    public String getQtsm() {
        return qtsm;
    }

    public void setQtsm(String qtsm) {
        this.qtsm = qtsm;
    }

    public String getCljg() {
        return cljg;
    }

    public void setCljg(String cljg) {
        this.cljg = cljg;
    }

    public Integer getZrks() {
        return zrks;
    }

    public void setZrks(Integer zrks) {
        this.zrks = zrks;
    }

    public Integer getZrfzr() {
        return zrfzr;
    }

    public void setZrfzr(Integer zrfzr) {
        this.zrfzr = zrfzr;
    }

    public Timestamp getZrtbsj() {
        return zrtbsj;
    }

    public void setZrtbsj(Timestamp zrtbsj) {
        this.zrtbsj = zrtbsj;
    }

    public String getSjzgryj() {
        return sjzgryj;
    }

    public void setSjzgryj(String sjzgryj) {
        this.sjzgryj = sjzgryj;
    }

    public Integer getSjzgbm() {
        return sjzgbm;
    }

    public void setSjzgbm(Integer sjzgbm) {
        this.sjzgbm = sjzgbm;
    }

    public Integer getSjzgfzr() {
        return sjzgfzr;
    }

    public void setSjzgfzr(Integer sjzgfzr) {
        this.sjzgfzr = sjzgfzr;
    }

    public Timestamp getSjzgtbsj() {
        return sjzgtbsj;
    }

    public void setSjzgtbsj(Timestamp sjzgtbsj) {
        this.sjzgtbsj = sjzgtbsj;
    }

    public String getYwkyj() {
        return ywkyj;
    }

    public void setYwkyj(String ywkyj) {
        this.ywkyj = ywkyj;
    }

    public Integer getSbbz() {
        return sbbz;
    }

    public void setSbbz(Integer sbbz) {
        this.sbbz = sbbz;
    }

    public Integer getShbz() {
        return shbz;
    }

    public void setShbz(Integer shbz) {
        this.shbz = shbz;
    }

    public Integer getShjg() {
        return shjg;
    }

    public void setShjg(Integer shjg) {
        this.shjg = shjg;
    }

    public Integer getFxbz() {
        return fxbz;
    }

    public void setFxbz(Integer fxbz) {
        this.fxbz = fxbz;
    }
}
