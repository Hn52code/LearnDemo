package com.zhn.demo.spring.web.result;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiResultUtil {

    public static String localDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(dtf);
    }

    /**
     * 用于捕捉http错误异常 结果返回
     *
     * @param code 状态吗
     * @param msg  信息描述
     * @param url  请求地址
     * @return 结果
     */
   public static ApiResult createHttpExceptionResult(int code, String msg, String url) {
        return new ApiResult(code, msg, url, localDateTime(), null);
    }

    /**
     * 用于捕捉business异常 结果返回
     *
     * @param code 状态吗
     * @param msg  信息描述
     * @param url  请求地址
     * @return 结果
     */
    public static ApiResult createBusinessExceptionResult(int code, String msg, String url) {
        return new ApiResult(code, msg, url, localDateTime(), null);
    }

    /**
     * 用于返回business 结果返回
     *
     * @return 结果
     */
    public static ApiResult createBusinessResult(Object data) {
        return new ApiResult(0, "请求成功", null, localDateTime(), data);
    }


}
