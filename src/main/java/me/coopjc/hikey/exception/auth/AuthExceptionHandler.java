package me.coopjc.hikey.exception.auth;

import jakarta.servlet.http.HttpServletRequest;
import me.coopjc.hikey.dto.ErrorResponse;
import me.coopjc.hikey.exception.GlobalExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex, HttpServletRequest request) {
        ErrorResponse error = GlobalExceptionHandler.getErrorResponse(
                ex.getErrorCode(),
                ex.getMessage(),
                ex.getStatus(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex, HttpServletRequest request) {
        ErrorResponse error = GlobalExceptionHandler.getErrorResponse(
                ex.getErrorCode(),
                ex.getMessage(),
                ex.getStatus(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, ex.getStatus());
    }

}