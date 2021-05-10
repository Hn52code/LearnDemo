package com.zhn.pro.demo.wx.common.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本 HTTP request 工具是基于 Apache httpclient 4.5.x的封装的。
 * 其中包含常用HTTP请求方法的（POST,PUT,GET,DELETE）通用封装，打印日志。
 * 内部流程的大致步骤如下：
 * 1. 创建请求方法
 * 2. 封装请求参数（uri）
 * 3. 设置请求头（headers）
 * 4. 设置请求体（entity，主要用于POST,PUT请求）
 * 5. 经请求客户端，执行 请求方法
 * 6. 去读与判断结果，并封装返回。
 */
public class HttpUtil {

    /* 日志 */
    private static Logger logger = LogManager.getLogger(HttpUtil.class);

    /* 默认的请求连接配置 */
    private static RequestConfig config = RequestConfig
            .custom()
            .setConnectTimeout(20000)
            .setConnectTimeout(20000)
            .build();

    /* 请求管理对象 */
    private static CloseableHttpClient client = HttpClientBuilder.create()
            .setDefaultRequestConfig(config)
            .setMaxConnTotal(200)
            .build();

    /* 测试 */
    public static void main(String[] args) {
        String url = "https://www.baidu.com";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhouhainan");
        map.put("age", "zhouhainan");
        String s = httpGet(url, null, map);
        System.out.println(s);
    }

    /**
     * POST请求 表单参数
     *
     * @param url       请求地址
     * @param headers   请求头
     * @param formParas 表单参数
     * @return 字符
     */
    public static String httpPostByForm(String url, Map<String, Object> headers, Map<String, Object> formParas) {
        HttpPost httpPost = new HttpPost(url);
        wrapHeader(httpPost, headers);
        wrapEntity(httpPost, formParas);
        return invoke(httpPost);
    }

    /**
     * POST请求 实体参数
     *
     * @param url        请求地址
     * @param headers    头部参数
     * @param entityPara 实体参数
     * @return 字符
     */
    public static String httpPostByJson(String url, Map<String, Object> headers, String entityPara) {
        HttpPost httpPost = new HttpPost(url);
        wrapHeader(httpPost, headers);
        httpPost.setEntity(new StringEntity(entityPara, StandardCharsets.UTF_8));
        return invoke(httpPost);
    }

    /**
     * DELETE请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param paras   参数
     * @return 字符
     */
    public static String httpDelete(String url, Map<String, Object> headers, Map<String, Object> paras) {
        URI uri = wrapUri(url, paras);
        HttpDelete httpDelete = new HttpDelete(uri);
        wrapHeader(httpDelete, headers);
        return invoke(httpDelete);
    }

    /**
     * PUT请求
     *
     * @param url        请求地址
     * @param headers    请求头
     * @param entityPara 参数
     * @return 字符
     */
    public static String httpPutByJson(String url, Map<String, Object> headers, String entityPara) {
        HttpPut httpPut = new HttpPut(url);
        wrapHeader(httpPut, headers);
        httpPut.setEntity(new StringEntity(entityPara, StandardCharsets.UTF_8));
        return invoke(httpPut);
    }

    /**
     * GET请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param paras   参数
     * @return 字符
     */
    public static String httpGet(String url, Map<String, Object> headers, Map<String, Object> paras) {
        URI uri = wrapUri(url, paras);
        HttpGet httpGet = new HttpGet(uri);
        wrapHeader(httpGet, headers);
        return invoke(httpGet);
    }

    /**
     * 封装uri 用于GET,DELETE
     *
     * @param url   请求地址
     * @param paras 参数
     * @return URI
     */
    private static URI wrapUri(String url, Map<String, Object> paras) {
        URIBuilder builder;
        try {
            builder = new URIBuilder(url);
            if (paras != null && paras.size() > 0) {
                for (Map.Entry<String, Object> entry : paras.entrySet())
                    builder.setParameter(entry.getKey(), entry.getValue().toString());
            }
            URI uri = builder.build();
            logger.debug(uri.toString());
            return uri;
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 封装请求头部信息
     *
     * @param httpRequestBase 请求方法
     * @param headers         头部参数
     */
    private static void wrapHeader(HttpRequestBase httpRequestBase, Map<String, Object> headers) {
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                httpRequestBase.setHeader(entry.getKey(), entry.getValue().toString());
            }
        }
    }

    /**
     * 封装表单参数 针对 PUT(接口必须支持put表单参数),POST
     *
     * @param httpRequestBase 请求方法
     * @param formParas       表单参数
     */
    private static void wrapEntity(HttpEntityEnclosingRequestBase httpRequestBase, Map<String, Object> formParas) {
        if (formParas != null && formParas.size() > 0) {
            List<BasicNameValuePair> pairs = new ArrayList<>();
            for (Map.Entry<String, Object> entry : formParas.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            try {
                httpRequestBase.setEntity(new UrlEncodedFormEntity(pairs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 请求调用
     *
     * @param httpRequestBase 请求方法
     * @return 字符 结果
     */
    private static String invoke(HttpRequestBase httpRequestBase) {
        CloseableHttpResponse execute = null;
        try {
            execute = client.execute(httpRequestBase);
            if (execute != null) {
                String result = null;
                HttpEntity entity = execute.getEntity();
                if (entity != null) {
                    long len = entity.getContentLength();
                    if (len != -1 && len < 2048) {
                        result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                    } else {
                        result = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8);
                    }
                }
                StatusLine statusLine = execute.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    return result;
                }
                throw new RuntimeException(statusLine.toString() + " detail: " + result);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (execute != null)
                    execute.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }


}
