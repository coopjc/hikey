package me.coopjc.hikey.exception.user;

import me.coopjc.hikey.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super("USER_NOT_FOUND", "No user found.", HttpStatus.NOT_FOUND);
    }
}