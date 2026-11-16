package me.coopjc.hikey.exception.hike;

import me.coopjc.hikey.exception.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidHikePointStateException extends BaseException {
    public InvalidHikePointStateException() {
        super("INVALID_HIKE_POINT_STATE", "Failed to push hike point(s) due to invalid hike state", HttpStatus.CONFLICT);
    }
}