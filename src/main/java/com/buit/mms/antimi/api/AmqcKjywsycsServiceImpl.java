package com.buit.mms.antimi.api;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.mms.antimi.dao.AmqcKjywsycsDao;
import com.buit.mms.antimi.model.AmqcKjywsycs;
import com.buit.mms.antimi.service.AmqcKjywsycsService;

/**
 * 抗菌药物使用参数外部接口实现
 * @ClassName: AmqcKjywsycsServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年10月15日 下午3:41:27
 *
 */
@DubboService(timeout = 10000)
public class AmqcKjywsycsServiceImpl implements AmqcKjywsycsService{

	@Autowired
	private AmqcKjywsycsDao amqcKjywsycsDao;
	
	@Override
	public AmqcKjywsycs getSycsById(Integer id) {
		return amqcKjywsycsDao.getById(id);
	}

}
