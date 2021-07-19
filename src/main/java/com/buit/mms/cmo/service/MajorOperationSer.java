package com.buit.mms.cmo.service;

import cn.hutool.json.JSONUtil;
import com.buit.mms.cmo.dao.CmoZdssDao;
import com.buit.mms.cmo.request.QueryMajorListReq;
import com.buit.mms.cmo.response.CmoZdssResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/10 15:36
 */
@Service
public class MajorOperationSer {

    static final Logger log = LoggerFactory.getLogger(MajorOperationSer.class);

    @Autowired
    CmoZdssDao cmoZdssDao;


    public void add(List<Integer> ssbmList) {
        log.info("添加重大手术[{}]", JSONUtil.toJsonStr(ssbmList));
        cmoZdssDao.insertSelectByDicJbbm(ssbmList);
    }

    public void remove(List<Integer> ssbmList) {
        log.info("移除重大手术[{}]",JSONUtil.toJsonStr(ssbmList));
        cmoZdssDao.remove(ssbmList);
    }

    public PageInfo<CmoZdssResp> list(QueryMajorListReq req) {
        log.info("查询重大手术列表[{}]",JSONUtil.toJsonStr(req));
        PageInfo<CmoZdssResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize()).doSelectPageInfo(()->cmoZdssDao.queryList(req.getPydm()));
        return resp;
    }

    public PageInfo<CmoZdssResp> uncheckedList(QueryMajorListReq req) {
        log.info("查询待选择手术列表[{}]",JSONUtil.toJsonStr(req));
        PageInfo<CmoZdssResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize()).doSelectPageInfo(()->cmoZdssDao.queryUncheckedList(req.getPydm()));
        return resp;
    }
}
