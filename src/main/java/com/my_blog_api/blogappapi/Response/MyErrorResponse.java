package com.my_blog_api.blogappapi.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyErrorResponse {
    private  int status;
    private Object data;
    private String message;

    public MyErrorResponse(int status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
