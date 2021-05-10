package com.zhn.demo.spring.web.exc;

import com.zhn.demo.spring.web.restresult.ApiResultCode;

public class BusinessException extends RuntimeException {

    private int code;

    public BusinessException(ApiResultCode apiResultCode) {
        super(apiResultCode.getDesc());
        this.code = apiResultCode.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
