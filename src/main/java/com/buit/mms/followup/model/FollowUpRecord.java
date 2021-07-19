package com.buit.mms.followup.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description
 * @Author tianjunhao
 * @Date 2020/12/16 14:28
 */
public class FollowUpRecord implements Serializable {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty("机构ID")
    private Integer jgid;

    @ApiModelProperty("住院号")
    @NotNull
    private Integer zyh;


    @ApiModelProperty("随访状态：0-失访，1-完成随访")
    @NotNull
    private Boolean sfzt;


    @ApiModelProperty("回访时间")
    private Timestamp hfsj;


    @ApiModelProperty("回访人")
    private Integer hfr;


    @ApiModelProperty("随访方式")
    private Integer sffs;


    @ApiModelProperty("总体情况：0-不满意，1-满意")
    private Boolean ztqk;


    @ApiModelProperty("意见和建议")
    private String yjhjy;


    @ApiModelProperty("处理情况")
    private String clqk;


    @ApiModelProperty("失访原因")
    private Integer sfyy;


    @ApiModelProperty("创建时间")
    private Timestamp cjsj;

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

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Boolean getSfzt() {
        return sfzt;
    }

    public void setSfzt(Boolean sfzt) {
        this.sfzt = sfzt;
    }

    public Timestamp getHfsj() {
        return hfsj;
    }

    public void setHfsj(Timestamp hfsj) {
        this.hfsj = hfsj;
    }

    public Integer getHfr() {
        return hfr;
    }

    public void setHfr(Integer hfr) {
        this.hfr = hfr;
    }

    public Integer getSffs() {
        return sffs;
    }

    public void setSffs(Integer sffs) {
        this.sffs = sffs;
    }

    public Boolean getZtqk() {
        return ztqk;
    }

    public void setZtqk(Boolean ztqk) {
        this.ztqk = ztqk;
    }

    public String getYjhjy() {
        return yjhjy;
    }

    public void setYjhjy(String yjhjy) {
        this.yjhjy = yjhjy;
    }

    public String getClqk() {
        return clqk;
    }

    public void setClqk(String clqk) {
        this.clqk = clqk;
    }

    public Integer getSfyy() {
        return sfyy;
    }

    public void setSfyy(Integer sfyy) {
        this.sfyy = sfyy;
    }

    public Timestamp getCjsj() {
        return cjsj;
    }

    public void setCjsj(Timestamp cjsj) {
        this.cjsj = cjsj;
    }
}
