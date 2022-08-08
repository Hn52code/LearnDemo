package com.zhn.demo.spring.web.result;

// App之API 返回结果统一bean
public class ApiResult {

    //错误码
    private int code;
    //信息提示
    private String msg;
    //时间
    private String createTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ApiResult() {
    }

    public ApiResult(int code, String msg, String url, String createTime, Object data) {
        this.code = code;
        this.msg = msg;
        this.url = url;
        this.createTime = createTime;
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
