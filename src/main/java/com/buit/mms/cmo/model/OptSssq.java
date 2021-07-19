package com.buit.mms.cmo.model;


import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/6 10:19
 */
public class OptSssq {
    @ApiModelProperty("申请单号")
    private Integer sqdh;
    @ApiModelProperty("机构id")
    private Integer jgid;
    @ApiModelProperty("门诊号码")
    private String mzhm;
    @ApiModelProperty("手术科室")
    @NotNull
    private Integer ssks;
    @ApiModelProperty("申请科室")
    @NotNull
    private Integer sqks;
    @ApiModelProperty("申请医生")
    private Integer sqys;
    @ApiModelProperty("申请日期")
    private Timestamp sqrq;
    @NotNull
    @ApiModelProperty("手术日期")
    private Timestamp ssrq;
    @ApiModelProperty("手术内码")
    private Integer ssnm;
    @NotNull
    @ApiModelProperty("手术医生")
    private Integer ssys;
    @ApiModelProperty("手术助手1")
    private Integer ssyz;
    @ApiModelProperty("手术助手2")
    private Integer ssez;
    @ApiModelProperty("手术助手3")
    private Integer sssz;
    @NotNull
    @ApiModelProperty("麻醉代码")
    private Integer mzdm;
    @ApiModelProperty("麻醉医生")
    private Integer mzys;
    @ApiModelProperty("操作工号")
    private Integer czgh;
    @ApiModelProperty("拟手术名称")
    private String nssmc;
    @ApiModelProperty("手术级别")
    private Integer ssjb;
    @ApiModelProperty("手术备注")
    private String ssbz;
    @NotNull
    @ApiModelProperty("上级医生")
    private Integer sjys;
    @ApiModelProperty("上级意见")
    private String sjyj;
    @ApiModelProperty("住院号码")
    private String zyhm;
    @ApiModelProperty("病人id")
    private Integer brid;
    @ApiModelProperty("术前诊断")
    private String sqzd;
    @ApiModelProperty("病人科室")
    private Integer brks;
    @ApiModelProperty("申请类型")
    private Integer sqlx;
    @ApiModelProperty("住院号")
    private Integer zyh;
    @ApiModelProperty("费用序号")
    private Integer fyxh;
    @ApiModelProperty("麻醉名称")
    private String mzmc;
    @ApiModelProperty("手术名称")
    private String ssmc;
    @ApiModelProperty("就诊流水号")
    private String jzlsh;
    @ApiModelProperty("隔离标志")
    private Integer glbz;
    @ApiModelProperty("特殊手术")
    private Integer tsss;
    @ApiModelProperty("病情标志")
    private Integer bqbz;
    @ApiModelProperty("手术类型")
    private Integer sslx;
    @ApiModelProperty("手术助手4")
    private Integer sszs;
    @ApiModelProperty("审批意见")
    private String spyj;
    @ApiModelProperty("审批医生")
    private Integer spys;
    @ApiModelProperty("审批标志，医院字典：SJ0000000006")
    private Integer spbz;
    @ApiModelProperty("作废标志，标识字典：ZF000001")
    private Integer zfbz;
    @ApiModelProperty("业务标志,1:择期，2：急诊")
    @NotNull
    private Integer ywbz;
    @ApiModelProperty("重大手术标志,1：是，0：否")
    @NotNull
    private Integer zdssbz;
    @ApiModelProperty("审批时间")
    private Timestamp spsj;
    @ApiModelProperty("手术台次")
    private String sstc;
    @ApiModelProperty("术前结果")
    private String sqjg;
    @ApiModelProperty("就诊卡号")
    private String jzkh;
    @ApiModelProperty("病史摘要")
    private String bszy;
    @ApiModelProperty("后果及措施")
    private String hgjcs;
    @ApiModelProperty("患者知情同意情况")
    private String hzzqtyqk;
    @ApiModelProperty("手术部位")
    private Integer ssbw;
    @ApiModelProperty("手术体位")
    private Integer sstw;
    @ApiModelProperty("手术备血")
    private String ssbx;
    @ApiModelProperty("是否计划再手术，0：否，1：是")
    private Integer sfjhzss;
    @ApiModelProperty("重返手术，0：否，1：是")
    private Integer cfss;
    @ApiModelProperty("植入物")
    private Integer zrw;
    @ApiModelProperty("切口等级")
    private Integer qkdj;

