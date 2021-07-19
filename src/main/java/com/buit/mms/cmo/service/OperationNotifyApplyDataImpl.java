package com.buit.mms.cmo.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import com.buit.commons.BaseException;
import com.buit.commons.SysUser;
import com.buit.constans.GbzdCodeCts;
import com.buit.file.FtpFileService;
import com.buit.his.export.dto.ExportRequestDto;
import com.buit.his.export.dto.JasperExportDataDto;
import com.buit.his.export.enums.ExportFileTypeEnum;
import com.buit.his.export.service.JasperExportDataService;
import com.buit.his.export.service.impl.FileExportContext;
import com.buit.mms.cmo.dao.OptSssqDao;
import com.buit.mms.cmo.dto.OperationNotifyApplyParamDto;
import com.buit.mms.cmo.enums.ExportDataTypeEnum;
import com.buit.mms.cmo.request.OperationApplyReq;
import com.buit.system.response.DicYljgOut;
import com.buit.system.response.DictDto;
import com.buit.system.service.DicGbsj02Service;
import com.buit.system.service.DicYljgOutSer;
import com.buit.system.service.SysFlagDataValueOutSer;
import com.buit.system.service.SysXtcsCacheSer;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/12/17 15:31
 */
@Service("operationNotifyApplyData")
public class OperationNotifyApplyDataImpl implements JasperExportDataService<OperationApplyReq, OperationNotifyApplyParamDto, Void> {

    @Autowired
    FileExportContext context;
    @Autowired
    OptSssqDao optSssqDao;
    @DubboReference
    DicGbsj02Service dicGbsj02Service;
    @DubboReference
    SysFlagDataValueOutSer sysFlagDataValueOutSer;
    @Autowired
    FtpFileService ftpFileService;
    @DubboReference
    DicYljgOutSer dicYljgOutSer;
    @Value("${resource.url}")
    String previewUrl;
    @Override
    public JasperExportDataDto<OperationNotifyApplyParamDto, Void> queryData(OperationApplyReq req, SysUser user) {
        OperationNotifyApplyParamDto param = optSssqDao.findNotifyApplyDetail(req.getSqdh());
        LocalDateTime csny = LocalDate.parse(param.getCsny()).atTime(LocalTime.MIN);
        long year = ChronoUnit.YEARS.between(csny, LocalDateTime.now());
        param.setBrnl(year+"岁");
        DictDto brxb = dicGbsj02Service.findGbzdByMainCodeAndValue(user.getHospitalId(), GbzdCodeCts.brxb,param.getBrxb());
        param.setBrxb(brxb.getName());
        DictDto ssjb = sysFlagDataValueOutSer.findFlagValueByDataIdAndValueCode(GbzdCodeCts.ssjb,param.getSsdj());
        param.setSsdj(ssjb.getName());
        DictDto qkdj = dicGbsj02Service.findGbzdByMainCodeAndValue(user.getHospitalId(),GbzdCodeCts.qkdj,param.getQkdj());
        param.setQkdj(qkdj.getName());
        DicYljgOut yljgOut = dicYljgOutSer.getById(user.getHospitalId());
        param.setLogoUrl(previewUrl+yljgOut.getLogo());
        param.setYzsj(DateUtil.formatDateTime(param.getSqrq()));
        param.setSsxx(String.format("拟于%s在%s下行%s(%s)", DateUtil.format(param.getSqrq(),"MM月dd日HH点mm分"),param.getMzmc(),param.getSsmc(),param.getSsfl()));
        return createResp(param,null,true);
    }

    @Override
    public String getFileName(OperationApplyReq req, SysUser user) {
        return ExportDataTypeEnum.operationNotifyApplyData.getDesc();
    }
}
