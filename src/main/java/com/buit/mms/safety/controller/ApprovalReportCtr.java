package com.buit.mms.safety.controller;

import com.buit.commons.BaseException;
import com.buit.commons.BaseSpringController;
import com.buit.mms.safety.request.ApprovalReportReq;
import com.buit.mms.safety.request.QueryReportListReq;
import com.buit.mms.safety.response.ReportInfoResp;
import com.buit.mms.safety.service.ApprovalReportSer;
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
 * @Date 2020/10/28 15:25
 */
@Api(tags="报告表审批")
@Controller
@RequestMapping(value = "/approval/report",method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ApprovalReportCtr extends BaseSpringController {

    @Autowired
    ApprovalReportSer approvalReportSer;

    @ApiOperation("查询报告登记列表")
    @RequestMapping("/query/list")
    @ResponseBody
    public ReturnEntity<PageInfo<ReportInfoResp>> queryList(@Valid QueryReportListReq req){
        return ReturnEntityUtil.success(approvalReportSer.queryList(req,getUser()));
    }

    @ApiOperation("审批")
    @RequestMapping("/")
    @ResponseBody
    public ReturnEntity approval(@Valid ApprovalReportReq req){
        approvalReportSer.approval(req,getUser());
        return ReturnEntityUtil.success();
    }


}
