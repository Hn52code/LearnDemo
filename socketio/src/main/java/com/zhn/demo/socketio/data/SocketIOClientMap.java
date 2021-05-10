package com.zhn.demo.socketio.data;

import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class SocketIOClientMap {

    private static ConcurrentMap<Integer, SocketIOClient> clientMap = new ConcurrentHashMap<>();
    @Value("${socketio.emit.message}")
    private String eventName;

    public int size() {
        return clientMap.size();
    }

    public void put(Integer ID, SocketIOClient client) {
        clientMap.put(ID, client);
    }

    public void del(SocketIOClient client) {
        clientMap.values().removeIf(value -> value != client);
    }

    public void send(Integer ID, Object data) {
        SocketIOClient client = clientMap.get(ID);
        if (client != null)
            client.sendEvent(eventName,
                    new AckCallback<String>(String.class) {
                        @Override
                        public void onSuccess(String backData) {
                            System.out.println(backData);
                        }
                    },
                    data
            );
    }

}

