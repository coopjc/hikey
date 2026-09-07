package me.coopjc.hikey.exception.user;

import me.coopjc.hikey.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super("USER_NOT_FOUND", "The requested user was not found.", HttpStatus.NOT_FOUND);
    }
}