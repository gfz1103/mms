package com.buit.mms.cmo.request;

import com.buit.commons.PageQuery;
import com.buit.mms.cmo.enums.OperationTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/9 11:19
 */
public class QueryExamineListReq extends PageQuery {

    @NotNull
    @ApiModelProperty("手术类型,major:重大，special:特殊")
    private OperationTypeEnum type;
    @ApiModelProperty("申请科室")
    private Integer sqks;
    @ApiModelProperty("审批状态，1：已审批，0：未审批，-1：全部")
    private Integer spbz;

    public OperationTypeEnum getType() {
        return type;
    }

    public void setType(OperationTypeEnum type) {
        this.type = type;
    }

    public Integer getSqks() {
        return sqks;
    }

    public void setSqks(Integer sqks) {
        this.sqks = sqks;
    }

    public Integer getSpbz() {
        return spbz;
    }

    public void setSpbz(Integer spbz) {
        this.spbz = spbz;
    }

}
