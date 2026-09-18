package me.coopjc.hikey.exception.hike;

import me.coopjc.hikey.exception.BaseException;
import org.springframework.http.HttpStatus;

public class HikeNotFoundException extends BaseException {
    public HikeNotFoundException() {
        super("HIKE_NOT_FOUND", "No hike found.", HttpStatus.NOT_FOUND);
    }
}