package com.zhn.demo.rabbitmq.channel.server.inout.model;

public class ResponseContent {

    private int errno;
    private String error;
    private Object data;

    public ResponseContent() {
    }

    public ResponseContent(int errno, String error, Object data) {
        this.errno = errno;
        this.error = error;
        this.data = data;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseContent{" +
                "errno=" + errno +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }
}
