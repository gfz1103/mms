
package com.buit.mms.antimi.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.Page;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.buit.commons.BaseSpringController;
import com.buit.mms.antimi.service.AmqcKjywsyhzksysSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 抗菌药物使用会诊科室医生表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="抗菌药物使用会诊科室医生表")
@Controller
@RequestMapping("/amqckjywsyhzksys")
public class AmqcKjywsyhzksysCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsyhzksysCtr.class);
    
    @Autowired
    private AmqcKjywsyhzksysSer amqcKjywsyhzksysSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<AmqcKjywsyhzksysResp>> queryPage(AmqcKjywsyhzksysReq amqckjywsyhzksys,PageQuery page){
//        PageInfo<AmqcKjywsyhzksys> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> amqcKjywsyhzksysSer.findByEntity(amqckjywsyhzksys)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<AmqcKjywsyhzksysResp>> getByEntity( AmqcKjywsyhzksysReq amqckjywsyhzksys){//@RequestBody 
//        return ReturnEntityUtil.success(amqcKjywsyhzksysSer.findByEntity(amqckjywsyhzksys));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsyhzksysResp> getOneByEntity(AmqcKjywsyhzksysReq amqckjywsyhzksys){
//        List<AmqcKjywsyhzksys> list=amqcKjywsyhzksysSer.findByEntity(amqckjywsyhzksys);
//        if(list.size()>0){
//           return ReturnEntityUtil.success(list.get(0));    
//        }
//        return ReturnEntityUtil.success();
//    }
//    
//    /** 新增 */
//    @RequestMapping("/add")
//    @ResponseBody
//    @ApiOperation(value="新增" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsyhzksysResp> add(AmqcKjywsyhzksysReq amqcKjywsyhzksys) {
//        amqcKjywsyhzksysSer.insert(amqcKjywsyhzksys);        
//        return ReturnEntityUtil.success(amqcKjywsyhzksys);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsyhzksysResp> edit(AmqcKjywsyhzksysReq amqcKjywsyhzksys)  {
//        amqcKjywsyhzksysSer.update(amqcKjywsyhzksys);        
//        return ReturnEntityUtil.success(amqcKjywsyhzksys);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsyhzksysResp> delete(AmqcKjywsyhzksysReq amqcKjywsyhzksys) {
//        amqcKjywsyhzksysSer.removeByEntity(amqcKjywsyhzksys);        
//        return ReturnEntityUtil.success(amqcKjywsyhzksys);            
//    }
    
}

