package me.coopjc.hikey.exception.hike;

import me.coopjc.hikey.exception.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidHikeStretchException extends BaseException {
  public InvalidHikeStretchException() {
    super("INVALID_HIKE_STRETCH", "Hike stretch is invalid", HttpStatus.CONFLICT);
  }
}