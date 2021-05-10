package com.zhn.demo.netty.netty3.msg;

import java.util.HashMap;

public class UploadPartsMessage extends Message {

    /* 部件情况 */
    private HashMap<String, Item> parts = new HashMap<>();

    public HashMap<String, Item> getParts() {
        return parts;
    }

    public void setParts(HashMap<String, Item> parts) {
        this.parts = parts;
    }

    public class Item {
        private int total;
        private int online;
        private int outline;

        public Item() {
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public int getOutline() {
            return outline;
        }

        public void setOutline(int outline) {
            this.outline = outline;
        }
    }
}
