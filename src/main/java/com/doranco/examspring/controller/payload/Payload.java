package com.doranco.examspring.controller.payload;

import java.time.LocalDateTime;

public class Payload {

    private Object content;

    private String message;

    private LocalDateTime dateTime;

    public Payload(){
        this.dateTime = LocalDateTime.now();
    }

    public Payload(String message, Object content) {
        this();
        this.content = content;
        this.message = message;
    }

    public Payload(String message) {
        this();
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
