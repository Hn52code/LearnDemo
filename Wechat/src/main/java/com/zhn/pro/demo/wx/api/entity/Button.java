package com.zhn.pro.demo.wx.api.entity;

import java.util.List;

public class Button {

    private List<Menu> button;

    public List<Menu> getButton() {
        return button;
    }

    public void setButton(List<Menu> button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "Button{" +
                "button=" + button.toString() +
                '}';
    }
}
