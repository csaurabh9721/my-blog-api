package com.my_blog_api.blogappapi.Exaptions;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String resourceName, String fieldName, String resourceValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, resourceValue));
    }

}
