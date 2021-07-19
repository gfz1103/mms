package com.buit.mms.followup.service;

import cn.hutool.core.date.*;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.EntityDao;
import com.buit.mms.followup.dao.FollowUpRecordDao;
import com.buit.mms.followup.dao.FollowUpSzDao;
import com.buit.mms.followup.enmu.DateUnit;
import com.buit.mms.followup.model.FollowUpRecord;
import com.buit.mms.followup.model.FollowUpSz;
import com.buit.mms.followup.request.FollowUpRecordReq;
import com.buit.mms.followup.request.FollowUpStatisticsReq;
import com.buit.mms.followup.response.FollowUpRecordResp;
import com.buit.mms.followup.response.FollowUpStatisticsResp;
import com.buit.utill.BUHISUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 随访记录service<br>
 * @author tianjunhao
 */
@Service
public class FollowUpRecordSer extends BaseManagerImp<FollowUpRecord, Integer> {

    @Autowired
    FollowUpRecordDao followUpRecordDao;
    @Autowired
    FollowUpSzDao followUpSzDao;

    @Override
    public EntityDao<FollowUpRecord, Integer> getEntityMapper() {
        return followUpRecordDao;
    }

    public PageInfo<FollowUpRecordResp> queryByConditions(FollowUpRecordReq followUpRecordReq){
        String sql = null;
        if(Objects.nonNull(followUpRecordReq.getKssj()) && Objects.nonNull(followUpRecordReq.getJssj())) {
            followUpRecordReq.setKssj(beginOfDay(followUpRecordReq.getKssj()));
            followUpRecordReq.setJssj(endOfDay(followUpRecordReq.getJssj()));
        }
        if(Objects.nonNull(followUpRecordReq.getBrlxid())){
            FollowUpSz followUpSz = followUpSzDao.getById(followUpRecordReq.getBrlxid());
            sql = followUpSz.getDtsql();
            if(followUpRecordReq.getCxlx() == 0 && followUpSz.getSflx() == 0) {
                DateTime nowDate = DateUtil.date(System.currentTimeMillis());
                Date newDate = DateUtil.offset(nowDate, DateField.DAY_OF_MONTH,
                        followUpSz.getCysfksts() * DateUnit.getDays(followUpSz.getKstsdw()) * -1);
                followUpRecordReq.setJssj(newDate.toString());
            }
        }
        String finalSql = sql;
        PageInfo<FollowUpRecordResp> pageInfo = PageHelper.startPage(
                followUpRecordReq.getPageNum(), followUpRecordReq.getPageSize()).doSelectPageInfo(
                () -> followUpRecordDao.findFollowUpRecord(followUpRecordReq, finalSql)
        );
        List<FollowUpRecordResp> resultList = pageInfo.getList();
        resultList.forEach(o->{
            Map<String, Object> agMap = BUHISUtil.getPersonAge(o.getCsny(), null);
            if (!agMap.isEmpty()) {
                o.setAge(Integer.valueOf(agMap.get("age").toString()));
                o.setAges(agMap.get("ages").toString());
            }
        });
        pageInfo.setList(resultList);
        return pageInfo;
    }


    public PageInfo<FollowUpStatisticsResp> statistics(FollowUpStatisticsReq followUpStatisticsReq) {
        followUpStatisticsReq.setKssj(beginOfDay(followUpStatisticsReq.getKssj()));
        followUpStatisticsReq.setJssj(endOfDay(followUpStatisticsReq.getJssj()));
        return PageHelper.startPage(
                followUpStatisticsReq.getPageNum(), followUpStatisticsReq.getPageSize()).doSelectPageInfo(
                () -> followUpRecordDao.statistics(followUpStatisticsReq)
        );
    }

    private String beginOfDay(String date){
        return DateUtil.beginOfDay(DateTime.of(date, DatePattern.NORM_DATE_PATTERN)).toString();
    }

    private String endOfDay(String date){
        return DateUtil.endOfDay(DateTime.of(date, DatePattern.NORM_DATE_PATTERN)).toString();
    }
}
