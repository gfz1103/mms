package com.buit.mms.safety.response;

import com.buit.mms.safety.model.AdvBgdj;
import io.swagger.annotations.ApiModelProperty;


/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 13:23
 */
public class ReportInfoResp extends AdvBgdj {
    @ApiModelProperty("事件性质名称")
    private String sjxzmc;

    public String getSjxzmc() {
        return sjxzmc;
    }

    public void setSjxzmc(String sjxzmc) {
        this.sjxzmc = sjxzmc;
    }
}
