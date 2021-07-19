package com.buit.mms.cmo.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/9 10:59
 */
public class ExamineOperationApplyReq {

    @NotNull
    private Integer sqdh;
    @NotBlank
    private String spyj;
    @ApiModelProperty(hidden = true)
    private Integer spys;
    @NotNull
    private Timestamp spsj;

    public Integer getSqdh() {
        return sqdh;
    }

    public void setSqdh(Integer sqdh) {
        this.sqdh = sqdh;
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

    public Timestamp getSpsj() {
        return spsj;
    }

    public void setSpsj(Timestamp spsj) {
        this.spsj = spsj;
    }
}
