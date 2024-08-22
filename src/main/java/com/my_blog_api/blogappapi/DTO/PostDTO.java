package com.my_blog_api.blogappapi.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostDTO {

    private String title;
    private String content;
    private String image;
    private Date postDate;
    private int categoryId;
    private int userId;
}
