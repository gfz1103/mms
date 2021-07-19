package com.buit.mms.safety.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 16:49
 */
public class ApprovalReportReq {

    @NotNull
    private Integer id;
    @NotNull
    @ApiModelProperty("审核结果")
    private Integer shjg;
    @NotNull
    @ApiModelProperty("审核意见")
    private String shyj;
    @NotNull
    @ApiModelProperty("科室id")
    private Integer ksid;
    @ApiModelProperty(hidden = true)
    private Integer shgh;
    @ApiModelProperty(hidden = true)
    private Timestamp shsj;

    public Integer getShgh() {
        return shgh;
    }

    public void setShgh(Integer shgh) {
        this.shgh = shgh;
    }

    public Timestamp getShsj() {
        return shsj;
    }

    public void setShsj(Timestamp shsj) {
        this.shsj = shsj;
    }

    public Integer getKsid() {
        return ksid;
    }

    public void setKsid(Integer ksid) {
        this.ksid = ksid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShjg() {
        return shjg;
    }

    public void setShjg(Integer shjg) {
        this.shjg = shjg;
    }

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }
}
