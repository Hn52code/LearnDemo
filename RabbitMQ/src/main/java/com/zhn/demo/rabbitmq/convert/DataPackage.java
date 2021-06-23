package com.zhn.demo.rabbitmq.convert;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


public class DataPackage {

    private String devId;
    private List<Object> list;
    private Map<String,Object> content;


    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DataPackage.class.getSimpleName() + "[", "]")
                .add("devId='" + devId + "'")
                .add("list=" + list)
                .add("content=" + content)
                .toString();
    }

}
