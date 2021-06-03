package com.zhn.demo.baseweb.servlet.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {

    private String id;
    private List<User> users;

    public Group() {
        this.id = UUID.randomUUID().toString();
        users = new ArrayList<>();
    }

    public String getId() {
        return id;
    }



}
