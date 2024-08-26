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
import com.my_blog_api.blogappapi.Response.PaginationPostResponse;
import jakarta.persistence.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        User user = this.userRepository.findById(postDTO.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found with id " + postDTO.getUserId()));
        Category category = this.categoryRepository.findById(postDTO.getCategoryId()).orElseThrow(() -> new UserNotFoundException("Category not found with id " + postDTO.getCategoryId()));
        // Posts post = this.modelMapper.map(postDTO, Posts.class);
        Posts post = new Posts();
        post.setTitle(postDTO.getTitle());
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
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        return null;
    }

    @Override
    public boolean deletePost(Integer postId) {
        Posts posts = this.postRepository.findById(postId).orElseThrow(() -> new UserNotFoundException("Post not found with id " + postId));
        if (posts != null) {
            this.postRepository.deleteById(postId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<PostDTO> getAllPost() {
        List<Posts> posts = postRepository.findAll();
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).toList();
    }

    @Override
    public PaginationPostResponse getPostWithPagination(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page,size);
            Page<Posts> postsPage = postRepository.findAll(pageable);
            List<PostDTO> postDTOS = postsPage.getContent().stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).toList();
        PaginationPostResponse pR = new  PaginationPostResponse();
        pR.setPosts(postDTOS);
        pR.setPageNumber(postsPage.getNumber());
        pR.setSize(postsPage.getSize());
        pR.setTotalElements(postsPage.getTotalElements());
        pR.setTotalPage(postsPage.getTotalPages());
        pR.setLastPage(postsPage.isLast());
        return  pR;

    }


    @Override
    public PostDTO getPostById(Integer postId) {
        Posts post = postRepository.findById(postId).orElseThrow(() -> new UserNotFoundException("Post not found with id " + postId));
        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPostByUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Post not found with id " + userId));
        List<Posts> posts = this.postRepository.findByUser(user);
        return posts.stream().map((post -> this.modelMapper.map(post, PostDTO.class))).toList();
    }

    @Override
    public List<PostDTO> getAllPostByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new UserNotFoundException("Category not found with id " + categoryId));
        List<Posts> posts = this.postRepository.findByCategory(category);
        return posts.stream().map((post -> this.modelMapper.map(post, PostDTO.class))).toList();
    }

    @Override
    public List<PostDTO> searchPost(String search) {
        List<Posts>  posts =  this.postRepository.findByTitleContaining(search);
        return posts.stream().map((post -> this.modelMapper.map(post, PostDTO.class))).toList();
    }

}
