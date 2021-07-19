package com.buit.mms.safety.dao;

import com.buit.his.pha.commons.dto.QueryConditionDto;
import com.buit.mms.safety.model.AdvBgdj;
import com.buit.mms.safety.request.ApprovalReportReq;
import com.buit.mms.safety.request.ReportAnalysisSaveReq;
import com.buit.mms.safety.request.ReportStatisticsReq;
import com.buit.mms.safety.request.SaveReportInfoReq;
import com.buit.mms.safety.response.ReportInfoResp;
import com.buit.mms.safety.response.ReportStatisticsResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 13:56
 */
@Mapper
public interface AdvBgdjDao {


    List<ReportInfoResp> queryListByCond(@Param("cond") QueryConditionDto cond);

    void insert(SaveReportInfoReq req);

    void update(SaveReportInfoReq req);

    void updateStatusById(@Param("id") Integer id, @Param("column") String column, @Param("status") int status);

    AdvBgdj getById(@Param("id") Integer id);

    void delete(@Param("id") Integer id);

    void approval(ApprovalReportReq req);

    void analysis(ReportAnalysisSaveReq req);

    List<ReportStatisticsResp> countStatistics(ReportStatisticsReq req);
}
