
package com.buit.mms.antimi.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.util.StrUtil;

import com.github.pagehelper.Page;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.constans.TableName;
import com.buit.mms.antimi.request.AmqcYskjywsylyReq;
import com.buit.mms.antimi.response.AmqcYskjywsylyResp;
import com.buit.mms.antimi.service.AmqcYskjywsylySer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 医生抗菌药物使用理由表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="医生抗菌药物使用理由表")
@Controller
@RequestMapping("/amqcyskjywsyly")
public class AmqcYskjywsylyCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(AmqcYskjywsylyCtr.class);
    
    @Autowired
    private AmqcYskjywsylySer amqcYskjywsylySer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value="医生使用理由分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<AmqcYskjywsylyResp>> queryPage(@ApiParam(name = "lx", value = "类型", required = true)
    @RequestParam Integer lx, @ApiParam(name = "syly", value = "使用理由", required = false)
    @RequestParam(value = "syly", required = false) String syly, PageQuery page){
        PageInfo<AmqcYskjywsylyResp> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> amqcYskjywsylySer.getEntityMapper().queryInfo(
                    		this.getUser().getHospitalId(), lx, syly)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    @ApiOperation(value="新增修改保存医生使用理由" ,httpMethod="POST")
    public ReturnEntity saveOrUpdate(AmqcYskjywsylyReq amqcYskjywsylyReq){
    	amqcYskjywsylyReq.setYljgd(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(amqcYskjywsylyReq.getJlxh())) {
    		amqcYskjywsylyReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.AMQC_YSKJYWSYLY));
    		amqcYskjywsylySer.insert(amqcYskjywsylyReq);
    	}else {
    		amqcYskjywsylySer.update(amqcYskjywsylyReq);
    	}
        return ReturnEntityUtil.success();    
    }

    /** 删除 */
	@RequestMapping("/deleteById")
	@ResponseBody
	@ApiOperation(value="医生使用理由删除" ,httpMethod="POST")
	public ReturnEntity deleteById(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh) {
		amqcYskjywsylySer.removeById(jlxh);
		return ReturnEntityUtil.success();            
	}
    
  
//    /** 新增 */
//    @RequestMapping("/add")
//    @ResponseBody
//    @ApiOperation(value="新增" ,httpMethod="POST")
//    public ReturnEntity<AmqcYskjywsylyResp> add(AmqcYskjywsylyReq amqcYskjywsyly) {
//        amqcYskjywsylySer.insert(amqcYskjywsyly);        
//        return ReturnEntityUtil.success(amqcYskjywsyly);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<AmqcYskjywsylyResp> edit(AmqcYskjywsylyReq amqcYskjywsyly)  {
//        amqcYskjywsylySer.update(amqcYskjywsyly);        
//        return ReturnEntityUtil.success(amqcYskjywsyly);            
//    }
//    

    
}

