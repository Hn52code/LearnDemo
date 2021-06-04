package com.zhn.demo.baseweb.servlet.chat;

import java.util.UUID;

public class Group {

    private UUID id;
    private String name;

    public Group(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }



}
