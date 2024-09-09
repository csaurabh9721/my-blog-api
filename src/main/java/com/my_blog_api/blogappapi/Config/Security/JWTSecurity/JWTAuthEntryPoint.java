package com.my_blog_api.blogappapi.Config.Security.JWTSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my_blog_api.blogappapi.DTO.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
       // PrintWriter writer = response.getWriter();
       // writer.println("Access Denied !! " + authException.getMessage());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ApiResponse<Object> apiResponse = new ApiResponse<>(401, null, "Unauthorized");
        String jsonResponse = new ObjectMapper().writeValueAsString(apiResponse);
        response.getWriter().write(jsonResponse);
    }
}
