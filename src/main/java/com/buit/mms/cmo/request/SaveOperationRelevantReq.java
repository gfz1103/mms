package com.buit.mms.cmo.request;

import com.buit.mms.cmo.enums.RelevantTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/5 16:55
 */
public class SaveOperationRelevantReq {
    private Integer id;
    @NotBlank
    private String name;
    private String pydm;
    private String wbdm;
    @NotBlank
    private Integer status;
    @NotNull
    private RelevantTypeEnum relevant;

    public RelevantTypeEnum getRelevant() {
        return relevant;
    }

    public void setRelevant(RelevantTypeEnum relevant) {
        this.relevant = relevant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPydm() {
        return pydm;
    }

    public void setPydm(String pydm) {
        this.pydm = pydm;
    }

    public String getWbdm() {
        return wbdm;
    }

    public void setWbdm(String wbdm) {
        this.wbdm = wbdm;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
