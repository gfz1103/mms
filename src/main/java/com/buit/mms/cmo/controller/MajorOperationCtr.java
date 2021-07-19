package com.buit.mms.cmo.controller;

import com.buit.mms.cmo.request.QueryMajorListReq;
import com.buit.mms.cmo.request.SaveOperationApplyReq;
import com.buit.mms.cmo.response.CmoZdssResp;
import com.buit.mms.cmo.service.MajorOperationSer;
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
 * @Date 2020/11/10 15:31
 */
@Api(tags="重大手术目录设置")
@Controller
@RequestMapping(value = "/major/operation",method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MajorOperationCtr {

    @Autowired
    MajorOperationSer majorOperationSer;

    @ApiOperation("新增重大手术")
    @RequestMapping("/add")
    @ResponseBody
    public ReturnEntity add(@RequestParam("ssbm") List<Integer> ssbmList){
        majorOperationSer.add(ssbmList);
        return ReturnEntityUtil.success();
    }

    @ApiOperation("移除重大手术")
    @RequestMapping("/remove")
    @ResponseBody
    public ReturnEntity remove(@RequestParam("ssbm") List<Integer> ssbmList){
        majorOperationSer.remove(ssbmList);
        return ReturnEntityUtil.success();
    }


    @ApiOperation("查询重大手术列表")
    @RequestMapping("/list")
    @ResponseBody
    public ReturnEntity<PageInfo<CmoZdssResp>> list(QueryMajorListReq req){

        return ReturnEntityUtil.success(majorOperationSer.list(req));
    }

    @ApiOperation("查询待选择手术列表")
    @RequestMapping("/list/unchecked")
    @ResponseBody
    public ReturnEntity<PageInfo<CmoZdssResp>> uncheckedList(QueryMajorListReq req){

        return ReturnEntityUtil.success(majorOperationSer.uncheckedList(req));
    }
}
