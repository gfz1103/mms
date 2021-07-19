package com.buit.mms.cmo.dao;

import com.buit.mms.cmo.model.CmoZdss;
import com.buit.mms.cmo.response.CmoZdssResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/10 15:46
 */
@Mapper
public interface CmoZdssDao {


    void remove(@Param("list")List<Integer> ssbm);

    void insertSelectByDicJbbm(@Param("list")List<Integer> ssbmList);

    List<CmoZdssResp> queryList(@Param("pydm") String pydm);

    List<CmoZdssResp> queryUncheckedList(@Param("pydm") String pydm);

    CmoZdss getById(@Param("ssbm") Integer ssbm);

    CmoZdss getByfyxhId(@Param("fyxh") Integer fyxh);
}
