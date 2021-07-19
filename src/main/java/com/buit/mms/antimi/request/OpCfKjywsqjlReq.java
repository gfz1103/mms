
package com.buit.mms.antimi.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 类名称：OpCfKjywsqjl<br>
 * 类描述：门诊_处方_抗菌药物申请记录<br>
 * @author 朱智
 */
@ApiModel(value="门诊_处方_抗菌药物申请记录入参")
public class OpCfKjywsqjlReq  extends  AmqcKjywsysqMzSaveReq{
    @ApiModelProperty(value="管控方式 1 填写使用理由/2 上级医生授权/3 填写使用申请")
    @NotBlank(message = "管控方式不能为空")
    private String gkfs;
    @NotNull(message = "就诊序号 不能为空")
    @ApiModelProperty(value="就诊序号")
    private Integer jzxh;
    @ApiModelProperty(value="管控方式为2时 填写的用户名")
    private String loginName;
    @ApiModelProperty(value="管控方式为2时 填写的密码")
    private String passWord;
    @ApiModelProperty(value="管控方式为1时 填写使用理由")
    private String usereason;


    public String getUsereason() {
        return usereason;
    }

    public void setUsereason(String usereason) {
        this.usereason = usereason;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getGkfs() {
        return gkfs;
    }

    public void setGkfs(String gkfs) {
        this.gkfs = gkfs;
    }

    public Integer getJzxh() {
        return jzxh;
    }

    public void setJzxh(Integer jzxh) {
        this.jzxh = jzxh;
    }
}
