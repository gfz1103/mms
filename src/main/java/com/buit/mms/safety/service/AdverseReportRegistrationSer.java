package com.buit.mms.safety.service;

import cn.hutool.json.JSONUtil;
import com.buit.commons.BaseException;
import com.buit.commons.SysUser;
import com.buit.his.pha.commons.dto.ColumnValueDto;
import com.buit.his.pha.commons.dto.QueryConditionDto;
import com.buit.mms.safety.dao.AdvBgdjDao;
import com.buit.mms.safety.dao.AdvPropDao;
import com.buit.mms.safety.model.AdvBgdj;
import com.buit.mms.safety.request.QueryReportListReq;
import com.buit.mms.safety.request.SaveReportInfoReq;
import com.buit.mms.safety.response.EventApprovalDepartmentResp;
import com.buit.mms.safety.response.ReportInfoResp;
import com.buit.utill.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 13:22
 */
@Service
public class AdverseReportRegistrationSer {

    static final Logger log = LoggerFactory.getLogger(ApprovalDepartmentConfigSer.class);

    @Autowired
    AdvBgdjDao advBgdjDao;
    @Autowired
    AdvPropDao advPropDao;

    public PageInfo<ReportInfoResp> queryRegistrationList(QueryReportListReq req, SysUser user) {
        log.info("查询报告登记列表[{},{}]", JSONUtil.toJsonStr(req),JSONUtil.toJsonStr(user));
        QueryConditionDto cond = QueryConditionDto.and(
                ColumnValueDto.eq("bgks",req.getKsid()),
                ColumnValueDto.eq("jgid",user.getHospitalId()),
                ColumnValueDto.eq("bgrgh",user.getUserId())
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
            cond.add(ColumnValueDto.eq("sbbz",req.getStatus()));
        }
        PageInfo<ReportInfoResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->advBgdjDao.queryListByCond(cond));
        List<EventApprovalDepartmentResp> propList = advPropDao.queryApprovalConfig(user.getHospitalId());
        Map<Integer,String> propMap = propList.stream().collect(Collectors.toMap(EventApprovalDepartmentResp::getId,EventApprovalDepartmentResp::getSjmc));
        resp.getList().forEach(f->f.setSjxzmc(propMap.get(f.getSjxzid())));
        return resp;
    }

    public void save(SaveReportInfoReq req) {
        log.info("保存事件报告[{}]",JSONUtil.toJsonStr(req));
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        req.setBgsj(now);
        req.setZrtbsj(now);
        if(req.getZdlc()==2&& StringUtils.isBlank(req.getWjbh())){
            throw BaseException.create("ERROR_ADV_0001");
        }
        if(req.getZcjl()==3&&StringUtils.isBlank(req.getQtsm())){
            throw BaseException.create("ERROR_ADV_0002");
        }
        if(req.getId()!=null){
            AdvBgdj old = advBgdjDao.getById(req.getId());
            if(old.getSbbz()==1){
                throw BaseException.create("ERROR_ADV_0004");
            }
            advBgdjDao.update(req);
        }else{
            advBgdjDao.insert(req);
        }
    }

    public void reporting(Integer id) {
        log.info("上报事件报告[{}]",id);
        advBgdjDao.updateStatusById(id,"sbbz",1);
    }

    public void delete(Integer id) {
        log.info("删除事件报告[{}]",id);
        AdvBgdj old = advBgdjDao.getById(id);
        if(old.getSbbz()==1){
            throw BaseException.create("ERROR_ADV_0003");
        }
        advBgdjDao.delete(id);
    }



}
