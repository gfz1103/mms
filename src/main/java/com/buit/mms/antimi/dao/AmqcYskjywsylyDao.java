package com.buit.mms.antimi.dao;

import com.buit.commons.EntityDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.mms.antimi.model.AmqcYskjywsyly;
import com.buit.mms.antimi.response.AmqcYskjywsylyResp;
/**
 * 医生抗菌药物使用理由表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface AmqcYskjywsylyDao extends EntityDao<AmqcYskjywsyly,Integer>{
    
	/**
	 * 医生使用理由分页查询信息
	 * @Title: queryInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    设定文件
	 * @return List<AmqcYskjywsylyResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    List<AmqcYskjywsylyResp> queryInfo(@Param("jgid") Integer jgid, @Param("lx") Integer lx,
    		 @Param("syly") String syly);
}
