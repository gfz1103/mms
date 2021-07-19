package com.buit.mms.antimi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.mms.antimi.model.AmqcKjywsysqyp;
import com.buit.mms.antimi.response.AmqcKjywsysqypResp;
import com.buit.commons.EntityDao;
/**
 * 抗菌药物使用申请药品表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface AmqcKjywsysqypDao extends EntityDao<AmqcKjywsysqyp,Integer>{
    
	  /**
	   * 查询抗菌药物信息
	   * @Title: queryKjywYpInfo
	   * @Description: TODO(这里用一句话描述这个方法的作用)
	   * @param @param jgid
	   * @param @param sqdh
	   * @param @return    设定文件
	   * @return List<AmqcKjywsysqypResp>    返回类型
	   * @author 龚方舟
	   * @throws
	   */
	  List<AmqcKjywsysqypResp> queryKjywYpInfo(@Param("jgid") Integer jgid, @Param("sqdh") Integer sqdh);


	  /**
	   * 抗菌药物天数校验
	   * @Title: checkDaysKjyw
	   * @Description: TODO(这里用一句话描述这个方法的作用)
	   * @param @param parameters
	   * @param @return    设定文件
	   * @return long    返回类型
	   * @author 龚方舟
	   * @throws
	   */
	  long checkDaysKjyw(Map<String, Object> parameters);
}
