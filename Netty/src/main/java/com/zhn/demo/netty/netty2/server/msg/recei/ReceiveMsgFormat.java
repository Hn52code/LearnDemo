package com.zhn.demo.netty.netty2.server.msg.recei;

import com.zhn.demo.netty.netty2.server.msg.MessageException;

/**
 * 接口类：接收消息扩展接口<br>
 * 1. 当需要扩充消息接收类时，需要实现该接口方法，将消息内容根据需求具体的格式化.<br>
 *
 * @author zhn <br>
 */
public interface ReceiveMsgFormat {

    // 格式化方法
    void format() throws MessageException, MessageException;
}
