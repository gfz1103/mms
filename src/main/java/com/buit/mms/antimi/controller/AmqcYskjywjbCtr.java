
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
import com.buit.mms.antimi.service.AmqcYskjywjbSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 医生抗菌药物级别表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="医生抗菌药物级别表")
@Controller
@RequestMapping("/amqcyskjywjb")
public class AmqcYskjywjbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(AmqcYskjywjbCtr.class);
    
    @Autowired
    private AmqcYskjywjbSer amqcYskjywjbSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<AmqcYskjywjbResp>> queryPage(AmqcYskjywjbReq amqcyskjywjb,PageQuery page){
//        PageInfo<AmqcYskjywjb> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> amqcYskjywjbSer.findByEntity(amqcyskjywjb)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<AmqcYskjywjbResp>> getByEntity( AmqcYskjywjbReq amqcyskjywjb){//@RequestBody 
//        return ReturnEntityUtil.success(amqcYskjywjbSer.findByEntity(amqcyskjywjb));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<AmqcYskjywjbResp> getOneByEntity(AmqcYskjywjbReq amqcyskjywjb){
//        List<AmqcYskjywjb> list=amqcYskjywjbSer.findByEntity(amqcyskjywjb);
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
//    public ReturnEntity<AmqcYskjywjbResp> add(AmqcYskjywjbReq amqcYskjywjb) {
//        amqcYskjywjbSer.insert(amqcYskjywjb);        
//        return ReturnEntityUtil.success(amqcYskjywjb);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<AmqcYskjywjbResp> edit(AmqcYskjywjbReq amqcYskjywjb)  {
//        amqcYskjywjbSer.update(amqcYskjywjb);        
//        return ReturnEntityUtil.success(amqcYskjywjb);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<AmqcYskjywjbResp> delete(AmqcYskjywjbReq amqcYskjywjb) {
//        amqcYskjywjbSer.removeByEntity(amqcYskjywjb);        
//        return ReturnEntityUtil.success(amqcYskjywjb);            
//    }
    
}

