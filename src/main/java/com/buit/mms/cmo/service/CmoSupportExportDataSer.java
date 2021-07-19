package com.buit.mms.cmo.service;

import com.buit.his.export.enums.ExportFileTypeEnum;
import com.buit.his.export.service.SupportExportDataService;
import com.buit.mms.cmo.enums.ExportDataTypeEnum;
import com.buit.mms.cmo.enums.JasperPathConditionEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/13 9:44
 */
@Service
public class CmoSupportExportDataSer implements SupportExportDataService {

    @Override
    public Map<String, String> allDataType() {
        return Arrays.stream(ExportDataTypeEnum.values())
                .collect(Collectors.toMap(ExportDataTypeEnum::name,ExportDataTypeEnum::getDesc));
    }

    @Override
    public List<ExportFileTypeEnum> supportFileType(String dataType) {
        return Arrays.asList(ExportDataTypeEnum.valueOf(dataType).getSupportType());
    }

    @Override
    public String getJasperPath(String dataType) {
        return ExportDataTypeEnum.valueOf(dataType).getJasperPath();
    }

    @Override
    public String getJasperCond(String dataType) {
        return ExportDataTypeEnum.valueOf(dataType).getJasperCond().name();
    }
}
