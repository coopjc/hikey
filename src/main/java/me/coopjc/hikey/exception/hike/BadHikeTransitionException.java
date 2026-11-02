package me.coopjc.hikey.exception.hike;

import me.coopjc.hikey.exception.BaseException;
import org.springframework.http.HttpStatus;

public class BadHikeTransitionException extends BaseException {
    public BadHikeTransitionException() {
        super("BAD_HIKE_TRANSITION", "Hike transition not allowed", HttpStatus.CONFLICT);
    }
}