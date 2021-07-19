package com.buit.mms.antimi.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.cis.dctwork.service.CisHzyzService;
import com.buit.cis.im.response.ImHzryModel;
import com.buit.cis.im.service.ImHzryService;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.PageQuery;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.dto.LineContentDto;
import com.buit.dto.TargetConfig;
import com.buit.dto.WaitProcessMessage;
import com.buit.enums.CoverageTypeEnum;
import com.buit.enums.MessageLevelType;
import com.buit.enums.SystemTypeEnum;
import com.buit.mms.antimi.dao.AmqcKjywsyhzksysDao;
import com.buit.mms.antimi.dao.AmqcKjywsysqDao;
import com.buit.mms.antimi.dao.AmqcKjywsysqypDao;
import com.buit.mms.antimi.dao.OpCfKjywsqjlDao;
import com.buit.mms.antimi.dto.AmqcKjywMessDto;
import com.buit.mms.antimi.model.AmqcKjywsyhzksys;
import com.buit.mms.antimi.model.AmqcKjywsysq;
import com.buit.mms.antimi.model.AmqcKjywsysqyp;
import com.buit.mms.antimi.request.AmqcKjywsyhzksysReq;
import com.buit.mms.antimi.request.AmqcKjywsysqMzSaveReq;
import com.buit.mms.antimi.request.AmqcKjywsysqReq;
import com.buit.mms.antimi.request.AmqcKjywsysqSaveReq;
import com.buit.mms.antimi.request.AmqcKjywsysqStatisticsReq;
import com.buit.mms.antimi.request.AmqcKjywsysqypReq;
import com.buit.mms.antimi.response.AmqcKjywsysqResp;
import com.buit.mms.antimi.response.AmqcKjywsysqStatisticsResp;
import com.buit.mms.antimi.response.AmqcKjywsysqUseIntensityResp;
import com.buit.mms.antimi.response.AmqcKjywsysqUseRankingResp;
import com.buit.mms.antimi.response.AmqcKjywsysqUtilizationRateResp;
import com.buit.mms.antimi.response.AmqcKjywsysqypResp;
import com.buit.mms.antimi.response.AmqcKjywsysqypysResp;
import com.buit.mq.RabbitMqProducer;
import com.buit.op.request.IUpdateKjywxxReq;
import com.buit.op.service.OpCf01Service;
import com.buit.system.request.MessJsrOut;
import com.buit.system.service.DicKszdOutSer;
import com.buit.system.service.SysMessSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.ParamUtil;
import com.buit.utill.RedisFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;

/**
 * 抗菌药物使用申请单<br>
 * @author GONGFANGZHOU
 */
