package com.zhn.demo.mode.listener;

/* 事件，如：点击 */
public interface Event {

    /* 设置事件监听器 */
    void setListener(EventListener listener);

    /* 触发事件，该方法内部实现需调用监听器中的具体“触发事件”方法 */
    void doEvent();

}
