package com.zhn.demo.netty.netty5.msg;

public class ReqMsgHead extends MsgHead {

    private String modelId; // 模板ID

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

}
