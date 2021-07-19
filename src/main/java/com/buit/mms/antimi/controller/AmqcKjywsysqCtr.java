
package com.buit.mms.antimi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.buit.mms.antimi.request.AmqcKjywsysqMzSaveReq;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.file.IReportExportFileSer;
import com.buit.mms.antimi.request.AmqcKjywsysqReq;
import com.buit.mms.antimi.request.AmqcKjywsysqSaveReq;
import com.buit.mms.antimi.request.AmqcKjywsysqStatisticsReq;
import com.buit.mms.antimi.response.AmqcKjywsysqResp;
import com.buit.mms.antimi.response.AmqcKjywsysqStatisticsResp;
import com.buit.mms.antimi.response.AmqcKjywsysqUseIntensityResp;
import com.buit.mms.antimi.response.AmqcKjywsysqUseRankingResp;
import com.buit.mms.antimi.response.AmqcKjywsysqUtilizationRateResp;
import com.buit.mms.antimi.response.AmqcKjywsysqypysResp;
import com.buit.mms.antimi.service.AmqcKjywsysqSer;
import com.buit.system.service.ExportFileSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 抗菌药物使用申请单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="抗菌药物使用申请单")
@Controller
@RequestMapping("/amqckjywsysq")
public class AmqcKjywsysqCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsysqCtr.class);
    
    @Autowired
    private AmqcKjywsysqSer amqcKjywsysqSer;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @RequestMapping("/queryAntibioticsInfo")
    @ResponseBody
    @ApiOperation(value="抗菌药物申请单查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<AmqcKjywsysqResp>> queryAntibioticsInfo(AmqcKjywsysqReq amqcKjywsysqReq,PageQuery page){
        PageInfo<AmqcKjywsysqResp> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> amqcKjywsysqSer.getEntityMapper().queryAntibioticsInfo(amqcKjywsysqReq)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
   
    @RequestMapping("/updateAntibioticsExamine")
    @ResponseBody
    @ApiOperation(value="审核抗菌药物申请单" ,httpMethod="POST")
    public ReturnEntity updateAntibioticsExamine(@ApiParam(name = "sqdh", value = "申请id") 
    @RequestParam(value = "sqdh") Integer sqdh, @ApiParam(name = "shyj", value = "审核意见") 
    @RequestParam(value = "shyj") String shyj, @ApiParam(name = "shjg", value = "审核结果") 
    @RequestParam(value = "shjg") Integer shjg){
    	amqcKjywsysqSer.updateAntibioticsExamine(sqdh, shyj, shjg, this.getUser());
        return ReturnEntityUtil.success();    
    }
 
    @RequestMapping("/queryHzAntibioticsInfo")
    @ResponseBody
    @ApiOperation(value="会诊抗菌药物申请单查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<AmqcKjywsysqResp>> queryHzAntibioticsInfo(AmqcKjywsysqReq amqcKjywsysqReq,PageQuery page){
        PageInfo<AmqcKjywsysqResp> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> amqcKjywsysqSer.getEntityMapper().queryHzAntibioticsInfo(amqcKjywsysqReq)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/saveAntibiotics")
    @ResponseBody
    @ApiOperation(value="抗菌药物申请单保存" ,httpMethod="POST")
    public ReturnEntity saveAntibiotics(@RequestBody List<AmqcKjywsysqSaveReq> list){
        amqcKjywsysqSer.saveAntibiotics(list, this.getUser());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/saveMzAntibiotics")
    @ResponseBody
    @ApiOperation(value="门诊抗菌药物申请单保存（申请单和汇诊）" ,httpMethod="POST")
    public ReturnEntity saveMzAntibiotics(@RequestBody AmqcKjywsysqMzSaveReq req){
        amqcKjywsysqSer.saveMzAntibiotics(req, this.getUser());
        return ReturnEntityUtil.success();
    }
  
    /** 删除 */
    @RequestMapping("/deleteBySqdh")
    @ResponseBody
    @ApiOperation(value="抗菌药物申请单删除" ,httpMethod="POST")
    public ReturnEntity deleteBySqdh(@ApiParam(name = "sqdh", value = "申请id") 
    @RequestParam(value = "sqdh") Integer sqdh) {
        amqcKjywsysqSer.removeAntibiotics(sqdh, this.getUser());        
        return ReturnEntityUtil.success();            
    }
    
    @RequestMapping("/getSqdInfoBySqdh")
    @ResponseBody
    @ApiOperation(value="查询抗菌药物申请单信息" ,httpMethod="POST")
    public ReturnEntity<AmqcKjywsysqypysResp> getSqdInfoBySqdh(@ApiParam(name = "sqdh", value = "申请id") 
    @RequestParam(value = "sqdh") Integer sqdh) {     
        return ReturnEntityUtil.success(amqcKjywsysqSer.getSqdInfoBySqdh(sqdh, this.getUser()));            
    }
    
    @RequestMapping("/antibioticsStatistics")
    @ResponseBody
    @ApiOperation(value="越级或特殊级使用抗菌药物统计" ,httpMethod="POST")
    public ReturnEntity<PageInfo<AmqcKjywsysqStatisticsResp>> antibioticsStatistics(AmqcKjywsysqStatisticsReq req,
    		PageQuery page) { 
    	req.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(amqcKjywsysqSer.antibioticsStatistics(req, page));            
    }
    
    @RequestMapping("/antibioticsUseIntensity")
    @ResponseBody
    @ApiOperation(value="抗菌药物使用强度统计" ,httpMethod="POST")
    public ReturnEntity<PageInfo<AmqcKjywsysqUseIntensityResp>> antibioticsUseIntensity(AmqcKjywsysqStatisticsReq req,
    		PageQuery page) { 
    	req.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(amqcKjywsysqSer.antibioticsUseIntensity(req, page));            
    }
    
    @RequestMapping("/antibioticsUtilizationRate")
    @ResponseBody
    @ApiOperation(value="抗菌药物使用率统计" ,httpMethod="POST")
    public ReturnEntity<PageInfo<AmqcKjywsysqUtilizationRateResp>> antibioticsUtilizationRate(AmqcKjywsysqStatisticsReq req,
    		PageQuery page) { 
    	req.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(amqcKjywsysqSer.antibioticsUtilizationRate(req, page));            
    }
    
    @RequestMapping("/antibioticsUseRanking")
    @ResponseBody
    @ApiOperation(value="抗菌药物使用排名" ,httpMethod="POST")
    public ReturnEntity<PageInfo<AmqcKjywsysqUseRankingResp>> antibioticsUseRanking(AmqcKjywsysqStatisticsReq req,
    		PageQuery page) { 
    	req.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(amqcKjywsysqSer.antibioticsUseRanking(req, page));            
    }
    
    @RequestMapping("/updateSpecialExamine")
    @ResponseBody
    @ApiOperation(value="审核特殊抗菌药物申请单" ,httpMethod="POST")
    public ReturnEntity updateSpecialExamine(@ApiParam(name = "sqdh", value = "申请id") 
    @RequestParam(value = "sqdh") Integer sqdh, @ApiParam(name = "hzyj", value = "会诊意见") 
    @RequestParam(value = "hzyj") String hzyj, @ApiParam(name = "hzjg", value = "会诊结果") 
    @RequestParam(value = "hzjg") Integer hzjg){
    	amqcKjywsysqSer.updateSpecialExamine(sqdh, hzyj, hzjg, this.getUser());
        return ReturnEntityUtil.success();    
    }
    
    @RequestMapping("/queryVerifyAntibioticsInfo")
    @ResponseBody
    @ApiOperation(value="住院抗菌药物申请单审核查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<AmqcKjywsysqResp>> queryVerifyAntibioticsInfo(AmqcKjywsysqReq amqcKjywsysqReq,PageQuery page){
        return ReturnEntityUtil.success(amqcKjywsysqSer.queryVerifyAntibioticsInfo(amqcKjywsysqReq, 
        		page, this.getUser()));
    }
    
    @GetMapping("/specialAntibioticsFile")
	@ApiOperation(value="特殊级抗菌药物会诊申请单打印")
	public void specialAntibioticsFile(@RequestParam("sqdh") Integer sqdh, HttpServletResponse response){
    	String jasperName = "jrxml/HosSpecialAntibioticsForm.jasper";
    	Map<String, Object> map = amqcKjywsysqSer.getEntityMapper().querySpecialAntibioticsPrint(sqdh, 
				this.getUser().getHospitalId());
    	map.put("TITLE", this.getUser().getHospitalName() + "特殊使用级抗菌药物使用申请和会诊单");
    	iReportExportFileSer.reportHtmlForStream(map, jasperName, response);
	}
}

