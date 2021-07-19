package com.buit.mms.safety.dao;

import com.buit.mms.safety.model.AdvShpz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 10:41
 */
@Mapper
public interface AdvShpzDao {

    void batchInsert(@Param("list") List<AdvShpz> data);

    void delete(@Param("list") List<Integer> propIds);
}
