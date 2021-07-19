package com.buit.mms.safety.dao;

import com.buit.mms.safety.response.EventApprovalDepartmentResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 10:49
 */
@Mapper
public interface AdvPropDao {

    List<EventApprovalDepartmentResp> queryApprovalConfig(@Param("jgid") Integer jgid);
}
