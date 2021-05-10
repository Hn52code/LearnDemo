package com.zhn.demo.rabbitmq.channel.server.inout.model;

import java.util.Map;

public class RequestModel {

    /* 请求方法：post put delete get */
    private String method;
    /* 请求的uri */
    private String uri;
    /* 请求参数 */
    private Map<String, Object> paras;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, Object> getParas() {
        return paras;
    }

    public void setParas(Map<String, Object> paras) {
        this.paras = paras;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "method='" + method + '\'' +
                ", uri='" + uri + '\'' +
                ", paras=" + paras +
                '}';
    }
}
