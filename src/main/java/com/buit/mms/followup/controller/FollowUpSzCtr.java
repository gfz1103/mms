package com.buit.mms.followup.controller;

import com.buit.commons.BaseSpringController;
import com.buit.mms.followup.model.FollowUpSz;
import com.buit.mms.followup.service.FollowUpSzSer;
import com.buit.utill.ParamUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * 随访配置接口<br>
 * @author tianjunhao
 */
@Api(tags="随访配置接口")
@Controller
@RequestMapping("/sz")
public class FollowUpSzCtr extends BaseSpringController {

    @Autowired
    FollowUpSzSer followUpSzSer;

    @RequestMapping("/addOrUpdate")
    @ResponseBody
    @ApiOperation(value="新增随访配置" ,httpMethod="POST")
    public ReturnEntity addOrUpdateFollowUpSz(@Valid FollowUpSz followUpSz){
        followUpSz.setJgid(getUser().getHospitalId());
        if(Objects.isNull(followUpSz.getId())) {
            followUpSzSer.add(followUpSz);
        }else {
            followUpSzSer.update(followUpSz);
        }
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    @ApiOperation(value="更新配置状态" ,httpMethod="POST")
    public ReturnEntity updateStatus(@ApiParam(name = "id", value = "配置表主键", required = true) @RequestParam Integer id,
                                     @ApiParam(name = "zt", value = "配置状态", required = true) @RequestParam Boolean zt){
        followUpSzSer.update(ParamUtil.instance().put("id", id).put("zt", zt));
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/remove")
    @ResponseBody
    @ApiOperation(value="删除随访配置" ,httpMethod="POST")
    public ReturnEntity removeFollowUpSz(@ApiParam(name = "id", value = "配置表主键", required = true) @RequestParam Integer id){
        followUpSzSer.remove(id);
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/queryByNrlx")
    @ResponseBody
    @ApiOperation(value="根据配置类型查询数据" ,httpMethod="POST")
    public ReturnEntity<List<FollowUpSz>> queryAll(@ApiParam(name = "nrlx", value = "内容类型", required = true) @RequestParam Integer nrlx,
                                                   @ApiParam(name = "zt", value = "状态:不传-查询全部, 0-停用，1-启用", required = false) @RequestParam(required = false) Integer zt,
                                                   @ApiParam(name = "pydm", value = "拼音码", required = false) @RequestParam(required = false) String pydm){
        Integer jgid = getUser().getHospitalId();
        List<FollowUpSz> followUpSzList = followUpSzSer.findByEntity(ParamUtil.instance().put("nrlx", nrlx).put("zt", zt).put("jgid", jgid).put("pydm", pydm));
        return ReturnEntityUtil.success(followUpSzList);
    }
}
