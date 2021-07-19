package com.buit.mms.cmo.dao;

import com.buit.mms.cmo.enums.RelevantTypeEnum;
import com.buit.mms.cmo.model.CmoSsxg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/5 16:49
 */
@Mapper
public interface CmoSsxgDao {

    List<CmoSsxg> queryAll(@Param("relevant") RelevantTypeEnum relevant);

    void replace(CmoSsxg tsss);

    void deleteById(@Param("id") Integer id);

    void updateStatusById(@Param("id") Integer id);
}
