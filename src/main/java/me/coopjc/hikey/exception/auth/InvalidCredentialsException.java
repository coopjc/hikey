package me.coopjc.hikey.exception.auth;

import me.coopjc.hikey.exception.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {

    public InvalidCredentialsException() {
        super("UNAUTHORIZED", "The provided credentials are invalid", HttpStatus.UNAUTHORIZED);
    }
}