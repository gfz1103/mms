package com.buit.mms.cmo.dao;

import com.buit.mms.cmo.response.OperationSelectorResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/11 15:17
 */
@Mapper
public interface FeeYlsfxmDao {

    List<OperationSelectorResp> ssxmSelector(String pydm);
}
