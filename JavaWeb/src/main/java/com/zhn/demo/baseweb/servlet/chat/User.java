package com.zhn.demo.baseweb.servlet.chat;

import javax.websocket.Session;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private Session session;

    public User(String name, Session session) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.session = session;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) return false;
        User user = (User) obj;
        return user.session.equals(this.session) && user.id.equals(id) && user.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        int result = this.session.hashCode();
        result = result * 13 + this.id.hashCode();
        result = result * 13 + this.name.hashCode();
        return result;
    }


}