    public Integer getSsbw() {
        return ssbw;
    }

    public void setSsbw(Integer ssbw) {
        this.ssbw = ssbw;
    }

    public Integer getSstw() {
        return sstw;
    }

    public void setSstw(Integer sstw) {
        this.sstw = sstw;
    }

    public Integer getZrw() {
        return zrw;
    }

    public void setZrw(Integer zrw) {
        this.zrw = zrw;
    }

    public String getSsbx() {
        return ssbx;
    }

    public void setSsbx(String ssbx) {
        this.ssbx = ssbx;
    }

    public Integer getSfjhzss() {
        return sfjhzss;
    }

    public void setSfjhzss(Integer sfjhzss) {
        this.sfjhzss = sfjhzss;
    }

    public Integer getCfss() {
        return cfss;
    }

    public void setCfss(Integer cfss) {
        this.cfss = cfss;
    }

    public Integer getQkdj() {
        return qkdj;
    }

    public void setQkdj(Integer qkdj) {
        this.qkdj = qkdj;
    }

    public String getHzzqtyqk() {
        return hzzqtyqk;
    }

    public void setHzzqtyqk(String hzzqtyqk) {
        this.hzzqtyqk = hzzqtyqk;
    }

    public String getBszy() {
        return bszy;
    }

    public void setBszy(String bszy) {
        this.bszy = bszy;
    }

    public String getHgjcs() {
        return hgjcs;
    }

    public void setHgjcs(String hgjcs) {
        this.hgjcs = hgjcs;
    }

    public String getJzkh() {
        return jzkh;
    }

    public void setJzkh(String jzkh) {
        this.jzkh = jzkh;
    }

    public String getSqjg() {
        return sqjg;
    }

    public void setSqjg(String sqjg) {
        this.sqjg = sqjg;
    }

    public String getSstc() {
        return sstc;
    }

    public void setSstc(String sstc) {
        this.sstc = sstc;
    }

    public Timestamp getSpsj() {
        return spsj;
    }

    public void setSpsj(Timestamp spsj) {
        this.spsj = spsj;
    }

    public Integer getZdssbz() {
        return zdssbz;
    }

    public void setZdssbz(Integer zdssbz) {
        this.zdssbz = zdssbz;
    }

    public Integer getYwbz() {
        return ywbz;
    }

    public void setYwbz(Integer ywbz) {
        this.ywbz = ywbz;
    }

    public Integer getSpbz() {
        return spbz;
    }

    public void setSpbz(Integer spbz) {
        this.spbz = spbz;
    }

    public Integer getZfbz() {
        return zfbz;
    }

    public void setZfbz(Integer zfbz) {
        this.zfbz = zfbz;
    }

    public String getSjyj() {
        return sjyj;
    }

    public void setSjyj(String sjyj) {
        this.sjyj = sjyj;
    }

    public Integer getSqdh() {
        return sqdh;
    }

