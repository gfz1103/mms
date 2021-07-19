package com.buit.mms.cmo.dao;

import com.buit.mms.cmo.dto.ClinicOperationNotifyApplyParamDto;
import com.buit.mms.cmo.dto.OperationNotifyApplyParamDto;
import com.buit.mms.cmo.model.OptSssq;
import com.buit.mms.cmo.model.SsJyjg;
import com.buit.mms.cmo.request.ExamineOperationApplyReq;
import com.buit.mms.cmo.request.QueryExamineListReq;
import com.buit.mms.cmo.request.QueryOperationApplyReq;
import com.buit.mms.cmo.response.IOptSssqResp;
import com.buit.mms.cmo.response.OperationApplyResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/6 10:26
 */
@Mapper
public interface OptSssqDao {

    void save(OptSssq sssq);

    OptSssq getById(@Param("sqdh") Integer sqdh);

    void updateZfbzBySqdh(@Param("sqdh") Integer sqdh);

    void commit(ExamineOperationApplyReq req);

    OperationApplyResp getDetailBySqdh(@Param("sqdh")Integer sqdh);

    List<OperationApplyResp> queryExamineList(QueryExamineListReq req);

    void updateTzztBySqdh(@Param("sqdh") Integer sqdh, @Param("tzgh") Integer tzgh);

    void delete(@Param("sqdh") Integer sqdh);

    List<OperationApplyResp> queryList(QueryOperationApplyReq req);

    String queryYszc(@Param("ysgh") Integer ysgh);

    void saveJyjg(SsJyjg ssjyjg);

    OperationNotifyApplyParamDto findNotifyApplyDetail(@Param("sqdh") Integer sqdh);

    List<IOptSssqResp> queryByZyh(Integer zyh);

    ClinicOperationNotifyApplyParamDto findClinicOperationData(@Param("sqdh") Integer sqdh);
}
