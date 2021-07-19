package com.buit.mms.safety.controller;

import com.buit.commons.BaseSpringController;
import com.buit.mms.safety.request.SaveApprovalDepartmentReq;
import com.buit.mms.safety.response.EventApprovalDepartmentResp;
import com.buit.mms.safety.service.ApprovalDepartmentConfigSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 9:41
 */
@Api(tags="审批科室配置")
@Controller
@RequestMapping(value = "/approval/department",method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ApprovalDepartmentConfigCtr extends BaseSpringController {

    @Autowired
    ApprovalDepartmentConfigSer approvalDepartmentConfigSer;

    @ApiOperation("查询事件审批科室配置")
    @RequestMapping("/query")
    @ResponseBody
    public ReturnEntity<List<EventApprovalDepartmentResp>> query(){
        return ReturnEntityUtil.success(approvalDepartmentConfigSer.query(getUser().getHospitalId()));
    }

    @ApiOperation("保存配置")
    @RequestMapping("/save")
    @ResponseBody
    public ReturnEntity save(@RequestBody @Valid SaveApprovalDepartmentReq req){
        approvalDepartmentConfigSer.save(req,getUser().getUserId());
        return ReturnEntityUtil.success();
    }

}
