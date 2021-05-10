package com.zhn.demo.basic.socket.general;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable {

    private boolean connectMark = true;
    private Socket socket;
    private BufferedInputStream bif;
    private BufferedOutputStream bof;

    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
            this.bif = new BufferedInputStream(socket.getInputStream());
            this.bof = new BufferedOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        // 业务
        while (connectMark) {

        }
        // 关闭连接
        try {
            bof.close();
            bif.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopConnected() {
        this.connectMark = false;
    }

}
