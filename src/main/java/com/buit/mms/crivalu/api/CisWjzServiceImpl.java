package com.buit.mms.crivalu.api;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.mms.crivalu.dao.CisWjzjlDao;
import com.buit.mms.crivalu.model.CisWjzjl;
import com.buit.mms.crivalu.request.CisLisReportlimitUploadReq;
import com.buit.mms.crivalu.response.LisWjzResp;
import com.buit.mms.crivalu.service.CisWjzService;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.ReturnEntity;

import cn.hutool.core.bean.BeanUtil;

@DubboService(timeout = 10000)
public class CisWjzServiceImpl implements CisWjzService {
	
	@Autowired
	private CisWjzjlDao cisWjzjlDao;

	@Override
	public LisWjzResp reportLimitUpload(CisLisReportlimitUploadReq req) {
		CisWjzjl wjzjl = BeanUtil.toBean(req, CisWjzjl.class);
		wjzjl.setZt(0);
		wjzjl.setYljgd(ObjectToTypes.parseInt(req.getHospital_id()));
		wjzjl.setBgh(req.getReport_id());
		cisWjzjlDao.insert(wjzjl);	
		LisWjzResp lisResp = new LisWjzResp();
		CisWjzjl resp = cisWjzjlDao.getById(wjzjl.getWjzdh());
		if(resp != null) {
			lisResp.setCode("0");
		}else {
			lisResp.setCode("-1");
			ReturnEntity<String> returnEntity = new ReturnEntity<>();
			returnEntity.setError("ERROR_CIS_WJZ_0003", new String[] {req.getReport_id()});
			lisResp.setMessage(returnEntity.getMessage());
		}
		return lisResp;
	}

}
