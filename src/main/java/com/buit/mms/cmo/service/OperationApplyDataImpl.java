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
import com.buit.mms.cmo.dto.OperationApplyParamDto;
import com.buit.mms.cmo.request.OperationApplyReq;
import com.buit.mms.cmo.response.OperationApplyResp;
import com.buit.system.response.DicYljgOut;
import com.buit.system.response.DictDto;
import com.buit.system.response.HrPersonnelModel;
import com.buit.system.service.*;
import com.buit.utill.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/13 9:34
 */
@Service("operationApplyData")
public class OperationApplyDataImpl implements JasperExportDataService<OperationApplyReq, OperationApplyParamDto, Void> {

    @Autowired
    OptSssqDao optSssqDao;
    @DubboReference
    DicKszdOutSer dicKszdOutSer;
    @DubboReference
    DicGbsj02Service dicGbsj02Service;
    @DubboReference
    SysFlagDataValueOutSer sysFlagDataValueOutSer;
    @DubboReference
    HrPersonnelService hrPersonnelService;
    @Autowired
    FileExportContext context;
    @Autowired
    FtpFileService ftpFileService;
    @DubboReference
    DicYljgOutSer dicYljgOutSer;
    @Value("${resource.url}")
    String previewUrl;
    @Override
    public JasperExportDataDto<OperationApplyParamDto, Void> queryData(OperationApplyReq req, SysUser user) {
        OperationApplyResp opDetail = optSssqDao.getDetailBySqdh(req.getSqdh());
        OperationApplyParamDto param = BeanUtil.toBean(opDetail,OperationApplyParamDto.class);
        param.setBrxm(opDetail.getBrxm());
        if(opDetail.getBq()!=null&&opDetail.getBq()!=0) {
            param.setBrbq(dicKszdOutSer.getNameById(opDetail.getBq()));
        }
        param.setBrch(opDetail.getCh());
        if(opDetail.getKs()!=null&&opDetail.getKs()!=0){
            param.setBrks(dicKszdOutSer.getNameById(opDetail.getKs()));
        }

        Long year = ChronoUnit.YEARS.between(opDetail.getCsny().toLocalDateTime(), LocalDateTime.now());
        param.setBrnl(year.intValue());
        DictDto xbDict = dicGbsj02Service.findGbzdByMainCodeAndValue(user.getHospitalId(), GbzdCodeCts.brxb,opDetail.getBrxb().toString());
        if(xbDict!=null){
            param.setBrxb(xbDict.getName());
        }
        param.setCsny(opDetail.getCsny());
        param.setHzzq("");
        DictDto mzDict = dicGbsj02Service.findGbzdByMainCodeAndValue(user.getHospitalId(), GbzdCodeCts.mzfs,opDetail.getBrxb().toString());
        if(mzDict!=null){
            param.setMzmc(mzDict.getName());
        }else{
            param.setMzmc("未知");
        }
        List<DictDto> ssjbDict = sysFlagDataValueOutSer.queryFlagValueByDataId(GbzdCodeCts.ssjb);
        String ssjb = ssjbDict.stream().map(s->(s.getCode().equals(opDetail.getSsjb().toString())?"☑":"▢")+s.getName()).reduce((s,v)->s.concat(v)).get();
        param.setSsjb(ssjb);
        param.setSpsj(opDetail.getSpsj());
        param.setSpyj(opDetail.getSpyj());
        if(opDetail.getSpys()!=null) {
            HrPersonnelModel hrPersonnelModel = hrPersonnelService.getPersonnelById(opDetail.getSpys());
            param.setSpys(hrPersonnelModel.getPersonname());
        }
        param.setSqjg(opDetail.getSqjg());
        param.setSqks(dicKszdOutSer.getNameById(opDetail.getSqks()));
        param.setSqrq(opDetail.getSqrq());
        param.setSsmc(opDetail.getSsmc());
        param.setSsrq(opDetail.getSsrq());
        HrPersonnelModel ssys = hrPersonnelService.getPersonnelById(opDetail.getSsys());
        param.setSzjzc(ssys.getPersonname()+(StringUtils.isNoneBlank(opDetail.getSzzc())?String.format("(%s)",opDetail.getSzzc()):""));
        List<HrPersonnelModel> sszsList = hrPersonnelService.queryById(Arrays.asList(opDetail.getSsyz(),opDetail.getSsez(),opDetail.getSssz(),opDetail.getSszs()));
        Optional<String> zsjzc = sszsList.stream().map(s->s.getPersonname()+(StringUtils.isNotBlank(s.getZc())?String.format("(%s)",s.getZc()):"")).reduce((s, v)->s.concat(",").concat(v));
        param.setZsjzc(zsjzc.orElse(""));
        param.setZyh(opDetail.getZyh());
        List<DictDto> tsssDict = sysFlagDataValueOutSer.queryFlagValueByDataId(GbzdCodeCts.tsss);
        String tsss = tsssDict.stream().map(s->{
            if(opDetail.getTsss()!=null&&s.getCode().equals(opDetail.getTsss().toString())){
                return "☑" + s.getName();
            }else {
                return "▢" + s.getName();
            }
        }).reduce((s,v)->s.concat("\r\n").concat(v)).get();
        param.setTsss(tsss);
        DicYljgOut yljgOut = dicYljgOutSer.getById(user.getHospitalId());
        param.setLogoUrl(previewUrl+yljgOut.getLogo());
        param.setCsnyFormat(DateUtil.format(param.getCsny(),"yyyy年MM月dd日"));
        param.setSpsjFormat(DateUtil.format(param.getSpsj(),"yyyy年MM月dd日"));
        param.setSqrqFormat(DateUtil.format(param.getSqrq(),"yyyy年MM月dd日"));
        param.setSsrqFormat(DateUtil.format(param.getSsrq(),"yyyy年MM月dd日HH时mm分"));
        return createResp(param,null,true);
    }

    @Override
    public String getFileName(OperationApplyReq req, SysUser user) {
        return "手术申请单";
    }
}
