package com.buit.mms.cmo.request;

import com.buit.commons.PageQuery;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/11 15:08
 */
public class OperationSelectorReq extends PageQuery {

    private String pydm;

    public String getPydm() {
        return pydm;
    }

    public void setPydm(String pydm) {
        this.pydm = pydm;
    }
}
