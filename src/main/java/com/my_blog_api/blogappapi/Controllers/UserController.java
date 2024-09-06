package com.my_blog_api.blogappapi.Controllers;

import com.my_blog_api.blogappapi.DTO.ApiResponse;
import com.my_blog_api.blogappapi.DTO.LoginDto;
import com.my_blog_api.blogappapi.DTO.UserModel;
import com.my_blog_api.blogappapi.Response.JwtResponse;
import com.my_blog_api.blogappapi.Response.LoginResponse;
import com.my_blog_api.blogappapi.Security.JWTSecurity.JWTAuthHelper;
import com.my_blog_api.blogappapi.Service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse<LoginResponse>> loginUser(@RequestBody LoginDto loginDto) {
//        try {
//            LoginResponse entity = userService.onLogin(loginDto);
//            if (entity != null) {
//                ApiResponse<LoginResponse> response = new ApiResponse<>(200, entity, "Success");
//                return ResponseEntity.status(HttpStatus.OK).body(response);
//            } else {
//                ApiResponse<LoginResponse> response = new ApiResponse<>(401, null, "Invalid Credential");
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//            }
//        } catch (Exception ex) {
//            ApiResponse<LoginResponse> response = new ApiResponse<>(501, null, ex.toString());
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
//        }
//    }


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JWTAuthHelper helper;

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto request) {

//        this.doAuthenticate(request.getEmail(), request.getPassword());
//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//        String token = this.helper.GenerateToken(request.getEmail());
//
//        JwtResponse response = new JwtResponse();
//        response.setJwtToken(token);
//        response.setUsername(request.getEmail());
//        return new ResponseEntity<>(response, HttpStatus.OK);

        Authentication authentication = this.manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if(authentication.isAuthenticated()){
                    JwtResponse response = new JwtResponse();
        response.setJwtToken(helper.GenerateToken(request.getEmail()));
        response.setUsername(request.getEmail());
        return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<UserModel>>> getAllUser() {
        try {
            List<UserModel> entity = userService.getAllUser();
            if (entity != null) {
                ApiResponse<List<UserModel>> response = new ApiResponse<>(200, entity, "Success");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ApiResponse<List<UserModel>> response = new ApiResponse<>(404, null, "Entity not found");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }

        } catch (Exception ex) {
            ApiResponse<List<UserModel>> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserModel>> getUserById(@PathVariable Integer id) {
        try {
            UserModel entity = userService.getUserById(id);
            ApiResponse<UserModel> response = new ApiResponse<>(200, entity, "Success");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception ex) {
            ApiResponse<UserModel> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<UserModel>> addUser(@Valid @RequestBody UserModel userModel) {
        try {
            UserModel entity = userService.addUser(userModel);
            if (entity != null) {
                ApiResponse<UserModel> response = new ApiResponse<>(200, entity, "User Created Successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                ApiResponse<UserModel> response = new ApiResponse<>(400, null, "Entity not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception ex) {
            ApiResponse<UserModel> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserModel>> updateUser(@Valid @RequestBody UserModel userModel, @PathVariable Integer id) {
        try {
            UserModel entity = userService.updateUser(userModel, id);
            if (entity != null) {
                ApiResponse<UserModel> response = new ApiResponse<>(200, entity, "User Update Successfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ApiResponse<UserModel> response = new ApiResponse<>(404, null, "Entity not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception ex) {
            ApiResponse<UserModel> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable Integer id) {
        try {
            boolean entity = userService.deleteUserById(id);
            ApiResponse<Boolean> response = new ApiResponse<>(200, entity, "User Deleted Successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } catch (Exception ex) {
            ApiResponse<Boolean> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }
}
