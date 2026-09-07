package me.coopjc.hikey.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus status;

    public BaseException(String errorCode, String message, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}