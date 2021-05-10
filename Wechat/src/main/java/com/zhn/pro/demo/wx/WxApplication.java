package com.zhn.pro.demo.wx;

import com.zhn.pro.demo.wx.api.ApiInvokeModel;
import com.zhn.pro.demo.wx.auth.AuthInfoModel;
import com.zhn.pro.demo.wx.auth.WoaInfo;
import com.zhn.pro.demo.wx.session.DispatchHandler;
import com.zhn.pro.demo.wx.session.SessionModel;
import com.zhn.pro.demo.wx.session.handle.MessageHandlerRegister;

public class WxApplication {

    private AuthInfoModel configModel;
    private SessionModel sessionModel;
    private ApiInvokeModel apiInvokeModel;

    public WxApplication(WoaInfo woaInfo) {
        configModel = new AuthInfoModel(woaInfo, true);
    }

    public void startTimer() {
        configModel.startTimer();
    }

    public void setSessionModel(SessionModel sessionModel) {
        this.sessionModel = sessionModel;
    }

    public void setApiInvokeModel(ApiInvokeModel apiInvokeModel) {
        this.apiInvokeModel = apiInvokeModel;
    }

    public WoaInfo getWoaInfo() {
        return configModel.getWoaInfo();
    }

    public ApiInvokeModel getApiInvokeModel() {
        if (apiInvokeModel == null)
            apiInvokeModel = new ApiInvokeModel(getWoaInfo());
        return apiInvokeModel;
    }

    public synchronized void initSessionModel() {
        if (sessionModel == null)
            sessionModel = new SessionModel();
    }

    public DispatchHandler getDispatchHandler() {
        initSessionModel();
        return sessionModel.getDispatchHandler();
    }

    public void setMessageHandlerRegister(MessageHandlerRegister handlerRegister) {
        initSessionModel();
        sessionModel.setMessageHandlerRegister(handlerRegister);
    }

}
