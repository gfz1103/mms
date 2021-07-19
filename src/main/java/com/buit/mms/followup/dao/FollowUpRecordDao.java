package com.buit.mms.followup.dao;

import com.buit.commons.EntityDao;
import com.buit.mms.followup.model.FollowUpRecord;
import com.buit.mms.followup.request.FollowUpRecordReq;
import com.buit.mms.followup.request.FollowUpStatisticsReq;
import com.buit.mms.followup.response.FollowUpRecordResp;
import com.buit.mms.followup.response.FollowUpStatisticsResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 随访记录表<br>
 * @author tianjunhao
 */
@Mapper
public interface FollowUpRecordDao extends EntityDao<FollowUpRecord, Integer> {
    List<FollowUpRecordResp> findFollowUpRecord(FollowUpRecordReq followUpRecordReq, @Param("sql") String sql);


    List<FollowUpStatisticsResp> statistics(FollowUpStatisticsReq followUpStatisticsReq);

    Integer countUsed(@Param("id") Integer id);
}
