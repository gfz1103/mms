
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
import com.buit.mms.antimi.service.AmqcKjywsysqypSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 抗菌药物使用申请药品表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="抗菌药物使用申请药品表")
@Controller
@RequestMapping("/amqckjywsysqyp")
public class AmqcKjywsysqypCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsysqypCtr.class);
    
    @Autowired
    private AmqcKjywsysqypSer amqcKjywsysqypSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<AmqcKjywsysqypResp>> queryPage(AmqcKjywsysqypReq amqckjywsysqyp,PageQuery page){
//        PageInfo<AmqcKjywsysqyp> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> amqcKjywsysqypSer.findByEntity(amqckjywsysqyp)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<AmqcKjywsysqypResp>> getByEntity( AmqcKjywsysqypReq amqckjywsysqyp){//@RequestBody 
//        return ReturnEntityUtil.success(amqcKjywsysqypSer.findByEntity(amqckjywsysqyp));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsysqypResp> getOneByEntity(AmqcKjywsysqypReq amqckjywsysqyp){
//        List<AmqcKjywsysqyp> list=amqcKjywsysqypSer.findByEntity(amqckjywsysqyp);
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
//    public ReturnEntity<AmqcKjywsysqypResp> add(AmqcKjywsysqypReq amqcKjywsysqyp) {
//        amqcKjywsysqypSer.insert(amqcKjywsysqyp);        
//        return ReturnEntityUtil.success(amqcKjywsysqyp);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsysqypResp> edit(AmqcKjywsysqypReq amqcKjywsysqyp)  {
//        amqcKjywsysqypSer.update(amqcKjywsysqyp);        
//        return ReturnEntityUtil.success(amqcKjywsysqyp);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsysqypResp> delete(AmqcKjywsysqypReq amqcKjywsysqyp) {
//        amqcKjywsysqypSer.removeByEntity(amqcKjywsysqyp);        
//        return ReturnEntityUtil.success(amqcKjywsysqyp);            
//    }
    
}

