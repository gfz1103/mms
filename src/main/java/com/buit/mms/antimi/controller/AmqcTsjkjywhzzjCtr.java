
package com.buit.mms.antimi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.mms.antimi.request.AmqcTsjkjywhzzjReq;
import com.buit.mms.antimi.response.AmqcTsjkjywhzzjResp;
import com.buit.mms.antimi.service.AmqcTsjkjywhzzjSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.constans.TableName;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 特殊级抗菌药物会诊专家表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="特殊级抗菌药物会诊专家表")
@Controller
@RequestMapping("/amqctsjkjywhzzj")
public class AmqcTsjkjywhzzjCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(AmqcTsjkjywhzzjCtr.class);
    
    @Autowired
    private AmqcTsjkjywhzzjSer amqcTsjkjywhzzjSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value="会诊专家分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<AmqcTsjkjywhzzjResp>> queryPage(PageQuery page){
        PageInfo<AmqcTsjkjywhzzjResp> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> amqcTsjkjywhzzjSer.getEntityMapper().queryInfo(this.getUser().getHospitalId())
            );
        return ReturnEntityUtil.success(pageInfo);
    }
  
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    @ApiOperation(value="新增修改保存会诊专家" ,httpMethod="POST")
    public ReturnEntity saveOrUpdate(@RequestBody List<AmqcTsjkjywhzzjReq> list){
    	for(AmqcTsjkjywhzzjReq amqcTsjkjywhzzjReq : list) {
    		amqcTsjkjywhzzjReq.setYljgd(this.getUser().getHospitalId());
    		if(StrUtil.isBlankIfStr(amqcTsjkjywhzzjReq.getJlxh())) {
    			amqcTsjkjywhzzjReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.AMQC_TSJKJYWHZZJ));
    			amqcTsjkjywhzzjSer.insert(amqcTsjkjywhzzjReq);
    		}else {
    			amqcTsjkjywhzzjSer.update(amqcTsjkjywhzzjReq);
    		}
    	}
        return ReturnEntityUtil.success();    
    }
   
    /** 删除 */
    @RequestMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value="会诊专家删除" ,httpMethod="POST")
    public ReturnEntity deleteById(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh) { 
    	amqcTsjkjywhzzjSer.removeById(jlxh);
    	return ReturnEntityUtil.success();            
    }

//    /** 新增 */
//    @RequestMapping("/add")
//    @ResponseBody
//    @ApiOperation(value="新增" ,httpMethod="POST")
//    public ReturnEntity<AmqcTsjkjywhzzjResp> add(AmqcTsjkjywhzzjReq amqcTsjkjywhzzj) {
//        amqcTsjkjywhzzjSer.insert(amqcTsjkjywhzzj);        
//        return ReturnEntityUtil.success(amqcTsjkjywhzzj);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<AmqcTsjkjywhzzjResp> edit(AmqcTsjkjywhzzjReq amqcTsjkjywhzzj)  {
//        amqcTsjkjywhzzjSer.update(amqcTsjkjywhzzj);        
//        return ReturnEntityUtil.success(amqcTsjkjywhzzj);            
//    }
//    

    
}

