package com.my_blog_api.blogappapi.Exaptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
//    public UserNotFoundException(String resourceName, String fieldName, String resourceValue) {
//        super(resourceName + " not found with " + fieldName + " " + resourceValue);
//    }

    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}