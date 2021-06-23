package com.zhn.demo.rabbitmq.amqp.rpc;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Request {

    private String reqId;
    private String content;
    private LocalDateTime dateTime = LocalDateTime.now();

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getReqId() {
        return reqId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Request.class.getSimpleName() + "[", "]")
                .add("reqId='" + reqId + "'")
                .add("content='" + content + "'")
                .add("dateTime=" + dateTime)
                .toString();
    }
}
