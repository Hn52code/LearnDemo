package com.zhn.demo.webexample.common;

import com.zhn.demo.webexample.util.TimeUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class WebResult {

    private int errNo = -1;
    private String errMsg;
    private String createTime;
    private Object data;

    public WebResult(Object data) {
        this.errNo = 1;
        this.createTime = TimeUtil.nowTime();
        this.errMsg = "请求成功";
        this.data = data;
    }

    public WebResult(HttpStatus status, String detailMsg) {
        this.errNo = status.value();
        this.createTime = TimeUtil.nowTime();
        this.errMsg = detailMsg != null ? detailMsg : status.getReasonPhrase();
    }

    public WebResult(int errNo, String errMsg) {
        this.errNo = errNo;
        this.createTime = TimeUtil.nowTime();
        this.errMsg = errMsg;
    }

    public WebResult(int errNo, String errMsg, Object data) {
        this.errNo = errNo;
        this.createTime = TimeUtil.nowTime();
        this.errMsg = errMsg;
    }

}
