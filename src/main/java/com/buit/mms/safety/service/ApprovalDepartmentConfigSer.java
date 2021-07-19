package com.buit.mms.safety.service;

import cn.hutool.json.JSONUtil;
import com.buit.mms.safety.dao.AdvPropDao;
import com.buit.mms.safety.dao.AdvShpzDao;
import com.buit.mms.safety.model.AdvShpz;
import com.buit.mms.safety.request.SaveApprovalDepartmentReq;
import com.buit.mms.safety.response.EventApprovalDepartmentResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 10:05
 */
@Service
public class ApprovalDepartmentConfigSer {

    static final Logger log = LoggerFactory.getLogger(ApprovalDepartmentConfigSer.class);

    @Autowired
    AdvShpzDao advShpzDao;
    @Autowired
    AdvPropDao advPropDao;

    public List<EventApprovalDepartmentResp> query(Integer jgid) {
        log.info("查询事件审批科室配置[{}]",jgid);
        return advPropDao.queryApprovalConfig(jgid);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(SaveApprovalDepartmentReq req, Integer userId) {
        log.info("保存事件审核科室配置[{},{}]", JSONUtil.toJsonStr(req),userId);
        List<AdvShpz> insertList = req.getShpz().stream().map(s->{
            AdvShpz shpz = new AdvShpz();
            shpz.setPropid(s.getId());
            shpz.setShks(s.getShks());
            shpz.setCzrgh(userId);
            return shpz;
        }).collect(Collectors.toList());
        List<Integer> propIds = req.getShpz().stream().map(s->s.getId()).collect(Collectors.toList());
        advShpzDao.delete(propIds);
        advShpzDao.batchInsert(insertList);
    }
}
