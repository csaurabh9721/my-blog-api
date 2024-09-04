package com.my_blog_api.blogappapi.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PostDTO {

    private Integer id;
    @NotNull
    private String title;
    @NotNull
    private String content;
    private String image;
    private Date postDate;
    @NotNull
    private int categoryId;
    @NotNull
    private int userId;
    private List<CommentsDto> comments;

}
