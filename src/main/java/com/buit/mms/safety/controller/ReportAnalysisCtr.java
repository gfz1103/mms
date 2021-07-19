package com.buit.mms.safety.controller;

import com.buit.commons.BaseSpringController;
import com.buit.mms.safety.request.ReportAnalysisReq;
import com.buit.mms.safety.request.ReportAnalysisSaveReq;
import com.buit.mms.safety.response.ReportInfoResp;
import com.buit.mms.safety.service.ReportAnalysisSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/29 10:07
 */
@Api(tags="不良事件分析")
@Controller
@RequestMapping(value = "/report/analysis",method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportAnalysisCtr extends BaseSpringController {

    @Autowired
    ReportAnalysisSer reportAnalysisSer;

    @ApiOperation("查询报告登记列表")
    @RequestMapping("/query/list")
    @ResponseBody
    public ReturnEntity<PageInfo<ReportInfoResp>> queryList(@Valid ReportAnalysisReq req){

        return ReturnEntityUtil.success(reportAnalysisSer.queryList(req,getUser()));
    }

    @ApiOperation("分析")
    @RequestMapping("/")
    @ResponseBody
    public ReturnEntity save(@Valid ReportAnalysisSaveReq req){
        reportAnalysisSer.save(req,getUser());
        return ReturnEntityUtil.success();
    }
}
