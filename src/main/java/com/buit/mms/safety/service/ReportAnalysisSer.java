package com.buit.mms.safety.service;

import cn.hutool.json.JSONUtil;
import com.buit.commons.BaseException;
import com.buit.commons.SysUser;
import com.buit.his.pha.commons.dto.ColumnValueDto;
import com.buit.his.pha.commons.dto.QueryConditionDto;
import com.buit.mms.safety.dao.AdvBgdjDao;
import com.buit.mms.safety.dao.AdvPropDao;
import com.buit.mms.safety.model.AdvBgdj;
import com.buit.mms.safety.request.ReportAnalysisReq;
import com.buit.mms.safety.request.ReportAnalysisSaveReq;
import com.buit.mms.safety.response.EventApprovalDepartmentResp;
import com.buit.mms.safety.response.ReportInfoResp;
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
 * @Date 2020/10/29 10:22
 */
@Service
public class ReportAnalysisSer {

    static final Logger log = LoggerFactory.getLogger(ReportAnalysisSer.class);

    @Autowired
    AdvBgdjDao advBgdjDao;
    @Autowired
    AdvPropDao advPropDao;

    public PageInfo<ReportInfoResp> queryList(ReportAnalysisReq req, SysUser user) {
        log.info("查询不良事件分析列表[{}]", JSONUtil.toJsonStr(req));
        QueryConditionDto cond = QueryConditionDto.and(
                ColumnValueDto.eq("shbz",1),
                ColumnValueDto.eq("shjg",1)
        );
        if(req.getSjlb()!=null&&req.getSjlb().size()>0){
            cond.add(ColumnValueDto.in("sjlb",req.getSjlb()));
        }
        if(req.getFsrqStart()!=null){
            cond.add(ColumnValueDto.gtEq("date_format(fssj,'%Y-%m-%d')",req.getFsrqStart().toString()));
        }
        if(req.getFsrqEnd()!=null){
            cond.add(ColumnValueDto.ltEq("date_format(fssj,'%Y-%m-%d')",req.getFsrqEnd().toString()));
        }
        if(req.getStatus()!=null){
            cond.add(ColumnValueDto.eq("fxbz",req.getStatus()));
        }
        PageInfo<ReportInfoResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->advBgdjDao.queryListByCond(cond));
        List<EventApprovalDepartmentResp> propList = advPropDao.queryApprovalConfig(user.getHospitalId());
        Map<Integer,String> propMap = propList.stream().collect(Collectors.toMap(EventApprovalDepartmentResp::getId,EventApprovalDepartmentResp::getSjmc));
        resp.getList().forEach(f->f.setSjxzmc(propMap.get(f.getSjxzid())));
        return resp;
    }

    public void save(ReportAnalysisSaveReq req, SysUser user) {
        log.info("报告分析保存[{},{}]",JSONUtil.toJsonStr(req),JSONUtil.toJsonStr(user));
        AdvBgdj advBgdj = advBgdjDao.getById(req.getId());
        if(advBgdj.getFxbz()==1){
            throw BaseException.create("ERROR_ADV_0006");
        }
        advBgdjDao.analysis(req);
    }
}
