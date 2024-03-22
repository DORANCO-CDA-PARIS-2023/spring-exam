package com.doranco.examspring.controller.api;

import java.time.LocalDateTime;

public class Payload {

    private String message;
    private Object data;
    private LocalDateTime localDateTime;

    public Payload() {
        this.localDateTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
