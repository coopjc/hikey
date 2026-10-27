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

    // Handles a request with an invalid argument
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        ErrorResponse error = getErrorResponse(
                "BAD_REQUEST",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handles a request with an invalid method
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        ErrorResponse error = getErrorResponse(
                "METHOD_NOT_ALLOWED",
                ex.getMessage(),
                HttpStatus.METHOD_NOT_ALLOWED,
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    // Handles a request that fails body validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        ErrorResponse error = getErrorResponse(
                "VALIDATION_FAILED",
                fieldErrors.toString(),
                HttpStatus.UNPROCESSABLE_CONTENT,
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    // Handles a request with an invalid body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJson(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ErrorResponse error = getErrorResponse(
                "MALFORMED_REQUEST",
                "Request body is not valid JSON.",
                HttpStatus.BAD_REQUEST,
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    public static ErrorResponse getErrorResponse(String code, String message, HttpStatus status, String path) {
        ErrorResponse response = new ErrorResponse();

        response.setErrorCode(code);
        response.setMessage(message);
        response.setStatus(status.value());
        response.setPath(path);

        return response;
    }

}