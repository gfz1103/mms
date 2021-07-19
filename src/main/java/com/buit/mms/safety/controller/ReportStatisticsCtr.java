package com.buit.mms.safety.controller;

import com.buit.commons.BaseSpringController;
import com.buit.mms.safety.request.ReportStatisticsReq;
import com.buit.mms.safety.response.ReportStatisticsResp;
import com.buit.mms.safety.service.ReportStatisticsSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/29 13:37
 */
@Api(tags="不良事件统计")
@Controller
@RequestMapping(value = "/report/statistics",method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportStatisticsCtr extends BaseSpringController {

    @Autowired
    ReportStatisticsSer reportStatisticsSer;

    @ApiOperation("报告表统计")
    @RequestMapping("/query")
    @ResponseBody
    public ReturnEntity<List<ReportStatisticsResp>> statistics(@Valid ReportStatisticsReq req){

        return ReturnEntityUtil.success(reportStatisticsSer.statistics(req,getUser()));
    }
}
