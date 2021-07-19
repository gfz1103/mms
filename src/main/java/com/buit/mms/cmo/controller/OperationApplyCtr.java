package com.buit.mms.cmo.controller;

import com.buit.commons.BaseSpringController;
import com.buit.mms.cmo.dto.DoctorDicDto;
import com.buit.mms.cmo.request.OperationSelectorReq;
import com.buit.mms.cmo.request.QueryOperationApplyReq;
import com.buit.mms.cmo.request.SaveOperationApplyReq;
import com.buit.mms.cmo.response.OperationApplyResp;
import com.buit.mms.cmo.response.OperationSelectorResp;
import com.buit.mms.cmo.service.OperationApplySer;
import com.buit.system.response.DoctorDicResp;
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
import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/6 10:08
 */
@Api(tags="手术申请")
@Controller
@RequestMapping(value = "/operation/apply",method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationApplyCtr extends BaseSpringController {

    @Autowired
    OperationApplySer operationApplySer;

    @ApiOperation("保存")
    @RequestMapping("/save")
    @ResponseBody
    public ReturnEntity<Integer> save(@Valid SaveOperationApplyReq req){
        return ReturnEntityUtil.success(operationApplySer.save(req,getUser()));
    }

    @ApiOperation("查询手术申请单明细")
    @RequestMapping("/get")
    @ResponseBody
    public ReturnEntity<OperationApplyResp> get(@RequestParam("sqdh")Integer sqdh){
        return ReturnEntityUtil.success(operationApplySer.get(sqdh));
    }

    @ApiOperation("查询手术申请单列表")
    @RequestMapping("/list")
    @ResponseBody
    public ReturnEntity<PageInfo<OperationApplyResp>> get(@Valid QueryOperationApplyReq req){
        return ReturnEntityUtil.success(operationApplySer.list(req,getUser().getUserId()));
    }

    @ApiOperation("作废手术申请单")
    @RequestMapping("/enable")
    @ResponseBody
    public ReturnEntity enable(@RequestParam("sqdh")Integer sqdh){
        operationApplySer.enable(sqdh);
        return ReturnEntityUtil.success();
    }

    @ApiOperation("保存前校验是否为重大手术")
    @RequestMapping("/check/major")
    @ResponseBody
    public ReturnEntity<Integer> checkMajor(@RequestParam("ssnm")Integer ssnm){

        return ReturnEntityUtil.success(operationApplySer.checkMajor(ssnm));
    }


    @ApiOperation("查询拥有手术等级权限的医生")
    @RequestMapping("/query/operator")
    @ResponseBody
    public ReturnEntity<List<DoctorDicResp>> queryOperator(@RequestParam(value="ssdj",required = false)Integer ssdj,@RequestParam(value="pydm",required = false)String pydm){

        return ReturnEntityUtil.success(operationApplySer.queryOperator(ssdj,pydm,getUser().getHospitalId()));
    }

    @ApiOperation("查询拥有手术审核权限的医生")
    @RequestMapping("/query/check/operator")
    @ResponseBody
    public ReturnEntity<List<DoctorDicResp>> queryCheckOperator(@RequestParam(value="ssdj",required = false)Integer ssdj,@RequestParam(value="pydm",required = false)String pydm){

        return ReturnEntityUtil.success(operationApplySer.queryCheckOperator(ssdj,pydm,getUser().getHospitalId()));
    }


    @ApiOperation("手术输入法")
    @RequestMapping("/selector")
    @ResponseBody
    public ReturnEntity<PageInfo<OperationSelectorResp>> selector(OperationSelectorReq req){

        return ReturnEntityUtil.success(operationApplySer.selector(req));
    }

    @ApiOperation("查询医生职称")
    @RequestMapping("/query/yszc")
    @ResponseBody
    public ReturnEntity<String> queryYszc(@RequestParam("ysgh")Integer ysgh){

        return ReturnEntityUtil.success(operationApplySer.queryYszc(ysgh));
    }


}
