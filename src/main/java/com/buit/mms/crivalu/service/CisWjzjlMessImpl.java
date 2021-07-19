package com.buit.mms.crivalu.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.mms.crivalu.dao.CisWjzjlDao;
import com.buit.mms.crivalu.dto.CisWjzMessDto;
import com.buit.mq.NotifyConsumer;
import com.buit.mq.RabbitMqProducer;
import com.buit.system.service.SysYwqxkzService;

import cn.hutool.json.JSONUtil;

@Service
public class CisWjzjlMessImpl implements NotifyConsumer<CisWjzMessDto>{
	
	static final Logger logger = LoggerFactory.getLogger(CisWjzjlMessImpl.class);

	@Autowired
	private CisWjzjlDao cisWjzjlDao;
	
	@Autowired
    private RabbitMqProducer rabbit;
	
	@DubboReference
    private SysYwqxkzService sysYwqxkzService;
	
	@Override
	public void failedCallBack(CisWjzMessDto data, Throwable e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void notify(CisWjzMessDto data) {
		logger.info("CisWjzMessDto[{}]", JSONUtil.toJsonStr(data));
	}

}
