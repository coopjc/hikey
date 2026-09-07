package me.coopjc.hikey.exception.auth;

import me.coopjc.hikey.exception.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {

    public InvalidCredentialsException() {
        super("INVALID_CREDENTIALS", "The provided credentials are invalid.", HttpStatus.UNAUTHORIZED);
    }
}