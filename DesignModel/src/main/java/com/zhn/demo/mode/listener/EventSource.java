package com.zhn.demo.mode.listener;

/* 事件源，如：按钮，需要事件触发的抽象调用 */
public class EventSource implements Event {

    /* 监听器的句柄 */
    private EventListener listener;

    /* 给事件源注册监听器 */
    @Override
    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    /* 事件触发时调用监听器的处理方法 */
    @Override
    public void doEvent() {
        if (this.listener == null) System.out.println("未注册监听器");
        else this.listener.onEvent(this);
    }
}
