package com.zhn.demo.spring.web.res;

import com.fasterxml.jackson.annotation.JsonInclude;

// App之API 返回结果统一bean
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Result {

    //错误码
    private int code;
    //信息提示
    private String msg;
    //时间
    private long createTime;
    //url
    private String url;
    //数据
    private Object data;
    // 其它
    private int sum;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Result() {
    }

    public Result(int code, String msg, long createTime, String url, Object data) {
        this.code = code;
        this.msg = msg;
        this.createTime = createTime;
        this.url = url;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", createTime='" + createTime + '\'' +
                ", url='" + url + '\'' +
                ", data=" + data +
                '}';
    }

}
