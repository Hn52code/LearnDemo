package com.zhn.demo.spring.web.res;

public class CustomException extends RuntimeException {

    private int code;

    public CustomException(Code code) {
        super(code.getDesc());
        this.code = code.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
