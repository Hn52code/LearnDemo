package com.zhn.demo.spring.web.res;

public class ResultUtil {

    /**
     * 用于捕捉http错误异常 结果返回
     *
     * @param code 状态吗
     * @param msg  信息描述
     * @param url  请求地址
     * @return 结果
     */
    public static Result createHttpExcResult(int code, String msg, String url) {
        return new Result(code, msg, System.currentTimeMillis(), url, null);
    }

    /**
     * 用于捕捉business异常 结果返回
     *
     * @param code 状态吗
     * @param msg  信息描述
     * @param url  请求地址
     * @return 结果
     */
    public static Result createCustomExcResult(int code, String msg, String url) {
        return new Result(code, msg, System.currentTimeMillis(), url, null);
    }

    /**
     * 用于返回business 结果返回
     *
     * @return 结果
     */
    public static Result createSucResult(Object data) {
        return new Result(200, "请求成功", System.currentTimeMillis(), null, data);
    }


}
