package com.buit.mms.followup.response;

import com.buit.mms.followup.model.FollowUpRecord;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * @Description
 * @Author tianjunhao
 * @Date 2020/12/16 14:28
 */
public class FollowUpRecordResp extends FollowUpRecord implements Serializable {
    @ApiModelProperty("病人姓名")
    private String brxm;
    @ApiModelProperty("病人性别 DIC_GBSJ01:PD0000000260")
    private Integer brxb;
    @ApiModelProperty("病人年龄")
    private Integer age;
    @ApiModelProperty("病人年龄(具体)")
    private String ages;
    @ApiModelProperty("出生年月")
    private Timestamp csny;
    @ApiModelProperty("住院号 | 该住院号为内码")
    private Integer zyh;
    @ApiModelProperty("目前诊断")
    private String mqzd;
    @ApiModelProperty("出院科室")
    private String cyks;
    @ApiModelProperty("出院日期 | 病区办理出院证明的日期(可以提前或推后)")
    private Timestamp cyrq;
    @ApiModelProperty("出院方式 |与GY_DMZD（DMLB= 23）对应 1：治愈 2：好转 3：未愈 5：死亡 ：其他")
    private Integer cyfs;
    @ApiModelProperty("联系电话")
    private String lxdh;

    @ApiModelProperty("回访人名称")
    private String hfrmc;
    @ApiModelProperty("随访方式内容")
    private String sffsnr;
    @ApiModelProperty("失访原因内容")
    private String sfyynr;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public Timestamp getCsny() {
        return csny;
    }

    public void setCsny(Timestamp csny) {
        this.csny = csny;
    }

    @Override
    public Integer getZyh() {
        return zyh;
    }

    @Override
    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public String getMqzd() {
        return mqzd;
    }

    public void setMqzd(String mqzd) {
        this.mqzd = mqzd;
    }

    public String getCyks() {
        return cyks;
    }

    public void setCyks(String cyks) {
        this.cyks = cyks;
    }

    public Timestamp getCyrq() {
        return cyrq;
    }

    public void setCyrq(Timestamp cyrq) {
        this.cyrq = cyrq;
    }

    public Integer getCyfs() {
        return cyfs;
    }

    public void setCyfs(Integer cyfs) {
        this.cyfs = cyfs;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getHfrmc() {
        return hfrmc;
    }

    public void setHfrmc(String hfrmc) {
        this.hfrmc = hfrmc;
    }

    public String getSffsnr() {
        return sffsnr;
    }

    public void setSffsnr(String sffsnr) {
        this.sffsnr = sffsnr;
    }

    public String getSfyynr() {
        return sfyynr;
    }

    public void setSfyynr(String sfyynr) {
        this.sfyynr = sfyynr;
    }
}
