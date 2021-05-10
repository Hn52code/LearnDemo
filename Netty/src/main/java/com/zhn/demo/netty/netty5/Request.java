package com.zhn.demo.netty.netty5;

import com.zhn.demo.netty.netty5.msg.ReqMsgBody;
import com.zhn.demo.netty.netty5.msg.ReqMsgHead;

public class Request {

    private ReqMsgHead head;
    private ReqMsgBody body;

    public ReqMsgBody getBody() {
        return body;
    }

    public void setBody(ReqMsgBody body) {
        this.body = body;
    }

    public ReqMsgHead getHead() {
        return head;
    }

    public void setHead(ReqMsgHead head) {
        this.head = head;
    }
}
