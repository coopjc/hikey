package me.coopjc.hikey.exception.user;

import jakarta.servlet.http.HttpServletRequest;
import me.coopjc.hikey.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();

        error.setErrorCode(ex.getErrorCode());
        error.setMessage(ex.getMessage());
        error.setStatus(ex.getStatus().value());
        error.setPath(request.getRequestURI());

        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();

        error.setErrorCode(ex.getErrorCode());
        error.setMessage(ex.getMessage());
        error.setStatus(ex.getStatus().value());
        error.setPath(request.getRequestURI());

        return new ResponseEntity<>(error, ex.getStatus());
    }

}