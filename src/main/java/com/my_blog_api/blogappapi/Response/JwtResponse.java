package com.my_blog_api.blogappapi.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String jwtToken;
    private String username;
}
