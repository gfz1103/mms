package com.buit.mms.antimi.api;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.commons.SysUser;
import com.buit.mms.antimi.dao.AmqcKjywsyhzksysDao;
import com.buit.mms.antimi.dao.AmqcKjywsysqDao;
import com.buit.mms.antimi.dao.AmqcKjywsysqypDao;
import com.buit.mms.antimi.model.AmqcKjywsyhzksys;
import com.buit.mms.antimi.model.AmqcKjywsysqyp;
import com.buit.mms.antimi.service.AmqcKjywsysqSer;
import com.buit.mms.antimi.service.AmqcKjywsysqService;

/**
 * 
 * @ClassName: AmqcKjywsysqServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年10月27日 下午5:09:59
 *
 */
@DubboService(timeout = 10000)
public class AmqcKjywsysqServiceImpl implements AmqcKjywsysqService{

	@Autowired
	private AmqcKjywsysqDao amqcKjywsysqDao;
	
	@Autowired
	private AmqcKjywsysqypDao amqcKjywsysqypDao;
	
	@Autowired
	private AmqcKjywsyhzksysDao amqcKjywsyhzksysDao;
	
	@Autowired
	private AmqcKjywsysqSer amqcKjywsysqSer;

	
	@Override
	public void deleteKjywSqdById(Integer id, Integer jgid) {
		amqcKjywsysqDao.deleteById(id);
		AmqcKjywsysqyp amqcKjywsysqyp = new AmqcKjywsysqyp();
		amqcKjywsysqyp.setSqdh(id);
		amqcKjywsysqyp.setYljgd(jgid);
		amqcKjywsysqypDao.removeByEntity(amqcKjywsysqyp);	
		AmqcKjywsyhzksys amqcKjywsyhzksys = new AmqcKjywsyhzksys();
		amqcKjywsyhzksys.setSqdh(id);
		amqcKjywsyhzksys.setYljgd(jgid);
		amqcKjywsyhzksysDao.removeByEntity(amqcKjywsyhzksys);
	}


	@Override
	public void cancelDeleteKjyw(Integer sqdh, SysUser user) {
		amqcKjywsysqSer.removeAntibiotics(sqdh, user);
	}

}
