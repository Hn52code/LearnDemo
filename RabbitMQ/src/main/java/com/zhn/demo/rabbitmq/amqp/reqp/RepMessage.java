package com.zhn.demo.rabbitmq.amqp.reqp;

public class RepMessage {

    private String id;
    private String code;
    private Object result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RepMessage{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", result=" + result +
                '}';
    }
}
