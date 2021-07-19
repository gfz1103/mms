package com.buit.mms.antimi.status;

/**
 * @Author weijing
 * @Date 2020-11-23 10:18
 * @Description
 **/
public class Status {
    public enum Gkfs {
        /*** 管控方式 1 填写使用理由/2 上级医生授权/3 填写使用申请*/
        USE_REASON("1","填写使用理由"),
        DOCTOR_GRANT("2","上级医生授权"),
        USE_APPLY("3","填写使用申请")
        ;
        /*** 字段key值*/
        private String code;

        /*** 字段文本值*/
        private String name;

        Gkfs(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    public enum KjywsqjlZt {
        /*** 状态 0：新单、1：已审核、2：会诊中、3：会诊完成*/
        NEW_ADD(0,"新单"),
        CHECKES(1,"已审核"),
        CHATTING(2,"会诊中"),
        CHAT_END(3,"会诊完成")
        ;
        /*** 字段key值*/
        private Integer code;

        /*** 字段文本值*/
        private String name;

        KjywsqjlZt(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
