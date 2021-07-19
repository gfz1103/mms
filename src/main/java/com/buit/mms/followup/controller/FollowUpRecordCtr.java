package com.buit.mms.followup.controller;

import cn.hutool.core.date.DateUtil;
import com.buit.commons.BaseSpringController;
import com.buit.mms.followup.model.FollowUpRecord;
import com.buit.mms.followup.request.FollowUpRecordReq;
import com.buit.mms.followup.request.FollowUpStatisticsReq;
import com.buit.mms.followup.response.FollowUpRecordResp;
import com.buit.mms.followup.response.FollowUpStatisticsResp;
import com.buit.mms.followup.service.FollowUpRecordSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.sql.Timestamp;

/**
 * 随访记录接口<br>
 * @author tianjunhao
 */
@Api(tags="随访记录接口")
@Controller
@RequestMapping("/record")
public class FollowUpRecordCtr extends BaseSpringController {

    @Autowired
    FollowUpRecordSer followUpRecordSer;

    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value="新增随访记录" ,httpMethod="POST")
    public ReturnEntity addFollowUpSz(@Valid FollowUpRecord followUpRecord){
        followUpRecord.setCjsj(Timestamp.valueOf(DateUtil.now()));
        followUpRecord.setJgid(getUser().getHospitalId());
        followUpRecordSer.insert(followUpRecord);
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/queryByConditions")
    @ResponseBody
    @ApiOperation(value="条件查询随访记录" ,httpMethod="POST")
    public ReturnEntity<PageInfo<FollowUpRecordResp>> queryByConditions(@Valid FollowUpRecordReq followUpRecordReq){
        followUpRecordReq.setJgid(getUser().getHospitalId());
        PageInfo<FollowUpRecordResp> pageInfo =  followUpRecordSer.queryByConditions(followUpRecordReq);
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/statistics")
    @ResponseBody
    @ApiOperation(value="随访记录统计" ,httpMethod="POST")
    public ReturnEntity<PageInfo<FollowUpStatisticsResp>> statistics(@Valid FollowUpStatisticsReq followUpStatisticsReq){
        followUpStatisticsReq.setJgid(getUser().getHospitalId());
        return ReturnEntityUtil.success(followUpRecordSer.statistics(followUpStatisticsReq));
    }

}
