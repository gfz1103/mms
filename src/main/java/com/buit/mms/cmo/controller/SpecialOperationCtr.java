package com.buit.mms.cmo.controller;

import com.buit.commons.BaseSpringController;
import com.buit.mms.cmo.request.ExamineOperationApplyReq;
import com.buit.mms.cmo.request.QueryExamineListReq;
import com.buit.mms.cmo.request.SaveOperationApplyReq;
import com.buit.mms.cmo.response.OperationApplyResp;
import com.buit.mms.cmo.service.SpecialOperationSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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
 * @Date 2020/11/9 10:48
 */
@Api(tags="特殊手术单")
@Controller
@RequestMapping(value = "/special/operation",method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class SpecialOperationCtr extends BaseSpringController {

    @Autowired
    SpecialOperationSer specialOperationSer;

    @ApiOperation("审核")
    @RequestMapping("/save")
    @ResponseBody
    public ReturnEntity commit(@Valid ExamineOperationApplyReq req){
        specialOperationSer.commit(req,getUser());
        return ReturnEntityUtil.success();
    }

    @ApiOperation("列表")
    @RequestMapping("/list")
    @ResponseBody
    public ReturnEntity<PageInfo<OperationApplyResp>> list(@Valid QueryExamineListReq req){

        return ReturnEntityUtil.success(specialOperationSer.list(req,getUser()));
    }

}
