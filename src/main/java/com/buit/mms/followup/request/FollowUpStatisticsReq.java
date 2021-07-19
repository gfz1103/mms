package com.buit.mms.followup.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author tianjunhao
 * @Date 2020/12/17 9:28
 */
public class FollowUpStatisticsReq extends PageQuery {
    @NotNull
    @ApiModelProperty("开始时间")
    private String kssj;
    @NotNull
    @ApiModelProperty("结束时间")
    private String jssj;
    @ApiModelProperty("机构ID")
    private Integer jgid;

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }
}
