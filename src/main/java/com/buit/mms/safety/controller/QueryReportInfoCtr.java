package com.buit.mms.safety.controller;

import com.buit.commons.BaseSpringController;
import com.buit.mms.safety.request.QueryReportInfoReq;
import com.buit.mms.safety.response.ReportInfoResp;
import com.buit.mms.safety.service.QueryReportInfoSer;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/29 11:07
 */
@Api(tags="报告表查询")
@Controller
@RequestMapping(value = "/report/query",method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class QueryReportInfoCtr extends BaseSpringController {

    @Autowired
    QueryReportInfoSer queryReportInfoSer;

    @ApiOperation("查看报告")
    @RequestMapping("/get")
    @ResponseBody
    public ReturnEntity<ReportInfoResp> get(@RequestParam("id")Integer id){

        return ReturnEntityUtil.success(queryReportInfoSer.get(id));
    }


    @ApiOperation("查询报告列表")
    @RequestMapping("/list")
    @ResponseBody
    public ReturnEntity<PageInfo<ReportInfoResp>> list(@Valid QueryReportInfoReq req){

        return ReturnEntityUtil.success(queryReportInfoSer.list(req,getUser()));
    }


}
