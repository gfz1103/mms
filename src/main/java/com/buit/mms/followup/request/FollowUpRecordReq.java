package com.buit.mms.followup.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
/**
 * @Description
 * @Author tianjunhao
 * @Date 2020/12/16 14:28
 */
public class FollowUpRecordReq extends PageQuery {
    @ApiModelProperty("科室ID")
    private Integer ksid;
    @ApiModelProperty("开始时间")
    private String kssj;
    @ApiModelProperty("结束时间")
    private String jssj;
    @ApiModelProperty("病人类型ID")
    private Integer brlxid;
    @NotNull
    @ApiModelProperty("查询类型:0-未随访,1-已随访,2-完成随访")
    private Integer cxlx;
    @ApiModelProperty("总体情况:不传参数-全部, 0-不满意, 1-满意")
    private Integer ztqk;
    @ApiModelProperty("机构ID")
    private Integer jgid;

    public Integer getKsid() {
        return ksid;
    }

    public void setKsid(Integer ksid) {
        this.ksid = ksid;
    }

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

    public Integer getBrlxid() {
        return brlxid;
    }

    public void setBrlxid(Integer brlxid) {
        this.brlxid = brlxid;
    }

    public Integer getCxlx() {
        return cxlx;
    }

    public void setCxlx(Integer cxlx) {
        this.cxlx = cxlx;
    }

    public Integer getZtqk() {
        return ztqk;
    }

    public void setZtqk(Integer ztqk) {
        this.ztqk = ztqk;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }
}
