
package com.buit.mms.antimi.controller;


import com.buit.commons.BaseException;
import com.buit.commons.BaseSpringController;
import com.buit.mms.antimi.model.OpCfKjywsqjl;
import com.buit.mms.antimi.request.OpCfKjywsqjlReq;
import com.buit.mms.antimi.response.OpCfKjywsqjlResp;
import com.buit.mms.antimi.service.OpCfKjywsqjlSer;
import com.buit.system.response.SysUserByKjywResp;
import com.buit.utill.BeanUtil;
import com.buit.utill.ParamUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * 门诊_处方_抗菌药物申请记录<br>
 * @author 朱智
 */
@Api(tags="门诊_处方_抗菌药物申请记录")
@Controller
@RequestMapping("/opcfkjywsqjl")
public class OpCfKjywsqjlCtr extends BaseSpringController{

    static final Logger logger = LoggerFactory.getLogger(OpCfKjywsqjlCtr.class);

    @Autowired
    private OpCfKjywsqjlSer opCfKjywsqjlSer;

//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<OpCfKjywsqjlResp>> queryPage(OpCfKjywsqjlReq opcfkjywsqjl,PageQuery page){
//        PageInfo<OpCfKjywsqjl> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> opCfKjywsqjlSer.findByEntity(opcfkjywsqjl)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }

    @RequestMapping("/findList")
    @ResponseBody
    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
    public ReturnEntity<List<OpCfKjywsqjlResp>> getByEntity(@ApiParam(name = "jzxh", value = "就诊序号", required = true) @RequestParam Integer jzxh){//@RequestBody
        List<OpCfKjywsqjl> list = opCfKjywsqjlSer.findByEntity(ParamUtil.instance().put("jzxh", jzxh));
        return ReturnEntityUtil.success(BeanUtil.toBeans(list, OpCfKjywsqjlResp.class));
    }

//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<OpCfKjywsqjlResp> getOneByEntity(OpCfKjywsqjlReq opcfkjywsqjl){
//        List<OpCfKjywsqjl> list=opCfKjywsqjlSer.findByEntity(opcfkjywsqjl);
//        if(list.size()>0){
//           return ReturnEntityUtil.success(list.get(0));
//        }
//        return ReturnEntityUtil.success();
//    }

    /** 新增 */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value="新增" ,httpMethod="POST")
    public ReturnEntity add(@Valid @RequestBody OpCfKjywsqjlReq opCfKjywsqjl) {
        opCfKjywsqjlSer.add(opCfKjywsqjl, getUser());
        return ReturnEntityUtil.success();
    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<OpCfKjywsqjlResp> edit(OpCfKjywsqjlReq opCfKjywsqjl)  {
//        opCfKjywsqjlSer.update(opCfKjywsqjl);
//        return ReturnEntityUtil.success(opCfKjywsqjl);
//    }

    /** 删除 */
    @RequestMapping("/delete")
    @ResponseBody
    @ApiOperation(value="删除" ,httpMethod="POST")
    public ReturnEntity delete(@ApiParam(name = "id", value = "主键", required = true) @RequestParam Integer id) {
        opCfKjywsqjlSer.delete(id,getUser());
//        OpCfKjywsqjl sqjl = opCfKjywsqjlSer.getById(id);
//        if(sqjl !=null && sqjl.getZt().intValue() > 0 ){
//            throw BaseException.createByMessage("当前状态不可删除！");
//        }
//        opCfKjywsqjlSer.removeById(id);
        return ReturnEntityUtil.success();
    }


    /** 查询抗菌药物权限医生列表 */
    @RequestMapping("/queryKjyw")
    @ResponseBody
    @ApiOperation(value="查询抗菌药物权限医生列表" ,httpMethod="POST")
    public ReturnEntity<List<SysUserByKjywResp>> queryKjyw(@ApiParam(name = "jgid", value = "机构id", required = true) @RequestParam Integer jgid,
                                                           @ApiParam(name = "kjywdj", value = "抗菌药物等级", required = true) @RequestParam String kjywdj) {
        return ReturnEntityUtil.success(opCfKjywsqjlSer.queryDocListByKjyw(jgid, kjywdj));
    }
}

