package com.buit.mms.cmo.service;

import cn.hutool.json.JSONUtil;
import com.buit.mms.cmo.dao.CmoSsxgDao;
import com.buit.mms.cmo.enums.RelevantTypeEnum;
import com.buit.mms.cmo.model.CmoSsxg;
import com.buit.mms.cmo.request.SaveOperationRelevantReq;
import com.buit.mms.cmo.response.OperationRelevantResp;
import com.buit.utill.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/5 16:45
 */
@Service
public class OperationRelevantSer {

    static final Logger log = LoggerFactory.getLogger(OperationRelevantSer.class);

    @Autowired
    CmoSsxgDao cmoSsxgDao;

    public List<OperationRelevantResp> list(RelevantTypeEnum relevant) {
        log.info("查询手术相关配置列表[{}]",relevant);
        List<CmoSsxg> list = cmoSsxgDao.queryAll(relevant);
        return BeanUtil.toBeans(list, OperationRelevantResp.class);
    }

    public void save(SaveOperationRelevantReq req) {
        log.info("保存手术相关配置[{}]", JSONUtil.toJsonStr(req));
        cmoSsxgDao.replace(BeanUtil.toBean(req, CmoSsxg.class));
    }


    public void delete(Integer id) {
        log.info("删除手术相关配置[{}]",id);
        cmoSsxgDao.deleteById(id);
    }

    public void enable(Integer id) {
        log.info("停用/启用手术相关配置[{}]",id);
        cmoSsxgDao.updateStatusById(id);
    }
}
