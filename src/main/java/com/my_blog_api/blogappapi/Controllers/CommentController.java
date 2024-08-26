package com.my_blog_api.blogappapi.Controllers;

import com.my_blog_api.blogappapi.DTO.ApiResponse;
import com.my_blog_api.blogappapi.DTO.CommentsDto;
import com.my_blog_api.blogappapi.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments/api")
public class CommentController {

    @Autowired
    private CommentsService service;

    @PostMapping("/createComment/userId/{userId}/postId/{postId}")
    public ResponseEntity<ApiResponse<CommentsDto>> createComment(@RequestBody CommentsDto commentsDto, @PathVariable Integer userId, @PathVariable Integer postId) {
        try {
            CommentsDto data = this.service.createComments(commentsDto, postId , userId);
            ApiResponse<CommentsDto> response = new ApiResponse<>(201, data, "Comment Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<CommentsDto> response = new ApiResponse<>(501, null, e.toString());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/getAllComment")
    public ResponseEntity<ApiResponse<List<CommentsDto>>> getAllComment() {
        try {
            List<CommentsDto> data = this.service.getAllComments();
            ApiResponse<List<CommentsDto>> response = new ApiResponse<>(200, data, "Comment Fetched Successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<CommentsDto>> response = new ApiResponse<>(501, null, e.toString());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
