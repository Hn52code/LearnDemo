package com.zhn.demo.rabbitmq.amqp.rpc;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Response {

    private String reqId;
    private int code;
    private String result;
    private LocalDateTime reqTime;
    private LocalDateTime dateTime = LocalDateTime.now();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getReqTime() {
        return reqTime;
    }

    public void setReqTime(LocalDateTime reqTime) {
        this.reqTime = reqTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Response.class.getSimpleName() + "[", "]")
                .add("reqId='" + reqId + "'")
                .add("code=" + code)
                .add("result='" + result + "'")
                .add("reqTime=" + reqTime)
                .add("dateTime=" + dateTime)
                .toString();
    }
}
