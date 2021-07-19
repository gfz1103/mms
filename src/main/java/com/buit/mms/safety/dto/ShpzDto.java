package com.buit.mms.safety.dto;

import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 11:09
 */
public class ShpzDto {

    @NotNull
    private Integer id;
    @NotNull
    private Integer shks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShks() {
        return shks;
    }

    public void setShks(Integer shks) {
        this.shks = shks;
    }
}
