package com.buit.mms.cmo.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/10 15:46
 */
public class CmoZdss {

    @ApiModelProperty("手术编码")
    private Integer ssbm;
    @ApiModelProperty("手术名称")
    private String ssmc;
    @ApiModelProperty("icd编码")
    private String icd;
    @ApiModelProperty("手术等级")
    private Integer ssdj;
    @ApiModelProperty("拼音代码")
    private String pydm;

    public String getPydm() {
        return pydm;
    }

    public void setPydm(String pydm) {
        this.pydm = pydm;
    }

    public Integer getSsbm() {
        return ssbm;
    }

    public void setSsbm(Integer ssbm) {
        this.ssbm = ssbm;
    }

    public String getSsmc() {
        return ssmc;
    }

    public void setSsmc(String ssmc) {
        this.ssmc = ssmc;
    }

    public String getIcd() {
        return icd;
    }

    public void setIcd(String icd) {
        this.icd = icd;
    }

    public Integer getSsdj() {
        return ssdj;
    }

    public void setSsdj(Integer ssdj) {
        this.ssdj = ssdj;
    }
}
