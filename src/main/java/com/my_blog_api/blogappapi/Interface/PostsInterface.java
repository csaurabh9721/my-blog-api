package com.my_blog_api.blogappapi.Interface;

import com.my_blog_api.blogappapi.DTO.PostDTO;
import com.my_blog_api.blogappapi.Entities.Category;
import com.my_blog_api.blogappapi.Entities.Posts;
import com.my_blog_api.blogappapi.Entities.User;

import java.util.List;

public interface PostsInterface {

    PostDTO createPost(PostDTO postDTO);

    PostDTO updatePost(PostDTO postDTO, Integer postId);

    boolean deletePost(Integer postId);

    List<PostDTO> getAllPost();
    List<PostDTO> getPostWithPagination(Integer size, Integer page);

    PostDTO getPostById(Integer postId);

    List<PostDTO> getAllPostByUser(Integer userId);

    List<PostDTO> getAllPostByCategory(Integer categoryId);

    List<PostDTO> searchPost(String search);
}
