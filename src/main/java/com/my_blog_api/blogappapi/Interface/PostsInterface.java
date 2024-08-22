package com.my_blog_api.blogappapi.Interface;

import com.my_blog_api.blogappapi.DTO.PostDTO;
import com.my_blog_api.blogappapi.Entities.Category;
import com.my_blog_api.blogappapi.Entities.Posts;
import com.my_blog_api.blogappapi.Entities.User;

import java.util.List;

public interface PostsInterface {

    PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);

    Posts updatePost(PostDTO postDTO, Integer postId);

    void deletePost(Integer postId);

    List<Posts> getAllPost();

    Posts getPostById(Integer postId);

    List<PostDTO> getAllPostByUser(Integer userId);

    List<PostDTO> getAllPostByCategory(Integer categoryId);

    List<PostDTO> searchPost(String search);
}
