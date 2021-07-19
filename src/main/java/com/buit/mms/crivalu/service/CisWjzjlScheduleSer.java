package com.buit.mms.crivalu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.buit.commons.BaseManagerImp;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.dto.LineContentDto;
import com.buit.dto.TargetConfig;
import com.buit.dto.WaitProcessMessage;
import com.buit.enums.CoverageTypeEnum;
import com.buit.enums.MessageLevelType;
import com.buit.enums.SystemTypeEnum;
import com.buit.mms.crivalu.dao.CisWjzjlDao;
import com.buit.mms.crivalu.dto.CisWjzMessDto;
import com.buit.mms.crivalu.enums.CisWjzEnum;
import com.buit.mms.crivalu.model.CisWjzjl;
import com.buit.mq.RabbitMqProducer;
import com.buit.system.model.SysYwqxkz;
import com.buit.system.response.HrPersonnelModel;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.HrPersonnelService;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.service.SysYwqxkzService;
import com.buit.system.utill.ObjectToTypes;

import cn.hutool.core.date.DateUtil;
/**
 * 
 * @ClassName: CisWjzjlScheduleSer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2021年5月13日 下午1:39:24
 *
 */
@Service
public class CisWjzjlScheduleSer extends BaseManagerImp<CisWjzjl,Integer> {

	static final Logger logger = LoggerFactory.getLogger(CisWjzjlScheduleSer.class);
    
    @Autowired
    private CisWjzjlDao cisWjzjlDao;
    
    @Override
    public CisWjzjlDao getEntityMapper(){        
        return cisWjzjlDao;
    }
    
    @DubboReference
    private HrPersonnelService hrPersonnelService;
    
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;
    
    @DubboReference
    private SysYwqxkzService sysYwqxkzService;
    
    @Autowired
    private RabbitMqProducer rabbit;
    
//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void cisWjzjlScheduleTask() {
//    	HrPersonnelModel hrPersonnel = hrPersonnelService.getPersonnelById(0);
//    	Integer jgid = hrPersonnel.getOrganizcode();
//    	SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.WJZSJ);
//    	Double time = sysXtcs == null ? 5 : ObjectToTypes.parseDouble(sysXtcs.getCsz());
//    	Double nowTime = ObjectToTypes.parseDouble(DateUtil.minute(DateUtil.date()));
//    	if(nowTime%time == 0) {
//    		List<Map<String, Object>> list = cisWjzjlDao.queryCisWjzHzInfo(jgid);
//    		for(Map<String, Object> map : list) {
//    			WaitProcessMessage<CisWjzMessDto> wait = new WaitProcessMessage<CisWjzMessDto>();
//    			wait.setTitle("危急值");
//    			List<LineContentDto> dtoList = new ArrayList<LineContentDto>();
//    			LineContentDto dto = new LineContentDto();
//    			dto.setLabel("床号");
//    			dto.setContent(ObjectToTypes.parseString(map.get("BRCH")));
//    			dtoList.add(dto);
//    			dto = new LineContentDto();
//    			dto.setLabel("患者");
//    			dto.setContent(ObjectToTypes.parseString(map.get("BRXM")));
//    			dtoList.add(dto);
//    			dto = new LineContentDto();
//    			dto.setContent(ObjectToTypes.parseString(map.get("XB")));
//    			dtoList.add(dto);
//    			dto = new LineContentDto();
//    			dto.setContent(ObjectToTypes.parseString(map.get("OFFICENAME")));
//    			dtoList.add(dto);
//    			wait.setContents(dtoList);
//    			wait.setData(new CisWjzMessDto(ObjectToTypes.parseInt(map.get("WJZDH")), true,
//    					CisWjzEnum.nisCriticalValue.name(), ObjectToTypes.parseString(map.get("BRCH")), 
//    					ObjectToTypes.parseString(map.get("BRXM")), ObjectToTypes.parseString(map.get("XB")),
//    					ObjectToTypes.parseString(map.get("OFFICENAME"))));
//    			wait.setRelativePath("2");
//    			List<Integer> userIdList = new ArrayList<Integer>();
//				SysYwqxkz sysYwqxkz = new SysYwqxkz();
//    			sysYwqxkz.setKsdm(ObjectToTypes.parseInt(map.get("BQDM")));
//    			sysYwqxkz.setYwlb(1);
//    			sysYwqxkz.setJgid(jgid);
//    			List<SysYwqxkz> qxkzList = sysYwqxkzService.findByEntity(sysYwqxkz);
//    			for(SysYwqxkz qxkz : qxkzList) {
//    				if(!"0".equals(ObjectToTypes.parseString(qxkz.getYgdm()))) {
//    					userIdList.add(qxkz.getYgdm());
//					}
//    			}
//
//    			//给护士站发送消息
//    			if(CollectionUtils.isNotEmpty(userIdList)) {
//    				logger.error("护士发送消息" + userIdList);
//    				String nisuuid = rabbit.sendAlertMsg(CisWjzEnum.nisCriticalValue.name(), wait, MessageLevelType.high, 
//    						TargetConfig.toSystemUser(SystemTypeEnum.zyhsz, userIdList, CoverageTypeEnum.onlineAndOffline), true);
//    				cisWjzjlDao.updateWjzMessIdByWjzdh(nisuuid, ObjectToTypes.parseInt(map.get("WJZDH")));
//    			}
//
//    			wait.setData(new CisWjzMessDto(ObjectToTypes.parseInt(map.get("WJZDH")), true,
//    					CisWjzEnum.cisCriticalValue.name(), ObjectToTypes.parseString(map.get("BRCH")), 
//    					ObjectToTypes.parseString(map.get("BRXM")), ObjectToTypes.parseString(map.get("XB")),
//    					ObjectToTypes.parseString(map.get("OFFICENAME"))));
//    			userIdList = new ArrayList<Integer>();
//    			sysYwqxkz = new SysYwqxkz();
//    			sysYwqxkz.setKsdm(ObjectToTypes.parseInt(map.get("KSDM")));
//				sysYwqxkz.setYwlb(6);
//				sysYwqxkz.setJgid(jgid);
//				qxkzList = sysYwqxkzService.findByEntity(sysYwqxkz);
//				for(SysYwqxkz qxkz : qxkzList) {
//					if(!"0".equals(ObjectToTypes.parseString(qxkz.getYgdm()))) {
//						userIdList.add(qxkz.getYgdm());
//					}
//				}
//
//				//给医生站发送消息
//				if(CollectionUtils.isNotEmpty(userIdList)) {
//					logger.error("医生发送消息" + userIdList);
//	    			String uuid = rabbit.sendAlertMsg(CisWjzEnum.cisCriticalValue.name(), wait, MessageLevelType.high, 
//	    			TargetConfig.toSystemUser(SystemTypeEnum.zyysz, userIdList, CoverageTypeEnum.onlineAndOffline), true);	
//	    			cisWjzjlDao.updateWjzYsMessIdByWjzdh(uuid, ObjectToTypes.parseInt(map.get("WJZDH")));
//				}
//    		}
//    	}
//    }

}
