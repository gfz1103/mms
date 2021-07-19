package com.buit.mms.antimi.api;

import java.util.Map;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.mms.antimi.dao.AmqcKjywsysqypDao;
import com.buit.mms.antimi.service.AmqcKjywsysqypService;

/**
 * 抗菌药药品对外接口实现
 * @ClassName: AmqcKjywsysqypServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年10月19日 下午8:09:42
 *
 */
@DubboService(timeout = 10000)
public class AmqcKjywsysqypServiceImpl implements AmqcKjywsysqypService{

	@Autowired
	private AmqcKjywsysqypDao amqcKjywsysqypDao;
	
	/**
	 * 抗菌药物天数校验
	 * @Title: checkDaysKjyw
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param parameters
	 * @param @return    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public long checkDaysKjyw(Map<String, Object> parameters) {
		return amqcKjywsysqypDao.checkDaysKjyw(parameters);
	}

}
