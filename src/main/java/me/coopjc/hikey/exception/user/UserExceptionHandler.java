package me.coopjc.hikey.exception.user;

import jakarta.servlet.http.HttpServletRequest;
import me.coopjc.hikey.dto.ErrorResponse;
import me.coopjc.hikey.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex, HttpServletRequest request) {
        ErrorResponse error = GlobalExceptionHandler.getErrorResponse(
                ex.getErrorCode(),
                ex.getMessage(),
                ex.getStatus(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        ErrorResponse error = GlobalExceptionHandler.getErrorResponse(
                ex.getErrorCode(),
                ex.getMessage(),
                ex.getStatus(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, ex.getStatus());
    }

}