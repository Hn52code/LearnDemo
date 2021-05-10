package com.zhn.demo.netty.netty3.server;

import com.zhn.demo.netty.netty3.msg.ControlSwitchMessage;
import com.zhn.demo.netty.netty3.msg.ResetMessage;
import org.springframework.stereotype.Component;

@Component
public class MessageRpcService {

    /* 请求复位 */
    public void requestReset(String mac) {


    }

    /* 请求控制--打开/关闭开关 */
    public void requestControl(String mac, int value) {
    }

    /* 响应复位 */
    public void responseReset(ResetMessage message) {
        System.out.println(message);
    }

    /* 响应控制 */
    public void responseControl(ControlSwitchMessage message) {
        System.out.println(message);
    }

}
