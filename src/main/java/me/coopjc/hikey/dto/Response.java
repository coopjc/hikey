package me.coopjc.hikey.dto;

import java.time.LocalDateTime;
public class Response {

    private String message;
    private Record data;
    private int status;
    private LocalDateTime timestamp;

    public Response() {
        this.timestamp = LocalDateTime.now();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Record data) {
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Record getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}