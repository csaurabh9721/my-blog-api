package com.my_blog_api.blogappapi.Exaptions;


import com.my_blog_api.blogappapi.DTO.ApiResponse;
import com.my_blog_api.blogappapi.Response.MyErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(404, null, "URL not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        MyErrorResponse response = new MyErrorResponse(400, null, "Username or Password is wrong");
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> res = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            res.put(field, message);
        });
        ApiResponse<Map<String, String>> response = new ApiResponse<>(400, res, "Validation Error");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Boolean>> handleUserNotFoundException(UserNotFoundException ex) {
        ApiResponse<Boolean> response = new ApiResponse<>(404, null, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Boolean>> handleGeneralException(Exception ex) {
        ApiResponse<Boolean> response = new ApiResponse<>(501, null, "An error occurred while processing your request.");
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
    }
}
