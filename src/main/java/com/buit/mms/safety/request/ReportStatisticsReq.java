package com.buit.mms.safety.request;

import com.buit.mms.safety.enums.ReportStatisticsTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/29 13:43
 */
public class ReportStatisticsReq {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fsrqStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fsrqEnd;
    @NotNull
    private ReportStatisticsTypeEnum type;
    @ApiModelProperty(hidden = true)
    private Integer jgid;

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public LocalDate getFsrqStart() {
        return fsrqStart;
    }

    public void setFsrqStart(LocalDate fsrqStart) {
        this.fsrqStart = fsrqStart;
    }

    public LocalDate getFsrqEnd() {
        return fsrqEnd;
    }

    public void setFsrqEnd(LocalDate fsrqEnd) {
        this.fsrqEnd = fsrqEnd;
    }

    public ReportStatisticsTypeEnum getType() {
        return type;
    }

    public void setType(ReportStatisticsTypeEnum type) {
        this.type = type;
    }
}
