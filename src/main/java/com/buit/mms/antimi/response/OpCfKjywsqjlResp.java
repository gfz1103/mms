package com.buit.mms.antimi.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;


/**
 * 类名称：OpCfKjywsqjl<br>
 * 类描述：门诊_处方_抗菌药物申请记录<br>
 * @author 韦靖
 */
@ApiModel(value="门诊_处方_抗菌药物申请记录")
public class OpCfKjywsqjlResp  extends  PageQuery{
    @ApiModelProperty(value="主键")
    private Integer id;
    @ApiModelProperty(value="药品序号")
    private Integer ypxh;
    @ApiModelProperty(value="药品名称")
    private String ypmc;
    @ApiModelProperty(value="药品用法")
    private Integer ypyf;
    @ApiModelProperty(value="天数")
    private Integer ts;
    @ApiModelProperty(value="管控方式为2时 填写")
    private String loginname;
    @ApiModelProperty(value="管控方式为2时 填写")
    private String password;
    @ApiModelProperty(value="管控方式为1时 填写")
    private String usereason;
    @ApiModelProperty(value="申请日期")
    private Date sqrq;
    @ApiModelProperty(value="抗菌药物申请单ID")
    private Integer sqdh;
    @ApiModelProperty(value="状态 0：新单、1：已审核、2：会诊中、3：会诊完成")
    private Integer zt;
    @ApiModelProperty(value="管控方式 1 填写使用理由/2 上级医生授权/3 填写使用申请")
    private String gkfs;
    @ApiModelProperty(value="就诊序号")
    private Integer jzxh;

    public Integer getYpyf() {
        return ypyf;
    }

    public void setYpyf(Integer ypyf) {
        this.ypyf = ypyf;
    }

    /**
     * 设置:主键
     */
    public void setId(Integer value) {
        this.id = value;
    }
    /**
     * 获取:主键
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置:药品序号
     */
    public void setYpxh(Integer value) {
        this.ypxh = value;
    }
    /**
     * 获取:药品序号
     */
    public Integer getYpxh() {
        return ypxh;
    }
    /**
     * 设置:药品名称
     */
    public void setYpmc(String value) {
        this.ypmc = value;
    }
    /**
     * 获取:药品名称
     */
    public String getYpmc() {
        return ypmc;
    }
    /**
     * 设置:天数
     */
    public void setTs(Integer value) {
        this.ts = value;
    }
    /**
     * 获取:天数
     */
    public Integer getTs() {
        return ts;
    }
    /**
     * 设置:管控方式为2时 填写
     */
    public void setLoginname(String value) {
        this.loginname = value;
    }
    /**
     * 获取:管控方式为2时 填写
     */
    public String getLoginname() {
        return loginname;
    }
    /**
     * 设置:管控方式为2时 填写
     */
    public void setPassword(String value) {
        this.password = value;
    }
    /**
     * 获取:管控方式为2时 填写
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置:管控方式为1时 填写
     */
    public void setUsereason(String value) {
        this.usereason = value;
    }
    /**
     * 获取:管控方式为1时 填写
     */
    public String getUsereason() {
        return usereason;
    }
    /**
     * 设置:申请日期
     */
    public void setSqrq(Date value) {
        this.sqrq = value;
    }
    /**
     * 获取:申请日期
     */
    public Date getSqrq() {
        return sqrq;
    }
    /**
     * 设置:抗菌药物申请单ID
     */
    public void setSqdh(Integer value) {
        this.sqdh = value;
    }
    /**
     * 获取:抗菌药物申请单ID
     */
    public Integer getSqdh() {
        return sqdh;
    }
    /**
     * 设置:状态 0：新单、1：已审核、2：会诊中、3：会诊完成
     */
    public void setZt(Integer value) {
        this.zt = value;
    }
    /**
     * 获取:状态 0：新单、1：已审核、2：会诊中、3：会诊完成
     */
    public Integer getZt() {
        return zt;
    }
    /**
     * 设置:管控方式 1 填写使用理由/2 上级医生授权/3 填写使用申请
     */
    public void setGkfs(String value) {
        this.gkfs = value;
    }
    /**
     * 获取:管控方式 1 填写使用理由/2 上级医生授权/3 填写使用申请
     */
    public String getGkfs() {
        return gkfs;
    }
    /**
     * 设置:就诊序号
     */
    public void setJzxh(Integer value) {
        this.jzxh = value;
    }
    /**
     * 获取:就诊序号
     */
    public Integer getJzxh() {
        return jzxh;
    }
}
