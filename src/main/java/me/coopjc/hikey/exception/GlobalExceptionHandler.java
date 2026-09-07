package me.coopjc.hikey.exception;

import jakarta.servlet.http.HttpServletRequest;
import me.coopjc.hikey.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // TODO: ADD A BUNCH OF ERROR HANDLERS

    // Handles a request with an invalid method
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();

        error.setErrorCode("METHOD_NOT_SUPPORTED");
        error.setMessage(ex.getMessage());
        error.setStatus(ex.getStatusCode().value());
        error.setPath(request.getRequestURI());

        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    // Handles a request that fails body validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        ErrorResponse error = new ErrorResponse();

        error.setErrorCode("VALIDATION_FAILED");
        error.setMessage("Validation failed: " + fieldErrors + ".");
        error.setStatus(ex.getStatusCode().value());
        error.setPath(request.getRequestURI());

        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    // Handles a request with an invalid body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJson(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();

        error.setErrorCode("MALFORMED_REQUEST");
        error.setMessage("Request body is not valid JSON.");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setPath(request.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}