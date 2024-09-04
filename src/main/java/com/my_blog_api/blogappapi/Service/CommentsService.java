package com.my_blog_api.blogappapi.Service;

import com.my_blog_api.blogappapi.DTO.CommentsDto;
import com.my_blog_api.blogappapi.Entities.Comments;
import com.my_blog_api.blogappapi.Entities.Posts;
import com.my_blog_api.blogappapi.Entities.User;
import com.my_blog_api.blogappapi.Exaptions.UserNotFoundException;
import com.my_blog_api.blogappapi.Interface.CommentsInterface;
import com.my_blog_api.blogappapi.Repository.CommentsRepository;
import com.my_blog_api.blogappapi.Repository.PostRepository;
import com.my_blog_api.blogappapi.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentsService implements CommentsInterface {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentsDto createComments(CommentsDto commentsDto, Integer postId, Integer userId) {
        Posts posts = this.postRepository.findById(postId).orElseThrow(() -> new UserNotFoundException("Post not found with id " + postId));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + postId));
        Comments comments = this.modelMapper.map(commentsDto, Comments.class);
        comments.setUser(user);
        comments.setPosts(posts);
        Comments savedComment = this.commentsRepository.save(comments);
        return this.modelMapper.map(savedComment, CommentsDto.class);
    }

    @Override
    public List<CommentsDto> getAllComments() {
        List<Comments> comments  = this.commentsRepository.findAll();
        return comments.stream().map((e)->this.modelMapper.map(e, CommentsDto.class)).toList();
    }

    @Override
    public CommentsDto getCommentsById() {
        return null;
    }

    @Override
    public boolean deleteComments(Integer commentId) {
        return false;
    }

    @Override
    public CommentsDto updateComments(CommentsDto commentsDto, Integer commentId, Integer postId, Integer userId) {
        return null;
    }
}
