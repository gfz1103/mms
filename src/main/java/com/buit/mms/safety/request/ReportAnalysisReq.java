package com.buit.mms.safety.request;

import com.buit.commons.PageQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/29 10:14
 */
public class ReportAnalysisReq extends PageQuery {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fsrqStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fsrqEnd;
    private List<Integer> sjlb;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public List<Integer> getSjlb() {
        return sjlb;
    }

    public void setSjlb(List<Integer> sjlb) {
        this.sjlb = sjlb;
    }
}
