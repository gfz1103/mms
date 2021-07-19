package com.buit.mms.crivalu.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.aop.lock.Locked;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.dto.TargetConfig;
import com.buit.enums.CoverageTypeEnum;
import com.buit.enums.SystemTypeEnum;
import com.buit.mms.crivalu.dao.CisWjzjlDao;
import com.buit.mms.crivalu.model.CisWjzjl;
import com.buit.mq.RabbitMqProducer;
import com.buit.system.model.SysYwqxkz;
import com.buit.system.service.SysYwqxkzService;
/**
 * 危急值记录表<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisWjzjlSer extends BaseManagerImp<CisWjzjl,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisWjzjlSer.class);
    
    @Autowired
    private CisWjzjlDao cisWjzjlDao;
    
    @Override
    public CisWjzjlDao getEntityMapper(){        
        return cisWjzjlDao;
    }
    
    @Autowired
    private RabbitMqProducer rabbit;
    
    @DubboReference
    private SysYwqxkzService sysYwqxkzService;

    /**
     * 危急值更新通知信息
     * @Title: updateNoticeInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param tzysdm
     * @param @param wjzdh    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Locked(value = {"updateNoticeInfo_#[wjzdh]"})
	public void updateNoticeInfo(Integer tzysdm, Integer wjzdh, Integer userId) {
		CisWjzjl wjzjl = cisWjzjlDao.getById(wjzdh);
		if(wjzjl != null) {
			if(wjzjl.getZt() > 1) {
				throw BaseException.create("ERROR_CIS_WJZ_0001");
			}
			cisWjzjlDao.updateNoticeInfo(tzysdm, wjzdh);
			List<Integer> userIdList = new ArrayList<Integer>();
			SysYwqxkz sysYwqxkz = new SysYwqxkz();
			sysYwqxkz.setKsdm(wjzjl.getBqdm());
			sysYwqxkz.setYwlb(1);
			sysYwqxkz.setJgid(wjzjl.getYljgd());
			List<SysYwqxkz> qxkzList = sysYwqxkzService.findByEntity(sysYwqxkz);
			for(SysYwqxkz qxkz : qxkzList) {
				if(!userId.equals(qxkz.getYgdm())) {
					userIdList.add(qxkz.getYgdm());
				}
			}
			rabbit.cancelAlertMsg(wjzjl.getMessid(), TargetConfig.toSystemUser(SystemTypeEnum.zyhsz, 
					userIdList, CoverageTypeEnum.onlineAndOffline));
		}
	}

	/**
	 * 危急值更新处置信息
	 * @Title: updateDealInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param userId
	 * @param @param czcs
	 * @param @param wjzdh    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    @Locked(value = {"updateDealInfo_#[wjzdh]"})
	public void updateDealInfo(Integer userId, String czcs, Integer wjzdh) {
		CisWjzjl cisWjzjl = cisWjzjlDao.getById(wjzdh);
		if(cisWjzjl != null) {
			if(cisWjzjl.getZt().intValue() == 3) {
				throw BaseException.create("ERROR_CIS_WJZ_0002");
			}
	    	//根据状态是否更新通知信息
	    	if(cisWjzjl.getZt().intValue() == 1) {
	    		cisWjzjlDao.updateNoticeDealInfo(userId, czcs, wjzdh);
	    	}else if(cisWjzjl.getZt().intValue() == 2) {
	    		cisWjzjlDao.updateDealInfo(userId, czcs, wjzdh);
	    	}
	    	List<Integer> userIdList = new ArrayList<Integer>();
			SysYwqxkz sysYwqxkz = new SysYwqxkz();
			sysYwqxkz.setKsdm(cisWjzjl.getKsdm());
			sysYwqxkz.setYwlb(6);
			sysYwqxkz.setJgid(cisWjzjl.getYljgd());
			List<SysYwqxkz> qxkzList = sysYwqxkzService.findByEntity(sysYwqxkz);
			for(SysYwqxkz qxkz : qxkzList) {
				if(!userId.equals(qxkz.getYgdm())) {
					userIdList.add(qxkz.getYgdm());
				}
			}
			rabbit.cancelAlertMsg(cisWjzjl.getYsmessid(), TargetConfig.toSystemUser(SystemTypeEnum.zyysz, 
					userIdList, CoverageTypeEnum.onlineAndOffline));
			if(cisWjzjl.getZt().intValue() >= 1) {
				userIdList = new ArrayList<Integer>();
				sysYwqxkz = new SysYwqxkz();
				sysYwqxkz.setKsdm(cisWjzjl.getBqdm());
				sysYwqxkz.setYwlb(1);
				sysYwqxkz.setJgid(cisWjzjl.getYljgd());
				qxkzList = sysYwqxkzService.findByEntity(sysYwqxkz);
				for(SysYwqxkz qxkz : qxkzList) {
					if(!userId.equals(qxkz.getYgdm())) {
						userIdList.add(qxkz.getYgdm());
					}
				}
				rabbit.cancelAlertMsg(cisWjzjl.getMessid(), TargetConfig.toSystemUser(SystemTypeEnum.zyhsz, 
						userIdList, CoverageTypeEnum.onlineAndOffline));
			}
	    	
		}
	}
}
