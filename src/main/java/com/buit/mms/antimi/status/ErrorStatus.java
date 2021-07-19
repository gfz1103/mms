package com.buit.mms.antimi.status;

/**
 * @Author weijing
 * @Date 2020-11-23 13:29
 * @Description
 **/
public enum ErrorStatus {
    ERROR_MZCFKJYWSQ_0001("ERROR_MZCFKJYWSQ_0001","未查询到当前用户"),
    ERROR_MZCFKJYWSQ_0002("ERROR_MZCFKJYWSQ_0002","用户密码输入错误"),
    ERROR_MZCFKJYWSQ_0003("ERROR_MZCFKJYWSQ_0003","上级用户名不能为空"),
    ERROR_MZCFKJYWSQ_0004("ERROR_MZCFKJYWSQ_0004","上级密码不能为空"),
    ERROR_MZCFKJYWSQ_0005("ERROR_MZCFKJYWSQ_0005","使用理由不能为空"),
    ERROR_MZCFKJYWSQ_0006("ERROR_MZCFKJYWSQ_0006","药品信息不能为空"),
    ERROR_MZCFKJYWSQ_0007("ERROR_MZCFKJYWSQ_0007","该药品的申请单已经生成,请勿重复发起申请"),
    ;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String message;


    ErrorStatus(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

}
