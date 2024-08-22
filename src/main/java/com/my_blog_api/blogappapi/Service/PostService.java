package com.my_blog_api.blogappapi.Service;

import com.my_blog_api.blogappapi.DTO.PostDTO;
import com.my_blog_api.blogappapi.Entities.Category;
import com.my_blog_api.blogappapi.Entities.Posts;
import com.my_blog_api.blogappapi.Entities.User;
import com.my_blog_api.blogappapi.Exaptions.UserNotFoundException;
import com.my_blog_api.blogappapi.Interface.PostsInterface;
import com.my_blog_api.blogappapi.Repository.CategoryRepository;
import com.my_blog_api.blogappapi.Repository.PostRepository;
import com.my_blog_api.blogappapi.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService implements PostsInterface {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        User user = this.userRepository.findById(postDTO.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found with id "+ postDTO.getUserId()));
        Category category = this.categoryRepository.findById(postDTO.getCategoryId()).orElseThrow(() -> new UserNotFoundException("Category not found with id "+ postDTO.getCategoryId()));
       // Posts post = this.modelMapper.map(postDTO, Posts.class);
       Posts post = new Posts();
       post.setTitle(postDTO.getTitle());
       post.setContent(postDTO.getContent());
        post.setImage("default.png");
        post.setUser(user);
        post.setCategory(category);
        post.setPostDate(new Date());
        Posts savedPost = this.postRepository.save(post);
        return this.modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public Posts updatePost(PostDTO postDTO, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Posts> getAllPost() {
        return List.of();
    }

    @Override
    public Posts getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDTO> getAllPostByUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new  UserNotFoundException("User not found with id "+ userId));
        List<Posts> posts = this.postRepository.findByUser(user);
        return posts.stream().map((post -> this.modelMapper.map(post,PostDTO.class))).toList();
    }

    @Override
    public List<PostDTO> getAllPostByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new UserNotFoundException("Category not found with id "+ categoryId));
        List<Posts> posts = this.postRepository.findByCategory(category);
        return posts.stream().map((post -> this.modelMapper.map(post, PostDTO.class))).toList();
    }

    @Override
    public List<PostDTO> searchPost(String search) {
        return List.of();
    }

}
