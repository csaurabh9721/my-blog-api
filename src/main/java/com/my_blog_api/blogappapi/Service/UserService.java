package com.my_blog_api.blogappapi.Service;

import com.my_blog_api.blogappapi.Entities.User;
import com.my_blog_api.blogappapi.Exaptions.UserNotFoundException;
import com.my_blog_api.blogappapi.Interface.UserInterface;
import com.my_blog_api.blogappapi.DTO.UserModel;
import com.my_blog_api.blogappapi.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserModel addUser(UserModel userModel) {
        User user = dtoToUser(userModel);
        User savedUser = this.userRepository.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserModel updateUser(UserModel userModel, Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id "+ id));
        if (user != null) {
            user.setUserName(userModel.getUserName());
            user.setEmail(userModel.getEmail());
            user.setPassword(userModel.getPassword());
            user.setAbout(userModel.getAbout());
            user.setIsActive(userModel.getIsActive() == null || userModel.getIsActive());
            User updatedUser = this.userRepository.save(user);
            return userToDto(updatedUser);
        } else {
            return null;
        }
    }

    @Override
    public List<UserModel> getAllUser() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public UserModel getUserById(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id "+ id));
        if (user != null) {
            return userToDto(user);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUserById(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id "+ id));
        if (user != null) {
            this.userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    private User dtoToUser(UserModel userModel) {
        return this.modelMapper.map(userModel, User.class);
    }

    private UserModel userToDto(User user) {
        return this.modelMapper.map(user, UserModel.class);
    }
}
