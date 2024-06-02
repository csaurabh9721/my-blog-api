package com.my_blog_api.blogappapi.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty
    @Size(min = 3, max = 100, message = "Name must be min 3 character")
    private String userName;

    @Email(message = "Email is not valid!!")
    private String email;

    @NotEmpty
    @Size(min = 6, max = 20, message = "Password must be min 3 character and maximum 20 character")
    private String password;

    @NotEmpty
    private String about;

    private Boolean isActive;
}
