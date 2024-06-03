package com.my_blog_api.blogappapi.Models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {


    private Integer id;
    @NotEmpty
    @Size(min = 3, max = 100, message = "Title must be min 3 character and max 100 character")
    private String title;
    @NotEmpty
    private String description;
}
