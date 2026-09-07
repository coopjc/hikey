package me.coopjc.hikey.exception.user;

import me.coopjc.hikey.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException() {
        super("USER_ALREADY_EXISTS", "A user with that email already exists.", HttpStatus.CONFLICT);
    }
}