package com.zhn.pro.demo.wx.auth;

public class AuthInfoModel {

    private final WoaInfo woaInfo;
    private TokenRefreshTimer timer;
    private boolean isStart;

    public AuthInfoModel(WoaInfo woaInfo, boolean startTimer) {
        this.woaInfo = woaInfo;
        this.isStart = startTimer;
        timer = new TokenRefreshTimer();
        if (startTimer) timer.invokeTimer(woaInfo);
    }

    public WoaInfo getWoaInfo() {
        return woaInfo;
    }

    public void startTimer() {
        if (!isStart) timer.invokeTimer(woaInfo);
    }

}
