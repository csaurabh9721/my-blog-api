package com.my_blog_api.blogappapi.Service;

import com.my_blog_api.blogappapi.Config.Constants;
import com.my_blog_api.blogappapi.Config.Security.JWTSecurity.JWTAuthHelper;
import com.my_blog_api.blogappapi.DTO.LoginDto;
import com.my_blog_api.blogappapi.DTO.UserModel;
import com.my_blog_api.blogappapi.Entities.Role;
import com.my_blog_api.blogappapi.Entities.User;
import com.my_blog_api.blogappapi.Exaptions.UserNotFoundException;
import com.my_blog_api.blogappapi.Interface.UserInterface;
import com.my_blog_api.blogappapi.Repository.RoleRepo;
import com.my_blog_api.blogappapi.Repository.UserRepository;
import com.my_blog_api.blogappapi.Response.LoginResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements UserInterface {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JWTAuthHelper helper;

    @Override
    public LoginResponse onLogin(LoginDto loginDto) {
        Authentication authentication = this.manager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = userRepository.findByEmail(loginDto.getEmail());
            if (user != null) {
                String token = helper.GenerateToken(loginDto.getEmail());
                return userToLoginResponse(user, token);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public UserModel register(UserModel userModel) {
        User user = dtoToUser(userModel);
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role role = this.roleRepo.findById(Constants.NORMAL_USER).get();
        user.setRole(role);
        User newUser = this.userRepository.save(user);
        return userToDto(newUser);
    }

    @Override
    public UserModel addUser(UserModel userModel) {
        User user = dtoToUser(userModel);
        User savedUser = this.userRepository.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserModel updateUser(UserModel userModel, Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        if (user != null) {
            String encodedPassword = passwordEncoder.encode(userModel.getPassword());
            user.setUserName(userModel.getUserName());
            user.setEmail(userModel.getEmail());
            user.setPassword(encodedPassword);
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
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        if (user != null) {
            return userToDto(user);
        } else {
            return null;
        }
    }


    @Override
    public boolean deleteUserById(Integer id) {
         this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        this.userRepository.deleteById(id);
        return true;
    }

    private User dtoToUser(UserModel userModel) {
        return this.modelMapper.map(userModel, User.class);
    }

    private UserModel userToDto(User user) {
        return this.modelMapper.map(user, UserModel.class);
    }

    private LoginResponse userToLoginResponse(User user, String token) {
        LoginResponse response = new LoginResponse();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setAbout(user.getAbout());
        response.setIsActive(user.getIsActive());
        response.setAccessToken(token);
        response.setRole(user.getRole());
        return response;
    }
}
