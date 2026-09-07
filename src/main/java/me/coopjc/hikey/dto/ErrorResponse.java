package me.coopjc.hikey.dto;

import java.time.LocalDateTime;
public class ErrorResponse {

    private String errorCode;
    private String message;
    private int status;
    private String path;
    private LocalDateTime timestamp;

    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPath(String path) {
        this.path = path;
    }
}