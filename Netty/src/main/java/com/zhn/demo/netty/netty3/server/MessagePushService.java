package com.zhn.demo.netty.netty3.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhn.demo.netty.netty3.msg.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class MessagePushService {
    // 记录设备连线状态。
    private static ConcurrentHashMap<String, Integer> deviceOnlineStatusMap = new ConcurrentHashMap<>();

    /* 上传注册信息 */
    public void register(RegisterMessage message) {
        String mac = message.getMac();
        deviceOnlineStatusMap.put(mac, -1);
        try {
            ObjectMapper mapper = new ObjectMapper();

            String msg = mapper.writeValueAsString(message);
            System.out.println(msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /* 上传报警或故障信息 */
    public void warnOrBreakdown(WarnAndBreakdownMessage message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String msg = mapper.writeValueAsString(message);
            System.out.println(msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        push();

    }

    /* 上传实时数据 */
    public void uploadRt(UploadRealTimeMessage message) {
        String mac = message.getMac();
        Integer old = deviceOnlineStatusMap.put(mac, message.getOn_off());
        if (old != message.getOn_off()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                String msg = mapper.writeValueAsString(message);
                System.out.println(msg);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            push();
        }
    }

    /* 上传主机配件情况 */
    public void uploadParts(UploadPartsMessage message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String msg = mapper.writeValueAsString(message);
            System.out.println(msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        push();
    }

    /* 上传设备离线 */
    public void outline(Message message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String msg = mapper.writeValueAsString(message);
            System.out.println(msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        push();

    }

    private void push() {
        System.out.println("push");
    }


}
