
package com.buit.mms.crivalu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.buit.commons.BaseSpringController;
import com.buit.mms.crivalu.model.CisWjzjl;
import com.buit.mms.crivalu.request.CisWjzjlQualityReportReq;
import com.buit.mms.crivalu.response.CisWjzjlQualityReportResp;
import com.buit.mms.crivalu.response.CisWjzjlResp;
import com.buit.mms.crivalu.service.CisWjzjlSer;
import com.buit.system.service.SysMessSer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 危急值记录表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="危急值记录表")
@Controller
@RequestMapping("/ciswjzjl")
public class CisWjzjlCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisWjzjlCtr.class);
    
    @Autowired
    private CisWjzjlSer cisWjzjlSer;
    
    @RequestMapping("/queryWjzQualityReport")
    @ResponseBody
    @ApiOperation(value="危急值管理质量报表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CisWjzjlQualityReportResp>> queryWjzQualityReport(CisWjzjlQualityReportReq req){
    	req.setJgid(this.getUser().getHospitalId());
    	PageInfo<CisWjzjlQualityReportResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                    () -> cisWjzjlSer.getEntityMapper().queryWjzQualityReport(req)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/queryCriticalValueRecord")
    @ResponseBody
    @ApiOperation(value="危急值登记和临床处置" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CisWjzjlResp>> queryCriticalValueRecord(CisWjzjlQualityReportReq req){
    	req.setJgid(this.getUser().getHospitalId());
    	PageInfo<CisWjzjlResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                    () -> cisWjzjlSer.getEntityMapper().queryCriticalValueRecord(req)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/updateReceiveInfo")
    @ResponseBody
    @ApiOperation(value="危急值更新接收信息" ,httpMethod="POST")
    public ReturnEntity<CisWjzjl> updateReceiveInfo(@ApiParam(name = "wjzdh", value = "危急值单号", required = true)
    @RequestParam Integer wjzdh){
    	cisWjzjlSer.getEntityMapper().updateReceiveInfo(this.getUser().getUserId(), wjzdh);
        return ReturnEntityUtil.success(cisWjzjlSer.getById(wjzdh));
    }
    
    @RequestMapping("/updateNoticeInfo")
    @ResponseBody
    @ApiOperation(value="危急值更新通知信息" ,httpMethod="POST")
    public ReturnEntity updateNoticeInfo(@ApiParam(name = "wjzdh", value = "危急值单号", required = true)
    @RequestParam Integer wjzdh, @ApiParam(name = "tzysdm", value = "通知医生", required = true)
    @RequestParam Integer tzysdm){
    	cisWjzjlSer.updateNoticeInfo(tzysdm, wjzdh, this.getUser().getUserId());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/updateDealInfo")
    @ResponseBody
    @ApiOperation(value="危急值更新处置信息" ,httpMethod="POST")
    public ReturnEntity updateDealInfo(@ApiParam(name = "wjzdh", value = "危急值单号", required = true)
    @RequestParam Integer wjzdh, @ApiParam(name = "czcs", value = "处置措施", required = true)
    @RequestParam String czcs){
    	cisWjzjlSer.updateDealInfo(this.getUser().getUserId(), czcs, wjzdh);
        return ReturnEntityUtil.success();
    }
    
}

