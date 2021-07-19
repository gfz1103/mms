package com.buit.mms.safety.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/29 10:38
 */
public class ReportAnalysisSaveReq {

    @NotNull
    private Integer id;
    @NotBlank
    private String ywkyj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYwkyj() {
        return ywkyj;
    }

    public void setYwkyj(String ywkyj) {
        this.ywkyj = ywkyj;
    }
}
