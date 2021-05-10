package com.zhn.demo.mode.listener;

public class ListenerTest {

    public static void main(String[] args) {
        /* 创建事件源 */
        EventSource eventSource = new EventSource();
        /* 给事件源 注册事件 */
        eventSource.setListener(event -> {
            System.out.println(event);
            System.out.println("do event");
        });
        /* 事件源调用事件，内部实现是 调用listener.onEvent(Event e)事件 */
        eventSource.doEvent();

        System.out.println(eventSource);

        EventSource eventSource1 = new EventSource();
        eventSource1.doEvent();

    }

}
