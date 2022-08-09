package com.zhn.demo.spring.web.res;

public enum Code {

    USER_NOT_EXIST(1001,"用户不存在"),
    USER_PWD_ERROR(1002,"用户或密码错误"),
    ;
    private int code;
    private String desc;

    Code(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
