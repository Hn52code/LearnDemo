package com.zhn.demo.rabbitmq.channel.server.inout.model;

/* rpc 响应消息模型 */
public class ResponseModel {

    /* 请求方法：post put get delete */
    private String method;
    /* 访问的uri */
    private String uri;
    /* 响应结果 */
    private ResponseContent resp;

    public ResponseModel(String method, String uri, ResponseContent resp) {
        this.method = method;
        this.uri = uri;
        this.resp = resp;
    }

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

    public ResponseContent getResp() {
        return resp;
    }

    public void setResp(ResponseContent resp) {
        this.resp = resp;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "method='" + method + '\'' +
                ", uri='" + uri + '\'' +
                ", resp=" + resp +
                '}';
    }
}
