package com.buit.mms.cmo.service;

import com.buit.commons.BaseException;
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
 * @Date 2021/2/2 16:56
 */
@Service("operationNotifyPathCond")
public class OperationNotifyConditionImpl  implements JasperPathConditionService<OperationApplyReq> {

    @Autowired
    OptSssqDao optSssqDao;

    @Override
    public ExportContextDto exportContext(String dataType, String jasperPath, OperationApplyReq param, SysUser user) {
        ExportContextDto contextDto = new ExportContextDto();
        OptSssq sssq = optSssqDao.getById(param.getSqdh());
        if(sssq.getSqlx().equals(1)){//门诊手术
            contextDto.setJasperPath("jrxml/ClinicOperationNotice.jrxml");
            contextDto.setDataService("clinicOperationNotifyApplyData");
        }else if(sssq.getSqlx().equals(2)){//住院手术
            contextDto.setJasperPath("jrxml/OperationNotice.jrxml");
            contextDto.setDataService("operationNotifyApplyData");
        }else{
            throw BaseException.create("ERROR_SSSQ_0001");
        }
        return contextDto;
    }
}
