package com.zhn.demo.socketio.controller;

import com.zhn.demo.socketio.data.SocketIOClientMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ask")
public class SocketIoRequestController {

    @Autowired
    private SocketIOClientMap clientMap;

    @GetMapping("/{id}")
    public void sendMsgToClient(@PathVariable("id") Integer id) {
        clientMap.send(id, "这是服务器测试");
    }
}
