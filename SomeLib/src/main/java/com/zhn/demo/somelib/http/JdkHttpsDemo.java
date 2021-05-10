package com.zhn.demo.somelib.http;

import org.apache.http.conn.ssl.NoopHostnameVerifier;

import javax.net.ssl.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;

public class JdkHttpsDemo {


    public static void main(String[] args) {

        // 开发者平台app
//         String urlStr = "https://develop.api.ct10649.com:8743/iocm/app/sec/v1.1.0/login";
//         String formParas = "appId=GfSr8SeMe7ee2CZDm1oJb2634xoa&secret=ZJHd9B22YKn_ucJiGxco2FadTzAa";

        // 商用平台App 测试应用
        String urlStr = "https://device.api.ct10649.com:8743/iocm/app/sec/v1.1.0/login";
        String formParas = "appId=i834P9rn76xpz49xyn1qfsxQihoa&secret=S7VVLpMnjS9bJXifeSY5G4wy4Iwa";

        try {
            https(urlStr, formParas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   private static void https(String urlStr, String formParas) throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {

        String SELFCERTPWD = "IoM@1234";
        InputStream isSelfCert = JdkHttpsDemo.class.getResourceAsStream("/outgoing.CertwithKey.pkcs12");
        KeyStore self = KeyStore.getInstance("pkcs12");
        self.load(isSelfCert, SELFCERTPWD.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
        kmf.init(self, SELFCERTPWD.toCharArray());

        String TRUSTCAPWD = "Huawei@123";
        InputStream isTrustCa = JdkHttpsDemo.class.getResourceAsStream("/ca.jks");

        KeyStore ca = KeyStore.getInstance("jks");
        ca.load(isTrustCa, TRUSTCAPWD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(ca);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        URL url = new URL(urlStr);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setSSLSocketFactory(sslSocketFactory);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestMethod("POST");
        conn.setHostnameVerifier(NoopHostnameVerifier.INSTANCE);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream out = conn.getOutputStream();
        out.write(formParas.getBytes());
        out.flush();

        BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());

        byte[] b = new byte[2048];
        while (buf.read(b) != -1) {
            System.out.println(new String(b));
        }
        buf.close();
        out.close();


    }

}
