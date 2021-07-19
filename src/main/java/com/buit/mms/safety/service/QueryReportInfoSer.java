package com.buit.mms.safety.service;

import cn.hutool.json.JSONUtil;
import com.buit.commons.SysUser;
import com.buit.his.pha.commons.dto.ColumnValueDto;
import com.buit.his.pha.commons.dto.QueryConditionDto;
import com.buit.mms.safety.dao.AdvBgdjDao;
import com.buit.mms.safety.dao.AdvPropDao;
import com.buit.mms.safety.model.AdvBgdj;
import com.buit.mms.safety.request.QueryReportInfoReq;
import com.buit.mms.safety.response.EventApprovalDepartmentResp;
import com.buit.mms.safety.response.ReportInfoResp;
import com.buit.utill.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/29 11:08
 */
@Service
public class QueryReportInfoSer {

    static final Logger log = LoggerFactory.getLogger(QueryReportInfoSer.class);

    @Autowired
    AdvBgdjDao advBgdjDao;
    @Autowired
    AdvPropDao advPropDao;

    public ReportInfoResp get(Integer id) {
        log.info("查询事件报告[{}]",id);
        AdvBgdj advBgdj = advBgdjDao.getById(id);
        ReportInfoResp resp = BeanUtil.toBean(advBgdj,ReportInfoResp.class);

        return resp;
    }

    public PageInfo<ReportInfoResp> list(QueryReportInfoReq req, SysUser user) {
        log.info("已审核报告表查询[{}]", JSONUtil.toJsonStr(req));
        QueryConditionDto cond = QueryConditionDto.and(
                ColumnValueDto.eq("shbz",1),
                ColumnValueDto.eq("shjg",1)
        );
        if(req.getKsid()!=null){
            cond.add(ColumnValueDto.eq("bgks",req.getKsid()));
        }
        if(req.getSjxz()!=null){
            cond.add(ColumnValueDto.eq("sjxzid",req.getSjxz()));
        }
        if(req.getBgrqStart()!=null){
            cond.add(ColumnValueDto.gtEq("date_format(bgsj,'%Y-%m-%d')",req.getBgrqStart().toString()));
        }
        if(req.getBgrqEnd()!=null){
            cond.add(ColumnValueDto.ltEq("date_format(bgsj,'%Y-%m-%d')",req.getBgrqEnd().toString()));
        }

        if(req.getFsrqStart()!=null){
            cond.add(ColumnValueDto.gtEq("date_format(fssj,'%Y-%m-%d')",req.getFsrqStart().toString()));
        }
        if(req.getFsrqEnd()!=null){
            cond.add(ColumnValueDto.ltEq("date_format(fssj,'%Y-%m-%d')",req.getFsrqEnd().toString()));
        }
        if(req.getSjlb()!=null&&req.getSjlb().size()>0){
            cond.add(ColumnValueDto.in("sjlb",req.getSjlb()));
        }
        PageInfo<ReportInfoResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->advBgdjDao.queryListByCond(cond));
        List<EventApprovalDepartmentResp> propList = advPropDao.queryApprovalConfig(user.getHospitalId());
        Map<Integer,String> propMap = propList.stream().collect(Collectors.toMap(EventApprovalDepartmentResp::getId,EventApprovalDepartmentResp::getSjmc));
        resp.getList().forEach(f->f.setSjxzmc(propMap.get(f.getSjxzid())));
        return resp;
    }
}
