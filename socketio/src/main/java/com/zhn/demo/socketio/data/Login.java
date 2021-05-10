package com.zhn.demo.socketio.data;

import java.io.Serializable;
import java.util.StringJoiner;

public class Login implements Serializable {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Login.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .toString();
    }
}
