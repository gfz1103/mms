package com.buit.mms.cmo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.InputStream;
import java.sql.Timestamp;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/13 9:38
 */
public class OperationApplyParamDto {

    private String brxm;
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Timestamp csny;
    private String csnyFormat;
    private String brxb;
    private Integer brnl;
    private String brks;
    private String brbq;
    private String brch;
    private Integer zyh;
    private String sqks;
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Timestamp sqrq;
    private String sqrqFormat;
    @JsonFormat(pattern = "yyyy年MM月dd日HH时mm分")
    private Timestamp ssrq;
    private String ssrqFormat;
    private String ssjb;
    private String ssmc;
    private String mzmc;
    private String sqjg;
    private String hzzq;
    private String szjzc;
    private String zsjzc;
    private String spyj;
    private String spys;
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Timestamp spsj;
    private String spsjFormat;
    private String tsss;
    private String hzzqtyqk;
    private String hgjcs;
    private String bszy;
    private String logoUrl;

    public String getCsnyFormat() {
        return csnyFormat;
    }

    public void setCsnyFormat(String csnyFormat) {
        this.csnyFormat = csnyFormat;
    }

    public String getSqrqFormat() {
        return sqrqFormat;
    }

    public void setSqrqFormat(String sqrqFormat) {
        this.sqrqFormat = sqrqFormat;
    }

    public String getSsrqFormat() {
        return ssrqFormat;
    }

    public void setSsrqFormat(String ssrqFormat) {
        this.ssrqFormat = ssrqFormat;
    }

    public String getSpsjFormat() {
        return spsjFormat;
    }

    public void setSpsjFormat(String spsjFormat) {
        this.spsjFormat = spsjFormat;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getHgjcs() {
        return hgjcs;
    }

    public void setHgjcs(String hgjcs) {
        this.hgjcs = hgjcs;
    }

    public String getBszy() {
        return bszy;
    }

    public void setBszy(String bszy) {
        this.bszy = bszy;
    }

    public String getHzzqtyqk() {
        return hzzqtyqk;
    }

    public void setHzzqtyqk(String hzzqtyqk) {
        this.hzzqtyqk = hzzqtyqk;
    }

    public String getTsss() {
        return tsss;
    }

    public void setTsss(String tsss) {
        this.tsss = tsss;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public Timestamp getCsny() {
        return csny;
    }

    public void setCsny(Timestamp csny) {
        this.csny = csny;
    }

    public String getBrxb() {
        return brxb;
    }

    public void setBrxb(String brxb) {
        this.brxb = brxb;
    }

    public Integer getBrnl() {
        return brnl;
    }

    public void setBrnl(Integer brnl) {
        this.brnl = brnl;
    }

    public String getBrks() {
        return brks;
    }

    public void setBrks(String brks) {
        this.brks = brks;
    }

    public String getBrbq() {
        return brbq;
    }

    public void setBrbq(String brbq) {
        this.brbq = brbq;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public String getSqks() {
        return sqks;
    }

    public void setSqks(String sqks) {
        this.sqks = sqks;
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

    public String getSsjb() {
        return ssjb;
    }

    public void setSsjb(String ssjb) {
        this.ssjb = ssjb;
    }

    public String getSsmc() {
        return ssmc;
    }

    public void setSsmc(String ssmc) {
        this.ssmc = ssmc;
    }

    public String getMzmc() {
        return mzmc;
    }

    public void setMzmc(String mzmc) {
        this.mzmc = mzmc;
    }

    public String getSqjg() {
        return sqjg;
    }

    public void setSqjg(String sqjg) {
        this.sqjg = sqjg;
    }

    public String getHzzq() {
        return hzzq;
    }

    public void setHzzq(String hzzq) {
        this.hzzq = hzzq;
    }

    public String getSzjzc() {
        return szjzc;
    }

    public void setSzjzc(String szjzc) {
        this.szjzc = szjzc;
    }

    public String getZsjzc() {
        return zsjzc;
    }

    public void setZsjzc(String zsjzc) {
        this.zsjzc = zsjzc;
    }

    public String getSpyj() {
        return spyj;
    }

    public void setSpyj(String spyj) {
        this.spyj = spyj;
    }

    public String getSpys() {
        return spys;
    }

    public void setSpys(String spys) {
        this.spys = spys;
    }

    public Timestamp getSpsj() {
        return spsj;
    }

    public void setSpsj(Timestamp spsj) {
        this.spsj = spsj;
    }
}
