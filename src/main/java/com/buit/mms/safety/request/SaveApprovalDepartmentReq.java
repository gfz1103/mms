package com.buit.mms.safety.request;

import com.buit.mms.safety.dto.ShpzDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/10/28 11:02
 */
public class SaveApprovalDepartmentReq {

    @NotEmpty
    @Valid
    private List<ShpzDto> shpz;


    public List<ShpzDto> getShpz() {
        return shpz;
    }

    public void setShpz(List<ShpzDto> shpz) {
        this.shpz = shpz;
    }
}
