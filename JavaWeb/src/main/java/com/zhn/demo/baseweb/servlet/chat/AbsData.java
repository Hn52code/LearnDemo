package com.zhn.demo.baseweb.servlet.chat;

import java.time.LocalDateTime;

public abstract class AbsData {

    private String dataType;
    private LocalDateTime dateTime;

    public AbsData(String dataType) {
        this.dataType = dataType;
        this.dateTime = LocalDateTime.now();
    }

    public String getDataType() {
        return dataType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