    public void setSqdh(Integer sqdh) {
        this.sqdh = sqdh;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getMzhm() {
        return mzhm;
    }

    public void setMzhm(String mzhm) {
        this.mzhm = mzhm;
    }

    public Integer getSsks() {
        return ssks;
    }

    public void setSsks(Integer ssks) {
        this.ssks = ssks;
    }

    public Integer getSqks() {
        return sqks;
    }

    public void setSqks(Integer sqks) {
        this.sqks = sqks;
    }

    public Integer getSqys() {
        return sqys;
    }

    public void setSqys(Integer sqys) {
        this.sqys = sqys;
    }

    public Timestamp getSqrq() {
        return sqrq;
    }

    public void setSqrq(Timestamp sqrq) {
        this.sqrq = sqrq;
    }

    public Timestamp getSsrq() {
        return ssrq;
    }

    public void setSsrq(Timestamp ssrq) {
        this.ssrq = ssrq;
    }

    public Integer getSsnm() {
        return ssnm;
    }

    public void setSsnm(Integer ssnm) {
        this.ssnm = ssnm;
    }

    public Integer getSsys() {
        return ssys;
    }

    public void setSsys(Integer ssys) {
        this.ssys = ssys;
    }

    public Integer getSsyz() {
        return ssyz;
    }

    public void setSsyz(Integer ssyz) {
        this.ssyz = ssyz;
    }

    public Integer getSsez() {
        return ssez;
    }

    public void setSsez(Integer ssez) {
        this.ssez = ssez;
    }

    public Integer getSssz() {
        return sssz;
    }

    public void setSssz(Integer sssz) {
        this.sssz = sssz;
    }

    public Integer getMzdm() {
        return mzdm;
    }

    public void setMzdm(Integer mzdm) {
        this.mzdm = mzdm;
    }

    public Integer getMzys() {
        return mzys;
    }

    public void setMzys(Integer mzys) {
        this.mzys = mzys;
    }

    public Integer getCzgh() {
        return czgh;
    }

    public void setCzgh(Integer czgh) {
        this.czgh = czgh;
    }

    public String getNssmc() {
        return nssmc;
    }

    public void setNssmc(String nssmc) {
        this.nssmc = nssmc;
    }

    public Integer getSsjb() {
        return ssjb;
    }

    public void setSsjb(Integer ssjb) {
        this.ssjb = ssjb;
    }

    public String getSsbz() {
        return ssbz;
    }

    public void setSsbz(String ssbz) {
        this.ssbz = ssbz;
    }

    public Integer getSjys() {
        return sjys;
    }

    public void setSjys(Integer sjys) {
        this.sjys = sjys;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public Integer getBrid() {
        return brid;
    }

    public void setBrid(Integer brid) {
        this.brid = brid;
    }

    public String getSqzd() {
        return sqzd;
    }

    public void setSqzd(String sqzd) {
        this.sqzd = sqzd;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public Integer getSqlx() {
        return sqlx;
    }

    public void setSqlx(Integer sqlx) {
        this.sqlx = sqlx;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Integer getFyxh() {
        return fyxh;
    }

    public void setFyxh(Integer fyxh) {
        this.fyxh = fyxh;
    }

    public String getMzmc() {
        return mzmc;
    }

    public void setMzmc(String mzmc) {
        this.mzmc = mzmc;
    }

    public String getSsmc() {
        return ssmc;
    }

    public void setSsmc(String ssmc) {
        this.ssmc = ssmc;
    }

    public String getJzlsh() {
        return jzlsh;
    }

    public void setJzlsh(String jzlsh) {
        this.jzlsh = jzlsh;
    }

    public Integer getGlbz() {
        return glbz;
    }

    public void setGlbz(Integer glbz) {
        this.glbz = glbz;
    }

    public Integer getTsss() {
        return tsss;
    }

    public void setTsss(Integer tsss) {
        this.tsss = tsss;
    }

    public Integer getBqbz() {
        return bqbz;
    }

    public void setBqbz(Integer bqbz) {
        this.bqbz = bqbz;
    }

    public Integer getSslx() {
        return sslx;
    }

    public void setSslx(Integer sslx) {
        this.sslx = sslx;
    }

    public Integer getSszs() {
        return sszs;
    }

    public void setSszs(Integer sszs) {
        this.sszs = sszs;
    }

    public String getSpyj() {
        return spyj;
    }

    public void setSpyj(String spyj) {
        this.spyj = spyj;
    }

    public Integer getSpys() {
        return spys;
    }

    public void setSpys(Integer spys) {
        this.spys = spys;
    }
}
