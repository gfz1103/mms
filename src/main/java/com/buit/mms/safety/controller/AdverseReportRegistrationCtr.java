package com.buit.mms.safety.controller;

import com.buit.commons.BaseSpringController;
import com.buit.mms.safety.request.QueryReportListReq;
import com.buit.mms.safety.request.SaveReportInfoReq;
import com.buit.mms.safety.response.ReportInfoResp;
import com.buit.mms.safety.service.AdverseReportRegistrationSer;
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
 * @Date 2020/10/28 13:15
 */
@Api(tags="不良事件报告登记")
@Controller
@RequestMapping(value = "/report/registration",method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AdverseReportRegistrationCtr extends BaseSpringController {

    @Autowired
    AdverseReportRegistrationSer adverseReportRegistrationSer;

    @ApiOperation("查询报告登记列表")
    @RequestMapping("/query/list")
    @ResponseBody
    public ReturnEntity<PageInfo<ReportInfoResp>> queryRegistrationList(@Valid QueryReportListReq req){

        return ReturnEntityUtil.success(adverseReportRegistrationSer.queryRegistrationList(req,getUser()));
    }

    @ApiOperation("保存报告登记表")
    @RequestMapping("/save")
    @ResponseBody
    public ReturnEntity save(@Valid SaveReportInfoReq req){
        adverseReportRegistrationSer.save(req);
        return ReturnEntityUtil.success();
    }

    @ApiOperation("上报")
    @RequestMapping("/reporting")
    @ResponseBody
    public ReturnEntity reporting(@RequestParam("id")Integer id){
        adverseReportRegistrationSer.reporting(id);
        return ReturnEntityUtil.success();
    }

    @ApiOperation("删除")
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnEntity delete(@RequestParam("id")Integer id){
        adverseReportRegistrationSer.delete(id);
        return ReturnEntityUtil.success();
    }




}
