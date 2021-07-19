package com.buit.mms.cmo.service;

import cn.hutool.json.JSONUtil;
import com.buit.commons.SysUser;
import com.buit.mms.cmo.dao.OptSssqDao;
import com.buit.mms.cmo.request.ExamineOperationApplyReq;
import com.buit.mms.cmo.request.QueryExamineListReq;
import com.buit.mms.cmo.response.OperationApplyResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/9 11:03
 */
@Service
public class SpecialOperationSer {

    static final Logger log = LoggerFactory.getLogger(SpecialOperationSer.class);

    @Autowired
    OptSssqDao optSssqDao;

    public void commit(ExamineOperationApplyReq req, SysUser user) {
        log.info("审核特殊手术[{},{}]", JSONUtil.toJsonStr(req),JSONUtil.toJsonStr(user));
        req.setSpys(user.getUserId());
        optSssqDao.commit(req);
    }

    public PageInfo<OperationApplyResp> list(QueryExamineListReq req, SysUser user) {
        log.info("查询手术单审核列表[{},{},{}]",JSONUtil.toJsonStr(req),JSONUtil.toJsonStr(user));
        if(req.getSpbz()==-1){
            req.setSpbz(null);
        }
        PageInfo<OperationApplyResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->optSssqDao.queryExamineList(req));
        resp.getList().forEach(s->{
            Long year = ChronoUnit.YEARS.between(s.getCsny().toLocalDateTime(), LocalDateTime.now());
            s.setBrnl(year+"岁");
        });
        return resp;
    }
}
