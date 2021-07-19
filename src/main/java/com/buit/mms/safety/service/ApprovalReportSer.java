package com.buit.mms.safety.service;

import cn.hutool.json.JSONUtil;
import com.buit.commons.BaseException;
import com.buit.commons.SysUser;
import com.buit.his.pha.commons.dto.ColumnValueDto;
import com.buit.his.pha.commons.dto.QueryConditionDto;
import com.buit.mms.safety.dao.AdvBgdjDao;
import com.buit.mms.safety.dao.AdvPropDao;
import com.buit.mms.safety.dao.AdvShpzDao;
import com.buit.mms.safety.model.AdvBgdj;
import com.buit.mms.safety.request.ApprovalReportReq;
import com.buit.mms.safety.request.QueryReportListReq;
import com.buit.mms.safety.response.EventApprovalDepartmentResp;
import com.buit.mms.safety.response.ReportInfoResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 15:30
 */
@Service
public class ApprovalReportSer {

    static final Logger log = LoggerFactory.getLogger(ApprovalDepartmentConfigSer.class);

    @Autowired
    AdvBgdjDao advBgdjDao;
    @Autowired
    AdvPropDao advPropDao;
    @Autowired
    AdvShpzDao advShpzDao;

    public PageInfo<ReportInfoResp> queryList(QueryReportListReq req, SysUser user) {
        log.info("查询待审核报告列表[{},{}]", JSONUtil.toJsonStr(req),JSONUtil.toJsonStr(user));
        List<EventApprovalDepartmentResp> shpzList = advPropDao.queryApprovalConfig(user.getHospitalId());
        Map<Integer,List<Integer>> shpzMap = shpzList.stream().filter(f->f.getShks()==0||f.getShks().equals(req.getKsid()))
                .collect(Collectors.groupingBy(d->d.getShks(),Collectors.mapping(EventApprovalDepartmentResp::getId,Collectors.toList())));
        QueryConditionDto or = QueryConditionDto.or();
        if(shpzMap.size()==0){
            return PageInfo.of(null);
        }else{
            if(shpzMap.get(0)!=null){
                or.add(QueryConditionDto.and(
                        ColumnValueDto.eq("bgks",req.getKsid()),
                        ColumnValueDto.in("sjxzid",shpzMap.get(0))
                ));
            }
            if(shpzMap.get(req.getKsid())!=null){
                or.add(ColumnValueDto.in("sjxzid",shpzMap.get(req.getKsid())));
            }
        }
        QueryConditionDto cond = QueryConditionDto.and(
                ColumnValueDto.eq("jgid",user.getHospitalId()),
                ColumnValueDto.eq("sbbz",1),
                or
        );

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
        if(req.getStatus()!=null){
            cond.add(ColumnValueDto.eq("shbz",req.getStatus()));
        }
        PageInfo<ReportInfoResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->advBgdjDao.queryListByCond(cond));
        List<EventApprovalDepartmentResp> propList = advPropDao.queryApprovalConfig(user.getHospitalId());
        Map<Integer,String> propMap = propList.stream().collect(Collectors.toMap(EventApprovalDepartmentResp::getId,EventApprovalDepartmentResp::getSjmc));
        resp.getList().forEach(f->f.setSjxzmc(propMap.get(f.getSjxzid())));
        return resp;
    }

    public void approval(ApprovalReportReq req, SysUser user) {
        log.info("审批报告[{},{}]",JSONUtil.toJsonStr(req),JSONUtil.toJsonStr(user));
        req.setShgh(user.getUserId());
        req.setShsj(Timestamp.valueOf(LocalDateTime.now()));
        AdvBgdj advBgdj = advBgdjDao.getById(req.getId());
        if(advBgdj.getShbz()==1){
            throw BaseException.create("ERROR_ADV_0005");
        }
        advBgdjDao.approval(req);
    }
}
