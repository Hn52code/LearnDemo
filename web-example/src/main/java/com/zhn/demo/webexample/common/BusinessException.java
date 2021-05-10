package com.zhn.demo.webexample.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessException extends RuntimeException {
    private int errNo;

    public BusinessException(int errNo, String message) {
        super(message);
        this.errNo = errNo;
    }

}
