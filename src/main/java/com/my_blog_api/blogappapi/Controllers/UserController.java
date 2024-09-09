package com.my_blog_api.blogappapi.Controllers;

import com.my_blog_api.blogappapi.Config.Security.JWTSecurity.JWTAuthHelper;
import com.my_blog_api.blogappapi.DTO.ApiResponse;
import com.my_blog_api.blogappapi.DTO.LoginDto;
import com.my_blog_api.blogappapi.DTO.UserModel;
import com.my_blog_api.blogappapi.Exaptions.UserNotFoundException;
import com.my_blog_api.blogappapi.Response.LoginResponse;
import com.my_blog_api.blogappapi.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JWTAuthHelper helper;

    @DeleteMapping("/deleteUserBy/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable Integer id) {
        try {
            boolean entity = userService.deleteUserById(id);
            ApiResponse<Boolean> response = new ApiResponse<>(200, entity, "User Deleted Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (UserNotFoundException ex) {
            ApiResponse<Boolean> response = new ApiResponse<>(404, null, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception ex) {
            ApiResponse<Boolean> response = new ApiResponse<>(501, null, "An error occurred while deleting the user.");
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> loginUser(@RequestBody LoginDto loginDto) {
        try {
            LoginResponse entity = userService.onLogin(loginDto);
            if (entity != null) {
                ApiResponse<LoginResponse> response = new ApiResponse<>(200, entity, "Success");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ApiResponse<LoginResponse> response = new ApiResponse<>(401, null, "Invalid Credential");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception ex) {
            ApiResponse<LoginResponse> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @PostMapping("/addUser")
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

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserModel>> userRegister(@Valid @RequestBody UserModel userModel) {
        try {
            UserModel entity = userService.register(userModel);
            if (entity != null) {
                ApiResponse<UserModel> response = new ApiResponse<>(200, entity, "User Register Successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                ApiResponse<UserModel> response = new ApiResponse<>(400, null, "Bad Request");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception ex) {
            ApiResponse<UserModel> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }


    @GetMapping("/getAllUser")
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

    @GetMapping("/getUserById/{id}")
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

    @PutMapping("/updateUserById/{id}")
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

}
