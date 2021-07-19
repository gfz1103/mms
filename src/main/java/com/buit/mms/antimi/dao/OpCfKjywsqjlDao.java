package com.buit.mms.antimi.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import com.buit.mms.antimi.model.OpCfKjywsqjl;
import org.apache.ibatis.annotations.Param;

/**
 * 门诊_处方_抗菌药物申请记录<br>
 * @author 朱智
 */
@Mapper
public interface OpCfKjywsqjlDao extends EntityDao<OpCfKjywsqjl,Integer>{

    /**
     * 修改状态
     * @param zt
     * @param sqdh
     */
    void updateZt(@Param("zt") Integer zt, @Param("sqdh") Integer sqdh);

    /**
     * 根据就诊序号查询申请单是否存在
     * @param ypxh
     * @param jzxh
     */
    int selectIsExit(@Param("ypxh") Integer ypxh, @Param("jzxh") Integer jzxh);

    /**
     * 通过药品序号和就诊序号查询抗菌药物申请单信息
     * @param ypxh
     * @param jzxh
     * @return
     */
    OpCfKjywsqjl queryCfkjwsqjl(@Param("ypxh") Integer ypxh, @Param("jzxh") Integer jzxh);
}
