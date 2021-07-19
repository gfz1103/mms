package com.buit.mms.safety.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.buit.commons.BaseException;
import com.buit.commons.SysUser;
import com.buit.constans.GbzdCodeCts;
import com.buit.mms.safety.dao.AdvBgdjDao;
import com.buit.mms.safety.dao.AdvPropDao;
import com.buit.mms.safety.request.ReportStatisticsReq;
import com.buit.mms.safety.response.EventApprovalDepartmentResp;
import com.buit.mms.safety.response.ReportStatisticsResp;
import com.buit.system.response.DicKszdModel;
import com.buit.system.response.DictDto;
import com.buit.system.service.DicGbsj02Service;
import com.buit.system.service.DicKszdOutSer;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/29 13:41
 */
@Service
public class ReportStatisticsSer {

    static final Logger log = LoggerFactory.getLogger(ReportStatisticsSer.class);

    @Autowired
    AdvBgdjDao advBgdjDao;
    @DubboReference
    DicGbsj02Service dicGbsj02Service;
    @Autowired
    AdvPropDao advPropDao;
    @DubboReference
    DicKszdOutSer dicKszdOutSer;

    public List<ReportStatisticsResp> statistics(ReportStatisticsReq req, SysUser user) {
        log.info("事件报告表统计[{},{}]", JSONUtil.toJsonStr(req),JSONUtil.toJsonStr(user));
        req.setJgid(user.getHospitalId());
        List<ReportStatisticsResp> resp = advBgdjDao.countStatistics(req);
        Map<String,String> dictMap=null;
        switch (req.getType()){
            case event:
                List<EventApprovalDepartmentResp> propList = advPropDao.queryApprovalConfig(user.getHospitalId());
                dictMap = propList.stream().collect(Collectors.toMap(e->e.getId().toString(),EventApprovalDepartmentResp::getSjmc));
                break;
            case category:
                List<DictDto> sjlbList = dicGbsj02Service.queryGbzdByMainCode(req.getJgid(), GbzdCodeCts.blsjlb);
                dictMap = sjlbList.stream().collect(Collectors.toMap(DictDto::getCode,DictDto::getName));
                break;
            case department:
                List<DicKszdModel> kszdList = dicKszdOutSer.findByEntity(MapUtil.of("ORGANIZCODE",req.getJgid()));
                dictMap = kszdList.stream().collect(Collectors.toMap(d->d.getId().toString(), DicKszdModel::getOfficename));
                break;
        }
        if(dictMap==null){
            throw BaseException.create("ERROR_ADV_0007",new String[]{req.getType().name()});
        }
        Map<String, String> finalDictMap = dictMap;
        resp.forEach(r->r.setName(finalDictMap.get(r.getName())));
        return resp;
    }
}
