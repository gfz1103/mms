package com.buit.mms.crivalu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.commons.EntityDao;
import com.buit.mms.crivalu.model.CisWjzjl;
import com.buit.mms.crivalu.request.CisWjzjlQualityReportReq;
import com.buit.mms.crivalu.response.CisWjzjlQualityReportResp;
import com.buit.mms.crivalu.response.CisWjzjlResp;
/**
 * 危急值记录表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface CisWjzjlDao extends EntityDao<CisWjzjl,Integer>{

    /**
     * 危急值管理质量报表
     * @Title: queryWjzQualityReport
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param req
     * @param @return    设定文件
     * @return List<CisWjzjlQualityReportResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<CisWjzjlQualityReportResp> queryWjzQualityReport(CisWjzjlQualityReportReq req);
    
    /**
     * 查询危急值记录
     * @Title: queryCriticalValueRecord
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param req
     * @param @return    设定文件
     * @return List<CisWjzjlResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<CisWjzjlResp> queryCriticalValueRecord(CisWjzjlQualityReportReq req);
    

    /**
     * 危急值更新接收信息
     * @Title: updateReceiveInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jsrdm
     * @param @param wjzdh    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateReceiveInfo(@Param("jsrdm") Integer jsrdm, @Param("wjzdh") Integer wjzdh);
    
    /**
     * 危急值更新通知信息
     * @Title: updateNoticeInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlxh
     * @param @param yzlx    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateNoticeInfo(@Param("tzysdm") Integer tzysdm, @Param("wjzdh") Integer wjzdh);
    

    /**
     * 危急值更新通知和处置信息
     * @Title: updateNoticeDealInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param czrdm
     * @param @param czcs
     * @param @param wjzdh    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateNoticeDealInfo(@Param("czrdm") Integer czrdm, @Param("czcs") String czcs, 
    		@Param("wjzdh") Integer wjzdh);

    /**
     * 危急值更新处置信息
     * @Title: updateDealInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param czrdm
     * @param @param czcs
     * @param @param wjzdh    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateDealInfo(@Param("czrdm") Integer czrdm, @Param("czcs") String czcs, 
    		@Param("wjzdh") Integer wjzdh);

    
    /**
     * 定时查询病人危机值信息
     * @Title: queryCisWjzHzInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryCisWjzHzInfo(Integer jgid);
    
    /**
     * 危急值更新返回护士站消息id
     * @Title: updateWjzMessIdByWjzdh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param messid
     * @param @param wjzdh    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateWjzMessIdByWjzdh(@Param("messid") String messid, @Param("wjzdh") Integer wjzdh);
    
    /**
     * 危急值更新返回医生站消息id
     * @Title: updateWjzMessIdByWjzdh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param ysmessid
     * @param @param wjzdh    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateWjzYsMessIdByWjzdh(@Param("ysmessid") String ysmessid, @Param("wjzdh") Integer wjzdh);
}
