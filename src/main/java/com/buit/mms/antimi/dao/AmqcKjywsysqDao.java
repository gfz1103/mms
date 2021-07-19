package com.buit.mms.antimi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.commons.EntityDao;
import com.buit.mms.antimi.model.AmqcKjywsysq;
import com.buit.mms.antimi.request.AmqcKjywsysqReq;
import com.buit.mms.antimi.request.AmqcKjywsysqStatisticsReq;
import com.buit.mms.antimi.response.AmqcKjywsysqResp;
import com.buit.mms.antimi.response.AmqcKjywsysqStatisticsResp;
import com.buit.mms.antimi.response.AmqcKjywsysqUseIntensityResp;
import com.buit.mms.antimi.response.AmqcKjywsysqUseRankingResp;
import com.buit.mms.antimi.response.AmqcKjywsysqUtilizationRateResp;
import com.buit.mms.antimi.response.AmqcKjywsysqypysResp;
/**
 * 抗菌药物使用申请单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface AmqcKjywsysqDao extends EntityDao<AmqcKjywsysq,Integer>{


	 /**
	  * 抗菌药物申请单查询
	  * @Title: queryAntibioticsInfo
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param amqcKjywsysqReq
	  * @param @return    设定文件
	  * @return List<AmqcKjywsysqResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<AmqcKjywsysqResp> queryAntibioticsInfo(AmqcKjywsysqReq amqcKjywsysqReq);


	 /**
	  * 审核抗菌药物申请单
	  * @Title: updateAntibioticsExamine
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param parameters    设定文件
	  * @return void    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 void updateAntibioticsExamine(Map<String, Object> parameters);


	 /**
	  * 会诊抗菌药物申请单查询
	  * @Title: queryHzAntibioticsInfo
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param amqcKjywsysqReq
	  * @param @return    设定文件
	  * @return List<AmqcKjywsysqResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<AmqcKjywsysqResp>  queryHzAntibioticsInfo(AmqcKjywsysqReq amqcKjywsysqReq);

	 /**
	  * 查询抗菌药物申请单信息
	  * @Title: getSqdInfoBySqdh
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param sqdh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return AmqcKjywsysqypysResp    返回类型
	  * @author 龚方舟
	  * @throws
	  */
     AmqcKjywsysqypysResp getSqdInfoBySqdh(@Param("sqdh") Integer sqdh, @Param("jgid") Integer jgid);


     /**
      * 抗菌药物使用统计
      * @Title: antibioticsStatistics
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param req
      * @param @return    设定文件
      * @return List<AmqcKjywsysqStatisticsResp>    返回类型
      * @author 龚方舟
      * @throws
      */
     List<AmqcKjywsysqStatisticsResp> antibioticsStatistics(AmqcKjywsysqStatisticsReq req);

	/**
	 * 越级使用抗菌药物使用统计（门诊）
	 * @param req
	 * @return
	 */
	List<AmqcKjywsysqStatisticsResp> antibioticsStatisticsforMz(AmqcKjywsysqStatisticsReq req);

     /**
      * 按科室统计抗菌药物使用强度
      * @Title: antibioticsUseIntensityByKs
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param req
      * @param @return    设定文件
      * @return List<AmqcKjywsysqUseIntensityResp>    返回类型
      * @author 龚方舟
      * @throws
      */
     List<AmqcKjywsysqUseIntensityResp> antibioticsUseIntensityByKs(AmqcKjywsysqStatisticsReq req);

     /**
      * 按药物类别统计抗菌药物使用强度
      * @Title: antibioticsUseIntensityByLb
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param req
      * @param @return    设定文件
      * @return List<AmqcKjywsysqUseIntensityResp>    返回类型
      * @author 龚方舟
      * @throws
      */
     List<AmqcKjywsysqUseIntensityResp> antibioticsUseIntensityByLb(AmqcKjywsysqStatisticsReq req);


     /**
      * 住院抗菌药物使用率统计
      * @Title: antibioticsUtilizationRate
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param req
      * @param @return    设定文件
      * @return List<AmqcKjywsysqUseIntensityResp>    返回类型
      * @author 龚方舟
      * @throws
      */
     List<AmqcKjywsysqUtilizationRateResp> antibioticsUtilizationRate(AmqcKjywsysqStatisticsReq req);

	/**
	 * 门诊抗菌药物使用率统计
	 * @param req
	 * @return
	 */
	List<AmqcKjywsysqUtilizationRateResp> antibioticsUtilizationRateForMz(AmqcKjywsysqStatisticsReq req);

     /**
      * 住院抗菌药物使用排名
      * @Title: antibioticsUseRanking
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param req
      * @param @return    设定文件
      * @return List<AmqcKjywsysqUseRankingResp>    返回类型
      * @author 龚方舟
      * @throws
      */
     List<AmqcKjywsysqUseRankingResp> antibioticsUseRanking(AmqcKjywsysqStatisticsReq req);

	/**
	 * 门诊抗菌药物使用排名
	 * @param req
	 * @return
	 */
	List<AmqcKjywsysqUseRankingResp> antibioticsUseRankingForMz(AmqcKjywsysqStatisticsReq req);

     /**
      * 审核特殊级抗菌药物申请单
      * @Title: updateSpecialExamine
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param parameters    设定文件
      * @return void    返回类型
      * @author 龚方舟
      * @throws
      */
     void updateSpecialExamine(Map<String, Object> parameters);


     /**
      * 住院医生抗菌药物申请单审核查询
      * @Title: queryVerifyAntibioticsInfo
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param amqcKjywsysqReq
      * @param @return    设定文件
      * @return List<AmqcKjywsysqResp>    返回类型
      * @author 龚方舟
      * @throws
      */
     List<AmqcKjywsysqResp> queryVerifyAntibioticsInfo(AmqcKjywsysqReq amqcKjywsysqReq);


     /**
      * 住院医生特殊抗菌药物申请单审核查询
      * @Title: queryHzVerifyAntibioticsInfo
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param amqcKjywsysqReq
      * @param @return    设定文件
      * @return List<AmqcKjywsysqResp>    返回类型
      * @author 龚方舟
      * @throws
      */
     List<AmqcKjywsysqResp> queryHzVerifyAntibioticsInfo(AmqcKjywsysqReq amqcKjywsysqReq);

     /**
      * 特殊抗菌药物申请单打印
      * @Title: querySpecialAntibioticsPrint
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param sqdh
      * @param @param jgid
      * @param @return    设定文件
      * @return Map<String,Object>    返回类型
      * @author 龚方舟
      * @throws
      */
     Map<String, Object> querySpecialAntibioticsPrint(@Param("sqdh") Integer sqdh, @Param("jgid") Integer jgid);
     

     /**
      * 更新抗菌药物消息返回id
      * @Title: updateAmqcMessIdBySqdh
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param messid
      * @param @param sqdh    设定文件
      * @return void    返回类型
      * @author 龚方舟
      * @throws
      */
     void updateAmqcMessIdBySqdh(@Param("messid") String messid, @Param("sqdh") Integer sqdh);
}
