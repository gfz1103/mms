package com.buit.mms.cmo.service;

import com.buit.commons.SysUser;
import com.buit.his.export.dto.ExportContextDto;
import com.buit.his.export.service.JasperPathConditionService;
import com.buit.mms.cmo.dao.OptSssqDao;
import com.buit.mms.cmo.enums.ExportDataTypeEnum;
import com.buit.mms.cmo.model.OptSssq;
import com.buit.mms.cmo.request.OperationApplyReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/12/16 13:47
 */
@Service("operationApplyPathCond")
public class OperationApplyPathConditionImpl  implements JasperPathConditionService<OperationApplyReq> {

    @Autowired
    OptSssqDao optSssqDao;

    @Override
    public ExportContextDto exportContext(String dataType, String jasperPath, OperationApplyReq param, SysUser user) {
        ExportContextDto contextDto = new ExportContextDto();
        OptSssq sssq = optSssqDao.getById(param.getSqdh());
        contextDto.setDataService(ExportDataTypeEnum.operationApplyData.name());
        if(sssq.getTsss()!=null&&sssq.getTsss()!=0){
            contextDto.setJasperPath("jrxml/SpecialApplicationOne.jrxml");
        }else if(sssq.getZdssbz()==1){
            contextDto.setJasperPath("jrxml/MajorOperation.jrxml");
        }
        return contextDto;
    }
}
