
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
import com.buit.mms.antimi.service.AmqcKjywsysqzdSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 抗菌药物使用申请诊断表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="抗菌药物使用申请诊断表")
@Controller
@RequestMapping("/amqckjywsysqzd")
public class AmqcKjywsysqzdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsysqzdCtr.class);
    
    @Autowired
    private AmqcKjywsysqzdSer amqcKjywsysqzdSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<AmqcKjywsysqzdResp>> queryPage(AmqcKjywsysqzdReq amqckjywsysqzd,PageQuery page){
//        PageInfo<AmqcKjywsysqzd> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> amqcKjywsysqzdSer.findByEntity(amqckjywsysqzd)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<AmqcKjywsysqzdResp>> getByEntity( AmqcKjywsysqzdReq amqckjywsysqzd){//@RequestBody 
//        return ReturnEntityUtil.success(amqcKjywsysqzdSer.findByEntity(amqckjywsysqzd));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsysqzdResp> getOneByEntity(AmqcKjywsysqzdReq amqckjywsysqzd){
//        List<AmqcKjywsysqzd> list=amqcKjywsysqzdSer.findByEntity(amqckjywsysqzd);
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
//    public ReturnEntity<AmqcKjywsysqzdResp> add(AmqcKjywsysqzdReq amqcKjywsysqzd) {
//        amqcKjywsysqzdSer.insert(amqcKjywsysqzd);        
//        return ReturnEntityUtil.success(amqcKjywsysqzd);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsysqzdResp> edit(AmqcKjywsysqzdReq amqcKjywsysqzd)  {
//        amqcKjywsysqzdSer.update(amqcKjywsysqzd);        
//        return ReturnEntityUtil.success(amqcKjywsysqzd);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsysqzdResp> delete(AmqcKjywsysqzdReq amqcKjywsysqzd) {
//        amqcKjywsysqzdSer.removeByEntity(amqcKjywsysqzd);        
//        return ReturnEntityUtil.success(amqcKjywsysqzd);            
//    }
    
}

