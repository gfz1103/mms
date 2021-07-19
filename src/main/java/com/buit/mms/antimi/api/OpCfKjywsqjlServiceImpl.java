package com.buit.mms.antimi.api;

import com.buit.mms.antimi.dao.OpCfKjywsqjlDao;
import com.buit.mms.antimi.model.OpCfKjywsqjl;
import com.buit.mms.antimi.response.OpCfKjywsqjlResp;
import com.buit.mms.antimi.service.OpCfKjywsqjlService;
import com.buit.utill.BeanUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author weijing
 * @Date 2020-12-04 13:32
 * @Description
 **/
@DubboService(timeout = 10000)
public class OpCfKjywsqjlServiceImpl implements OpCfKjywsqjlService {
    @Autowired
    private OpCfKjywsqjlDao opCfKjywsqjlDao;

    /**
     * 通过药品序号和就诊序号查询抗菌药物申请单信息
     * @param ypxh
     * @param jzxh
     * @return
     */
    @Override
    public OpCfKjywsqjlResp queryCfKjywjl(Integer ypxh, Integer jzxh) {
        OpCfKjywsqjl opCfKjywsqjl = opCfKjywsqjlDao.queryCfkjwsqjl(ypxh, jzxh);
        OpCfKjywsqjlResp opCfKjywsqjlResp = BeanUtil.toBean(opCfKjywsqjl, OpCfKjywsqjlResp.class);
        return opCfKjywsqjlResp;
    }
}
