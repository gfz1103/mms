package com.buit.mms.cmo.service;

import cn.hutool.core.date.DateUtil;
import com.buit.commons.SysUser;
import com.buit.constans.GbzdCodeCts;
import com.buit.file.FtpFileService;
import com.buit.his.export.dto.JasperExportDataDto;
import com.buit.his.export.service.JasperExportDataService;
import com.buit.his.export.service.impl.FileExportContext;
import com.buit.mms.cmo.dao.OptSssqDao;
import com.buit.mms.cmo.dto.ClinicOperationNotifyApplyParamDto;
import com.buit.mms.cmo.enums.ExportDataTypeEnum;
import com.buit.mms.cmo.request.OperationApplyReq;
import com.buit.system.response.DicYljgOut;
import com.buit.system.response.DictDto;
import com.buit.system.service.DicGbsj02Service;
import com.buit.system.service.DicYljgOutSer;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/12/17 15:31
 */
@Service("clinicOperationNotifyApplyData")
public class ClinicOperationNotifyApplyDataImpl implements JasperExportDataService<OperationApplyReq, ClinicOperationNotifyApplyParamDto, Void> {

    @Autowired
    FileExportContext context;
    @Autowired
    OptSssqDao optSssqDao;
    @DubboReference
    DicGbsj02Service dicGbsj02Service;
    @Autowired
    FtpFileService ftpFileService;
    @DubboReference
    DicYljgOutSer dicYljgOutSer;
    @Value("${resource.url}")
    String resourceUrl;
    @Override
    public JasperExportDataDto<ClinicOperationNotifyApplyParamDto, Void> queryData(OperationApplyReq req, SysUser user) {
        ClinicOperationNotifyApplyParamDto param = optSssqDao.findClinicOperationData(req.getSqdh());
        LocalDateTime csny = LocalDate.parse(param.getCsny()).atTime(LocalTime.MIN);
        long year = ChronoUnit.YEARS.between(csny, LocalDateTime.now());
        param.setBrnl(year+"岁");
        DictDto brxb = dicGbsj02Service.findGbzdByMainCodeAndValue(user.getHospitalId(), GbzdCodeCts.brxb,param.getBrxb());
        param.setBrxb(brxb.getName());
        param.setSssj(DateUtil.formatDateTime(param.getSsrq()));
        DicYljgOut yljgOut = dicYljgOutSer.getById(user.getHospitalId());
        param.setLogoUrl(resourceUrl+yljgOut.getLogo());
        return createResp(param,null,true);
    }

    @Override
    public String getFileName(OperationApplyReq req, SysUser user) {
        return ExportDataTypeEnum.operationNotifyApplyData.getDesc();
    }
}
