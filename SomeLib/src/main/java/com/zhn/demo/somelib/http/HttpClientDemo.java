package com.zhn.demo.somelib.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.LinkedList;
import java.util.List;

public class HttpClientDemo {

    public static void main(String[] args) throws Exception {
        String SELFCERTPWD = "IoM@1234";
        String TRUSTCAPWD = "Huawei@123";

        InputStream isTrustCa = HttpClientDemo.class.getResourceAsStream("/ca.jks");
        InputStream isSelfCert = HttpClientDemo.class.getResourceAsStream("/outgoing.CertwithKey.pkcs12");
        KeyStore selfCert = KeyStore.getInstance("pkcs12");
        selfCert.load(isSelfCert, SELFCERTPWD.toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
        kmf.init(selfCert, SELFCERTPWD.toCharArray());

        KeyStore caCert = KeyStore.getInstance("jks");
        caCert.load(isTrustCa, TRUSTCAPWD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(caCert);
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sc, NoopHostnameVerifier.INSTANCE);


        CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLSocketFactory(sslsf).build();
        // 创建Get请求
        HttpPost post = new HttpPost("https://develop.api.ct10649.com:8743/iocm/app/sec/v1.1.0/login");
        List<NameValuePair> nvps = new LinkedList<>();
        nvps.add(new BasicNameValuePair("appId", "GfSr8SeMe7ee2CZDm1oJb2634xoa"));
        nvps.add(new BasicNameValuePair("secret", "ZJHd9B22YKn_ucJiGxco2FadTzA1"));
        HttpEntity reqEntity = new UrlEncodedFormEntity(nvps);
        post.setEntity(reqEntity);
        System.out.println(post.toString());

        CloseableHttpResponse execute = httpClient.execute(post);
        HttpEntity entity = execute.getEntity();
        System.out.println(execute.getStatusLine());
        System.out.println(execute.getStatusLine());
        if (entity != null) System.out.println(EntityUtils.toString(entity));

        httpClient.close();
    }

}
