package me.coopjc.hikey.exception.auth;

import me.coopjc.hikey.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        super("UNAUTHORIZED", "You aren't allowed to perform this action.", HttpStatus.UNAUTHORIZED);
    }
}