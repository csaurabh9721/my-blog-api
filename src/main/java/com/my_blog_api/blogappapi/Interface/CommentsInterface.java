package com.my_blog_api.blogappapi.Interface;
import com.my_blog_api.blogappapi.DTO.CommentsDto;

import java.util.List;

public interface CommentsInterface {
    CommentsDto  createComments(CommentsDto commentsDto, Integer postId, Integer userId);
    List<CommentsDto> getAllComments();
    CommentsDto getCommentsById();
    boolean deleteComments(Integer commentId);
    CommentsDto updateComments(CommentsDto commentsDto,Integer commentId, Integer postId, Integer userId);
}
