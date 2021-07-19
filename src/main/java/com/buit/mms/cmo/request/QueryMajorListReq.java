package com.buit.mms.cmo.request;

import com.buit.commons.PageQuery;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/10 16:07
 */
public class QueryMajorListReq extends PageQuery {

    private String pydm;

    public String getPydm() {
        return pydm;
    }

    public void setPydm(String pydm) {
        this.pydm = pydm;
    }
}
