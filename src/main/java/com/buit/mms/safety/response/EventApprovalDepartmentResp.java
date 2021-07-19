package com.buit.mms.safety.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 10:06
 */
public class EventApprovalDepartmentResp {

    private Integer id;
    private String sjmc;
    @ApiModelProperty("当shks=0时，表示上报科室审核，当shks!=0时，表示指定的科室审核，shks为指定的科室id")
    private Integer shks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSjmc() {
        return sjmc;
    }

    public void setSjmc(String sjmc) {
        this.sjmc = sjmc;
    }

    public Integer getShks() {
        return shks;
    }

    public void setShks(Integer shks) {
        this.shks = shks;
    }
}
