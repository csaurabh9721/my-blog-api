package com.my_blog_api.blogappapi.Models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class UserModel {
    private Integer id;
    private String userName;
    private String email;
    private String password;
    private String about;
    private Boolean isActive;
}
