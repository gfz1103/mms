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
public class FollowUpSz implements Serializable {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("机构ID")
    private Integer jgid;

    @ApiModelProperty("拼音码")
    private String pydm;


    @ApiModelProperty("设置内容")
    @NotNull
    private String sznr;


    @ApiModelProperty("内容类型：0-病人类型，1-随访方式，2-失访原因")
    @NotNull
    private Integer nrlx;


    @ApiModelProperty("病人类型下的动态sql")
    private String dtsql;


    @ApiModelProperty("状态：0-停用，1-启用")
    private Boolean zt;


    @ApiModelProperty("随访类型：0-自定义随访时间，1-出院后立即随访")
    private Integer sflx;

    @ApiModelProperty("开始天数单位(天，周，月，年)")
    private String kstsdw;

    @ApiModelProperty("出院随访开始天数")
    private Integer cysfksts;

    @ApiModelProperty("结束天数单位(天，周，月，年)")
    private String jstsdw;

    @ApiModelProperty("出院随访结束天数")
    private Integer cysfjsts;


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

    public String getPydm() {
        return pydm;
    }

    public void setPydm(String pydm) {
        this.pydm = pydm;
    }

    public String getSznr() {
        return sznr;
    }

    public void setSznr(String sznr) {
        this.sznr = sznr;
    }

    public Integer getNrlx() {
        return nrlx;
    }

    public void setNrlx(Integer nrlx) {
        this.nrlx = nrlx;
    }

    public String getDtsql() {
        return dtsql;
    }

    public void setDtsql(String dtsql) {
        this.dtsql = dtsql;
    }

    public Boolean getZt() {
        return zt;
    }

    public void setZt(Boolean zt) {
        this.zt = zt;
    }

    public Integer getSflx() {
        return sflx;
    }

    public void setSflx(Integer sflx) {
        this.sflx = sflx;
    }

    public String getKstsdw() {
        return kstsdw;
    }

    public void setKstsdw(String kstsdw) {
        this.kstsdw = kstsdw;
    }

    public Integer getCysfksts() {
        return cysfksts;
    }

    public void setCysfksts(Integer cysfksts) {
        this.cysfksts = cysfksts;
    }

    public String getJstsdw() {
        return jstsdw;
    }

    public void setJstsdw(String jstsdw) {
        this.jstsdw = jstsdw;
    }

    public Integer getCysfjsts() {
        return cysfjsts;
    }

    public void setCysfjsts(Integer cysfjsts) {
        this.cysfjsts = cysfjsts;
    }

    public Timestamp getCjsj() {
        return cjsj;
    }

    public void setCjsj(Timestamp cjsj) {
        this.cjsj = cjsj;
    }
}
