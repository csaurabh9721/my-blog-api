package com.my_blog_api.blogappapi.Exaptions;


import com.my_blog_api.blogappapi.Models.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Boolean>> resourceNotFoundException(UserNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse<Boolean> response = new ApiResponse<>(404, false, message);
        return   ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
