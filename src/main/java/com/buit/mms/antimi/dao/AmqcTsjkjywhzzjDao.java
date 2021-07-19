package com.buit.mms.antimi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.buit.mms.antimi.model.AmqcTsjkjywhzzj;
import com.buit.mms.antimi.response.AmqcTsjkjywhzzjResp;
import com.buit.commons.EntityDao;
/**
 * 特殊级抗菌药物会诊专家表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface AmqcTsjkjywhzzjDao extends EntityDao<AmqcTsjkjywhzzj,Integer>{
    
	
   	/**
   	 * 专家信息查询
   	 * @Title: queryInfo
   	 * @Description: TODO(这里用一句话描述这个方法的作用)
   	 * @param @return    设定文件
   	 * @return AmqcTsjkjywhzzjResp    返回类型
   	 * @author 龚方舟
   	 * @throws
   	 */
   	List<AmqcTsjkjywhzzjResp> queryInfo(Integer jgid);
}
