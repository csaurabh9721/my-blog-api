package com.my_blog_api.blogappapi.Interface;
import com.my_blog_api.blogappapi.DTO.LoginDto;
import com.my_blog_api.blogappapi.DTO.UserModel;
import com.my_blog_api.blogappapi.Response.LoginResponse;

import java.util.List;


public interface UserInterface {


    LoginResponse onLogin(LoginDto loginDto);

    UserModel register(UserModel userModel);

    UserModel addUser(UserModel userModel);

    UserModel updateUser(UserModel userModel, Integer id);

    List<UserModel> getAllUser();

    UserModel getUserById(Integer id);

    boolean deleteUserById(Integer id);

}
