package me.coopjc.hikey.exception.hike;

import jakarta.servlet.http.HttpServletRequest;
import me.coopjc.hikey.dto.ErrorResponse;
import me.coopjc.hikey.exception.GlobalExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HikeExceptionHandler {

    @ExceptionHandler(HikeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleHikeNotFoundException(HikeNotFoundException ex, HttpServletRequest request) {
        ErrorResponse error = GlobalExceptionHandler.getErrorResponse(
                ex.getErrorCode(),
                ex.getMessage(),
                ex.getStatus(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, ex.getStatus());
    }
}