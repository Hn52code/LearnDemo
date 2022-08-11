package com.zhn.demo.spring.web.entity;

import javax.validation.constraints.NotNull;

public class AccessToken {

    @NotNull(message = "accessToken不能为null")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
