package com.zhn.pro.demo.wx.auth;

import com.zhn.pro.demo.wx.common.Constants;
import com.zhn.pro.demo.wx.common.utils.HttpUtil;
import com.zhn.pro.demo.wx.common.utils.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenRefreshTimer {

    private final static Logger logger = LogManager.getLogger(TokenRefreshTimer.class);

    private ScheduledExecutorService executorService;

    public TokenRefreshTimer() {
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    void invokeTimer(WoaInfo woaConfig) {
        executorService.scheduleAtFixedRate(() -> {
            refreshAccessToken(woaConfig);
            refreshJsApiTicket(woaConfig);
        }, 1, 7100, TimeUnit.SECONDS);
    }

    private void refreshAccessToken(WoaInfo woaInfo) {
        String url = Constants.ACCESS_TOKEN
                .replace("APPID", woaInfo.getAppId())
                .replace("APPSECRET", woaInfo.getAppSecret());
        String resultStr = HttpUtil.httpGet(url, null, null);
        Map result = JsonUtil.asJavaObject(resultStr, HashMap.class);
        if (!result.containsKey("access_token")) {
            logger.error(resultStr);
            return;
        }
        String accessToken = result.get("access_token").toString();
        logger.debug("last access_token : " + accessToken);
        woaInfo.setAccessToken(accessToken);
    }

    private void refreshJsApiTicket(WoaInfo woaConfig) {
        String url = Constants.JSAPI_TICKET
                .replace("ACCESS_TOKEN", woaConfig.getAccessToken());
        String resultStr = HttpUtil.httpGet(url, null, null);
        Map result = JsonUtil.asJavaObject(resultStr, HashMap.class);
        if (!result.containsKey("ticket")) {
            logger.error(resultStr);
            return;
        }
        String jsTicket = result.get("ticket").toString();
        logger.debug("last jsapi ticket : " + jsTicket);
        woaConfig.setJsApiAccessToken(jsTicket);
    }

}
