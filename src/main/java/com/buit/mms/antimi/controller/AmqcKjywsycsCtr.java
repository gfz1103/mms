
package com.buit.mms.antimi.controller;

import com.buit.commons.BaseSpringController;
import com.buit.mms.antimi.model.AmqcKjywsycs;
import com.buit.mms.antimi.request.AmqcKjywsycsReq;
import com.buit.mms.antimi.service.AmqcKjywsycsSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 抗菌药物使用参数<br>
 * @author GONGFANGZHOU
 */
@Api(tags="抗菌药物使用参数")
@Controller
@RequestMapping("/amqckjywsycs")
public class AmqcKjywsycsCtr extends BaseSpringController{

    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsycsCtr.class);

    @Autowired
    private AmqcKjywsycsSer amqcKjywsycsSer;

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    @ApiOperation(value="新增修改保存抗菌药物使用参数" ,httpMethod="POST")
    public ReturnEntity saveOrUpdate(AmqcKjywsycsReq amqcKjywsycsReq){
    	AmqcKjywsycs amqcKjywsycs = amqcKjywsycsSer.getById(this.getUser().getHospitalId());
    	amqcKjywsycsReq.setYljgd(this.getUser().getHospitalId());
    	if(amqcKjywsycs != null) {
    		amqcKjywsycsSer.update(amqcKjywsycsReq);
    	}else {
    		amqcKjywsycsSer.insert(amqcKjywsycsReq);
    	}
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/getOneByEntity")
    @ResponseBody
    @ApiOperation(value="抗菌药物使用参数" ,httpMethod="POST")
    public ReturnEntity<AmqcKjywsycs> getOneByEntity(){
        return ReturnEntityUtil.success(amqcKjywsycsSer.getById(this.getUser().getHospitalId()));
    }

//    /** 新增 */
//    @RequestMapping("/add")
//    @ResponseBody
//    @ApiOperation(value="新增" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsycsResp> add(AmqcKjywsycsReq amqcKjywsycs) {
//        amqcKjywsycsSer.insert(amqcKjywsycs);
//        return ReturnEntityUtil.success(amqcKjywsycs);
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsycsResp> edit(AmqcKjywsycsReq amqcKjywsycs)  {
//        amqcKjywsycsSer.update(amqcKjywsycs);
//        return ReturnEntityUtil.success(amqcKjywsycs);
//    }
//
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<AmqcKjywsycsResp> delete(AmqcKjywsycsReq amqcKjywsycs) {
//        amqcKjywsycsSer.removeByEntity(amqcKjywsycs);
//        return ReturnEntityUtil.success(amqcKjywsycs);
//    }

}

