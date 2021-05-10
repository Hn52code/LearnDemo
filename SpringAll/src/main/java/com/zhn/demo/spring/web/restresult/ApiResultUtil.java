package com.zhn.demo.spring.web.restresult;

import com.zhn.demo.spring.web.utils.TimeUtil;

public class ApiResultUtil {

    /**
     * 用于捕捉http错误异常 结果返回
     *
     * @param code 状态吗
     * @param msg  信息描述
     * @param url  请求地址
     * @return 结果
     */
   public static ApiResponseResult createHttpExceptionResult(int code, String msg, String url) {
        return new ApiResponseResult(code, msg, url, TimeUtil.localDateTime(), null);
    }

    /**
     * 用于捕捉business异常 结果返回
     *
     * @param code 状态吗
     * @param msg  信息描述
     * @param url  请求地址
     * @return 结果
     */
    public static ApiResponseResult createBusinessExceptionResult(int code, String msg, String url) {
        return new ApiResponseResult(code, msg, url, TimeUtil.localDateTime(), null);
    }

    /**
     * 用于返回business 结果返回
     *
     * @return 结果
     */
    public static ApiResponseResult createBusinessResult(Object data) {
        return new ApiResponseResult(0, "请求成功", null, TimeUtil.localDateTime(), data);
    }


}
