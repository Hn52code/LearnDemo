package com.zhn.demo.spring.web.res;

// App之API 返回结果统一bean
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