@Service
public class AmqcKjywsysqSer extends BaseManagerImp<AmqcKjywsysq,Integer> {

    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsysqSer.class);

    @Autowired
    private AmqcKjywsysqDao amqcKjywsysqDao;
    @DubboReference
	private OpCf01Service opCf01Service;
	@Autowired
    private OpCfKjywsqjlDao opCfKjywsqjlDao;

	@Override
    public AmqcKjywsysqDao getEntityMapper(){
        return amqcKjywsysqDao;
    }

    @DubboReference
    private CisHzyzService cisHzyzService;

    @Autowired
    private RedisFactory redisFactory;

    @Autowired
    private AmqcKjywsysqypDao amqcKjywsysqypDao;

    @Autowired
    private AmqcKjywsyhzksysDao amqcKjywsyhzksysDao;

    @DubboReference
    private SysMessSer sysMessSer;

    @DubboReference
    private ImHzryService imHzryService;

    @DubboReference
    private DicKszdOutSer dicKszdOutSer;
    
    @Autowired
    private RabbitMqProducer rabbit;

    /**
     * 抗菌药物申请单审批
     * @Title: updateAntibioticsExamine
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param sqdh
     * @param @param shyj
     * @param @param shjg
     * @param @param user    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
	public void updateAntibioticsExamine(Integer sqdh, String shyj, Integer shjg, SysUser user) {
		Map<String, Object> parameters = new HashMap<String, Object>(16);
		parameters.put("sqdh", sqdh);
		parameters.put("shyj", shyj);
		parameters.put("shjg", shjg);
		parameters.put("shrdm", user.getUserId());//parameters.put("shrdm", user.getHospitalId());更改bug
		parameters.put("shsj", DateUtil.date().toTimestamp());
		parameters.put("zt", 1);
		amqcKjywsysqDao.updateAntibioticsExamine(parameters);
		//更新医嘱审批状态
		Integer kjywsp = 1;
		if(shjg.intValue() == 0) {
			kjywsp = 2;
		}

		AmqcKjywsysq amqcKjywsysq = amqcKjywsysqDao.findByEntity(ParamUtil.instance().put("sqdh", sqdh)).get(0);
		if(amqcKjywsysq.getZyh() != null && amqcKjywsysq.getZyh().intValue() != 0) {
			cisHzyzService.updateYzAntibiotics(kjywsp, sqdh);
		}else{
			opCfKjywsqjlDao.updateZt(1, sqdh);
		}
	}

	/**
	 * 抗菌药物申请单保存
	 * @Title: saveAntibiotics
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param list
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveAntibiotics(List<AmqcKjywsysqSaveReq> list, SysUser user) {
		Integer jgid = user.getHospitalId();
		for(AmqcKjywsysqSaveReq amqcKjywsysqSaveReq : list) {
			amqcKjywsysqSaveReq.setYljgd(jgid);
			Integer sqdh = redisFactory.getTableKey(TableName.DB_NAME, TableName.AMQC_KJYWSYSQ);
			amqcKjywsysqSaveReq.setZt(0);
			List<AmqcKjywsysqypReq> ypList = amqcKjywsysqSaveReq.getYpList();
			for(AmqcKjywsysqypReq amqcKjywsysqypReq : ypList) {
				amqcKjywsysqypReq.setYljgd(jgid);
				amqcKjywsysqypReq.setSqdh(sqdh);
				amqcKjywsysqypReq.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.AMQC_KJYWSYSQYP));
				AmqcKjywsysqyp amqcKjywsysqyp = BeanUtil.toBean(amqcKjywsysqypReq, AmqcKjywsysqyp.class);
				amqcKjywsysqypDao.insert(amqcKjywsysqyp);
				//sqdh写入医嘱
				cisHzyzService.updateAntibioticsSqidByJlxh(sqdh, amqcKjywsysqypReq.getJlxh());
				cisHzyzService.updateAntibioticsTjByJlxh(amqcKjywsysqypReq.getJlxh());
			}
			if(amqcKjywsysqSaveReq.getSfwtsjsyhz() != null && amqcKjywsysqSaveReq.getSfwtsjsyhz() == 1) {
				List<AmqcKjywsyhzksysReq> hzList = amqcKjywsysqSaveReq.getHzList();
				amqcKjywsysqSaveReq.setZt(2);

				List<Integer> userIdList = new ArrayList<Integer>();
				for(AmqcKjywsyhzksysReq amqcKjywsyhzksysReq : hzList) {
					amqcKjywsyhzksysReq.setYljgd(jgid);
					amqcKjywsyhzksysReq.setSqdh(sqdh);
					amqcKjywsyhzksysReq.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.AMQC_KJYWSYHZKSYS));
					AmqcKjywsyhzksys amqcKjywsyhzksys = BeanUtil.toBean(amqcKjywsyhzksysReq, AmqcKjywsyhzksys.class);
					amqcKjywsyhzksysDao.insert(amqcKjywsyhzksys);
	    			//发送用户
					userIdList.add(ObjectToTypes.parseInt(amqcKjywsyhzksysReq.getYqhzysdm()));
				}

				ImHzryModel model = imHzryService.queryPatientBaseInfo(amqcKjywsysqSaveReq.getZyh());
				WaitProcessMessage<AmqcKjywMessDto> wait = new WaitProcessMessage<AmqcKjywMessDto>();
				wait.setTitle("特殊级抗菌药物会诊申请");
				List<LineContentDto> dtoList = new ArrayList<LineContentDto>();
				LineContentDto dto = new LineContentDto();
				dto.setLabel("床号");
				dto.setContent(model.getBrch());
				dtoList.add(dto);
				dto = new LineContentDto();
				dto.setLabel("患者");
				dto.setContent(model.getBrxm());
				dtoList.add(dto);
				dto = new LineContentDto();
				dto.setContent(model.getBrxb() == 1 ? "男" : "女");
				dtoList.add(dto);
				dto = new LineContentDto();
				dto.setContent(dicKszdOutSer.getNameById(model.getBrks()));
				dtoList.add(dto);
				wait.setContents(dtoList);
				wait.setData(new AmqcKjywMessDto(sqdh));
				wait.setRelativePath("im/useApprovalDoctTS");
				String uuid = rabbit.sendAlertMsg("specialConsultation", wait, MessageLevelType.high,
				TargetConfig.toSystemUser(SystemTypeEnum.zyysz, userIdList, CoverageTypeEnum.onlineAndOffline), false);
				amqcKjywsysqSaveReq.setMessid(uuid);
			}
			amqcKjywsysqSaveReq.setSqdh(sqdh);
			AmqcKjywsysq amqcKjywsysq = BeanUtil.toBean(amqcKjywsysqSaveReq, AmqcKjywsysq.class);
			amqcKjywsysqDao.insert(amqcKjywsysq);
		}
	}

	/**
	 * 门诊抗菌药物申请单保存
	 * @Title: saveMzAntibiotics
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param list
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer saveMzAntibiotics(AmqcKjywsysqMzSaveReq amqcKjywsysqSaveReq, SysUser user) {
		Integer jgid = user.getHospitalId();
		amqcKjywsysqSaveReq.setYljgd(jgid);
		Integer sqdh = redisFactory.getTableKey(TableName.DB_NAME, TableName.AMQC_KJYWSYSQ);
		amqcKjywsysqSaveReq.setZt(0);
		List<AmqcKjywsysqypReq> ypList = amqcKjywsysqSaveReq.getYpList();
		List<IUpdateKjywxxReq> kjywxxList = new ArrayList<>();
		for(AmqcKjywsysqypReq amqcKjywsysqypReq : ypList) {
			amqcKjywsysqypReq.setYljgd(jgid);
			amqcKjywsysqypReq.setSqdh(sqdh);
			amqcKjywsysqypReq.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.AMQC_KJYWSYSQYP));
			AmqcKjywsysqyp amqcKjywsysqyp = BeanUtil.toBean(amqcKjywsysqypReq, AmqcKjywsysqyp.class);
			amqcKjywsysqypDao.insert(amqcKjywsysqyp);

			IUpdateKjywxxReq kjywxx = new IUpdateKjywxxReq();
			kjywxx.setSbxh(amqcKjywsysqypReq.getSbxh());
			kjywxx.setKjywsp(0);
			kjywxx.setKjsqdh(sqdh);
			kjywxxList.add(kjywxx);
		}
		//批量更新抗菌药物申请号
		//opCf01Service.updateKjywxx(kjywxxList);

		//汇诊
		if(amqcKjywsysqSaveReq.getSfwtsjsyhz() != null && amqcKjywsysqSaveReq.getSfwtsjsyhz() == 1) {
			List<AmqcKjywsyhzksysReq> hzList = amqcKjywsysqSaveReq.getHzList();
			amqcKjywsysqSaveReq.setZt(2);
			for(AmqcKjywsyhzksysReq amqcKjywsyhzksysReq : hzList) {
				amqcKjywsyhzksysReq.setYljgd(jgid);
				amqcKjywsyhzksysReq.setSqdh(sqdh);
				amqcKjywsyhzksysReq.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.AMQC_KJYWSYHZKSYS));
				AmqcKjywsyhzksys amqcKjywsyhzksys = BeanUtil.toBean(amqcKjywsyhzksysReq, AmqcKjywsyhzksys.class);
				amqcKjywsyhzksysDao.insert(amqcKjywsyhzksys);
			}
		}
		amqcKjywsysqSaveReq.setSqdh(sqdh);
		AmqcKjywsysq amqcKjywsysq = BeanUtil.toBean(amqcKjywsysqSaveReq, AmqcKjywsysq.class);
		amqcKjywsysqDao.insert(amqcKjywsysq);

		return sqdh;
	}


	/**
	 * 抗菌药物申请单删除
	 * @Title: removeAntibiotics
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sqdh
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void removeAntibiotics(Integer sqdh, SysUser user) {
		amqcKjywsysqDao.deleteById(sqdh);
		cisHzyzService.removeAntibioticsStatus(sqdh);
        AmqcKjywsysqyp amqcKjywsysqyp = new AmqcKjywsysqyp();
        amqcKjywsysqyp.setSqdh(sqdh);
        amqcKjywsysqyp.setYljgd(user.getHospitalId());
        amqcKjywsysqypDao.removeByEntity(amqcKjywsysqyp);

        AmqcKjywsyhzksys amqcKjywsyhzksys = new AmqcKjywsyhzksys();
        amqcKjywsyhzksys.setSqdh(sqdh);
        amqcKjywsyhzksys.setYljgd(user.getHospitalId());
        amqcKjywsyhzksysDao.removeByEntity(amqcKjywsysqyp);
	}

	/**
	 * 查询抗菌药物申请单信息
	 * @Title: getSqdInfoBySqdh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sqdh
	 * @param @param user
	 * @param @return    设定文件
	 * @return AmqcKjywsysqypysResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public AmqcKjywsysqypysResp getSqdInfoBySqdh(Integer sqdh, SysUser user) {
		AmqcKjywsysqypysResp amqcKjywsysqypysResp = amqcKjywsysqDao.getSqdInfoBySqdh(sqdh,
				user.getHospitalId());

		List<AmqcKjywsysqypResp> ypList = amqcKjywsysqypDao.queryKjywYpInfo(user.getHospitalId(), sqdh);
		if(CollectionUtils.isNotEmpty(ypList)) {
			amqcKjywsysqypysResp.setYpList(ypList);
		}

		AmqcKjywsyhzksys amqcKjywsyhzksys = new AmqcKjywsyhzksys();
		amqcKjywsyhzksys.setYljgd(user.getHospitalId());
		amqcKjywsyhzksys.setSqdh(sqdh);
		List<AmqcKjywsyhzksys> hzList = amqcKjywsyhzksysDao.findByEntity(amqcKjywsyhzksys);
		if(CollectionUtils.isNotEmpty(hzList)) {
			amqcKjywsysqypysResp.setHzList(hzList);
		}
		return amqcKjywsysqypysResp;

	}

	/**
	 * 越级或特殊级使用抗菌药物统计
	 * @Title: antibioticsStatistics
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param req
	 * @param @param page
	 * @param @return    设定文件
	 * @return PageInfo<AmqcKjywsysqStatisticsResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public PageInfo<AmqcKjywsysqStatisticsResp> antibioticsStatistics(AmqcKjywsysqStatisticsReq req,
			PageQuery page) {
		PageInfo<AmqcKjywsysqStatisticsResp> pageInfo = new PageInfo<AmqcKjywsysqStatisticsResp>(new ArrayList<AmqcKjywsysqStatisticsResp>());
		if("2".equals(ObjectToTypes.parseString(req.getSqlx()))) {
			pageInfo = PageHelper.startPage(
	        		page.getPageNum(), page.getPageSize()).doSelectPageInfo(
	                () -> amqcKjywsysqDao.antibioticsStatistics(req)
	        );
		}else {
			pageInfo = PageHelper.startPage(
					page.getPageNum(), page.getPageSize()).doSelectPageInfo(
					() -> amqcKjywsysqDao.antibioticsStatisticsforMz(req)
			);
		}
		return pageInfo;
	}

	/**
	 * 抗菌药物使用强度统计
	 * @Title: antibioticsUseIntensity
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param req
	 * @param @param page
	 * @param @return    设定文件
	 * @return PageInfo<AmqcKjywsysqUseIntensityResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public PageInfo<AmqcKjywsysqUseIntensityResp> antibioticsUseIntensity(AmqcKjywsysqStatisticsReq req, PageQuery page) {
		PageInfo<AmqcKjywsysqUseIntensityResp> pageInfo = new PageInfo<AmqcKjywsysqUseIntensityResp>(new ArrayList<AmqcKjywsysqUseIntensityResp>());
		//按科室统计
		if("1".equals(ObjectToTypes.parseString(req.getSqlx()))) {
			pageInfo = PageHelper.startPage(
	        		page.getPageNum(), page.getPageSize()).doSelectPageInfo(
	                () -> amqcKjywsysqDao.antibioticsUseIntensityByKs(req)
	        );
		}else {
			pageInfo = PageHelper.startPage(
	        		page.getPageNum(), page.getPageSize()).doSelectPageInfo(
	                () -> amqcKjywsysqDao.antibioticsUseIntensityByLb(req)
	        );
		}
		return pageInfo;
	}

	/**
	 * 抗菌药物使用率统计
	 * @Title: antibioticsUtilizationRate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param req
	 * @param @param page
	 * @param @return    设定文件
	 * @return PageInfo<AmqcKjywsysqUtilizationRateResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public PageInfo<AmqcKjywsysqUtilizationRateResp> antibioticsUtilizationRate(AmqcKjywsysqStatisticsReq req, PageQuery page) {
		PageInfo<AmqcKjywsysqUtilizationRateResp> pageInfo = new PageInfo<AmqcKjywsysqUtilizationRateResp>(new ArrayList<AmqcKjywsysqUtilizationRateResp>());
		if("2".equals(ObjectToTypes.parseString(req.getSqlx()))) {
			pageInfo = PageHelper.startPage(
	        		page.getPageNum(), page.getPageSize()).doSelectPageInfo(
	                () -> amqcKjywsysqDao.antibioticsUtilizationRate(req)
	        );
		}else {
			pageInfo = PageHelper.startPage(
					page.getPageNum(), page.getPageSize()).doSelectPageInfo(
					() -> amqcKjywsysqDao.antibioticsUtilizationRateForMz(req)
			);
		}
		return pageInfo;
	}

	/**
	 * 抗菌药物使用排名
	 * @Title: antibioticsUseRanking
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param req
	 * @param @param page
	 * @param @return    设定文件
	 * @return PageInfo<AmqcKjywsysqUseRankingResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public PageInfo<AmqcKjywsysqUseRankingResp> antibioticsUseRanking(AmqcKjywsysqStatisticsReq req, PageQuery page) {
		PageInfo<AmqcKjywsysqUseRankingResp> pageInfo = new PageInfo<AmqcKjywsysqUseRankingResp>(new ArrayList<AmqcKjywsysqUseRankingResp>());
		if("2".equals(ObjectToTypes.parseString(req.getSqlx()))) {
			pageInfo = PageHelper.startPage(
	        		page.getPageNum(), page.getPageSize()).doSelectPageInfo(
	                () -> amqcKjywsysqDao.antibioticsUseRanking(req)
	        );
		}else {//门诊
			pageInfo = PageHelper.startPage(
					page.getPageNum(), page.getPageSize()).doSelectPageInfo(
					() -> amqcKjywsysqDao.antibioticsUseRankingForMz(req)
			);
		}
		return pageInfo;
	}

	/**
	 * 特殊级抗菌药物会诊审批
	 * @Title: updateSpecialExamine
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sqdh
	 * @param @param hzyj
	 * @param @param hzjg
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void updateSpecialExamine(Integer sqdh, String hzyj, Integer hzjg, SysUser user) {
		Map<String, Object> parameters = new HashMap<String, Object>(16);
		parameters.put("sqdh", sqdh);
		parameters.put("hzjg", hzjg);
		parameters.put("shrdm", user.getUserId());
		parameters.put("zt", 3);
		amqcKjywsysqDao.updateSpecialExamine(parameters);


		Map<String, Object> param = new HashMap<String, Object>(16);
		param.put("sqdh", sqdh);
		param.put("hzyj", hzyj);
		param.put("hzsj", DateUtil.date().toTimestamp());
		param.put("hzysdm", user.getUserId());
		amqcKjywsyhzksysDao.updateHzyjInfo(param);
		//更新医嘱审批状态
		Integer kjywsp = 1;
		if(hzjg.intValue() == 0) {
			kjywsp = 2;
		}

		AmqcKjywsysq amqcKjywsysq = amqcKjywsysqDao.findByEntity(ParamUtil.instance().put("sqdh", sqdh)).get(0);
		if(amqcKjywsysq.getZyh() != null && amqcKjywsysq.getZyh().intValue() != 0) {
			cisHzyzService.updateYzAntibiotics(kjywsp, sqdh);
//			sysMessSer.updateFinishByCallId(sqdh, 5);
		}else{
			opCfKjywsqjlDao.updateZt(3, sqdh);
		}
	}

	/**
	 * 住院抗菌药物申请单审核查询
	 * @Title: queryVerifyAntibioticsInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param amqcKjywsysqReq
	 * @param @param page
	 * @param @param user
	 * @param @return    设定文件
	 * @return PageInfo<AmqcKjywsysqResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public PageInfo<AmqcKjywsysqResp> queryVerifyAntibioticsInfo(AmqcKjywsysqReq amqcKjywsysqReq, PageQuery page, SysUser user) {
		PageInfo<AmqcKjywsysqResp> pageInfo = new PageInfo<AmqcKjywsysqResp>(new ArrayList<AmqcKjywsysqResp>());
		if("1".equals(ObjectToTypes.parseString(amqcKjywsysqReq.getCxlx()))) {
			pageInfo = PageHelper.startPage(
	        		page.getPageNum(), page.getPageSize()).doSelectPageInfo(
	                () -> amqcKjywsysqDao.queryVerifyAntibioticsInfo(amqcKjywsysqReq)
	        );
		}else {
			amqcKjywsysqReq.setYqhzysdm(user.getUserId());
			pageInfo = PageHelper.startPage(
	        		page.getPageNum(), page.getPageSize()).doSelectPageInfo(
	                () -> amqcKjywsysqDao.queryHzVerifyAntibioticsInfo(amqcKjywsysqReq)
	        );
		}
		return pageInfo;
	}
}
