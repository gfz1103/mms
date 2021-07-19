package com.buit.mms.cmo.controller;

import com.buit.mms.cmo.enums.RelevantTypeEnum;
import com.buit.mms.cmo.request.SaveOperationRelevantReq;
import com.buit.mms.cmo.response.OperationRelevantResp;
import com.buit.mms.cmo.service.OperationRelevantSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
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
 * @Date 2020/11/5 16:41
 */
//@Api(tags="特殊手术/手术类型/隔离标志设置")
//@Controller
//@RequestMapping(value = "/operation/relevant",method = RequestMethod.POST,
//        produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationRelevantCtr {

    @Autowired
    OperationRelevantSer operationRelevantSer;

    @ApiOperation("列表")
    @RequestMapping("/list")
    @ResponseBody
    public ReturnEntity<List<OperationRelevantResp>> list(@RequestParam("relevant")RelevantTypeEnum relevant){

        return ReturnEntityUtil.success(operationRelevantSer.list(relevant));
    }

    @ApiOperation("保存")
    @RequestMapping("/save")
    @ResponseBody
    public ReturnEntity save(@Valid SaveOperationRelevantReq req){
        operationRelevantSer.save(req);
        return ReturnEntityUtil.success();
    }

    @ApiOperation("删除")
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnEntity delete(@RequestParam("id") Integer id){
        operationRelevantSer.delete(id);
        return ReturnEntityUtil.success();
    }

    @ApiOperation("停用/启用")
    @RequestMapping("/enable")
    @ResponseBody
    public ReturnEntity enable(@RequestParam("id") Integer id){
        operationRelevantSer.enable(id);
        return ReturnEntityUtil.success();
    }
}
