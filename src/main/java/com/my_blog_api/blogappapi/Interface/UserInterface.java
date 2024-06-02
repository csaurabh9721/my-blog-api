package com.my_blog_api.blogappapi.Interface;
import com.my_blog_api.blogappapi.Models.UserModel;
import java.util.List;


public interface UserInterface {


    UserModel addUser(UserModel userModel);

    UserModel updateUser(UserModel userModel, Integer id);

    List<UserModel> getAllUser();

    UserModel getUserById(Integer id);

    boolean deleteUserById(Integer id);

}
