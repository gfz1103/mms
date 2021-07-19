package com.buit.mms.cmo.service;

import com.buit.mms.cmo.dao.OptSssqDao;
import com.buit.mms.cmo.model.OptSssq;
import com.buit.mms.cmo.response.IOptSssqResp;
import com.buit.utill.BeanUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/9 14:12
 */
@DubboService
public class OptSssqServiceImpl implements OptSssqService{

    @Autowired
    OptSssqDao optSssqDao;

    @Override
    public IOptSssqResp getBySqdh(Integer sqdh) {
        OptSssq sssq = optSssqDao.getById(sqdh);
        return BeanUtil.toBean(sssq,IOptSssqResp.class);
    }

    @Override
    public void updateTzzt(Integer sqdh, Integer tzgh) {
        optSssqDao.updateTzztBySqdh(sqdh,tzgh);
    }

    @Override
    public void deleteById(Integer sqdh) {
        optSssqDao.delete(sqdh);
    }

    @Override
    public List<IOptSssqResp> queryList(Integer zyh) {
        return optSssqDao.queryByZyh(zyh);
    }
}
