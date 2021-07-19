package com.buit.mms.followup.dao;

import com.buit.commons.EntityDao;
import com.buit.mms.followup.model.FollowUpSz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 随访设置表<br>
 * @author tianjunhao
 */
@Mapper
public interface FollowUpSzDao extends EntityDao<FollowUpSz, Integer> {
}
