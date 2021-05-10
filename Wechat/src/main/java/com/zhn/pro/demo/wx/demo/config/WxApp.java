package com.zhn.pro.demo.wx.demo.config;

import com.zhn.pro.demo.wx.WxApplication;
import com.zhn.pro.demo.wx.demo.handle.*;
import com.zhn.pro.demo.wx.auth.WoaInfo;
import com.zhn.pro.demo.wx.session.DispatchHandler;
import com.zhn.pro.demo.wx.session.handle.MessageHandlerRegister;
import com.zhn.pro.demo.wx.session.MessageType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class WxApp {

    private WxApplication wxApplication;
    private WoaInfo config;

    public WxApp() {
        config = new WoaInfo(
                "wxb44e1e691d936652",
                "8c311e1a7de30a5d04bc4b6699ce7526",
                "mytest",
                ""
        );
//        new WoaInfo(
//                "wx43618aba708e2628",
//                "c8e9fae01522dcfa6d3a643c98f80ec1",
//                "FindMyWay",
//                "y78FjPszJGRP8oaPTTshyZvfre7ojSJffhbmsJg2fCW"
//        );
    }

    @PostConstruct
    void init() {
        wxApplication = new WxApplication(config);
        wxApplication.setMessageHandlerRegister(new MessageHandlerRegister() {
            @Override
            public void invoke() {
                this.registerHandler(MessageType.text, new TextMessageHandler());
//                this.registerHandler(MessageType.image, new ImageMessageHandler());
//                this.registerHandler(MessageType.voice, new VoiceMessageHandler());
//                this.registerHandler(MessageType.video, new VideoMessageHandler());
//                this.registerHandler(MessageType.location, new LocationMessageHandler());
            }
        });
    }

    public DispatchHandler getDispatchHandler() {
        return wxApplication.getDispatchHandler();
    }

    public WoaInfo getConfig() {
        return config;
    }
}
