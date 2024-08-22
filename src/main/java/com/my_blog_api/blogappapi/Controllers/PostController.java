package com.my_blog_api.blogappapi.Controllers;

import com.my_blog_api.blogappapi.DTO.ApiResponse;
import com.my_blog_api.blogappapi.DTO.PostDTO;
import com.my_blog_api.blogappapi.Entities.Posts;
import com.my_blog_api.blogappapi.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create_post/user/{userId}/category/{categoryId}")
    ResponseEntity<ApiResponse<PostDTO>> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        try {
            PostDTO data = this.postService.createPost(postDTO, userId, categoryId);

            if (data != null) {
                ApiResponse<PostDTO> response = new ApiResponse<PostDTO>(200, data, "Post Created");
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
            } else {
                ApiResponse<PostDTO> response = new ApiResponse<PostDTO>(400, null, "Bad Request");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

        } catch (Exception e) {
            ApiResponse<PostDTO> response = new ApiResponse<PostDTO>(401, null, e.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }

    }

    @GetMapping("/getPostBy/category/{categoryId}")
    ResponseEntity<ApiResponse<List<PostDTO>>> getPostByCategory(@PathVariable Integer categoryId) {
        try {
            List<PostDTO> data = this.postService.getAllPostByCategory(categoryId);

            if (data != null) {
                ApiResponse<List<PostDTO>> response = new ApiResponse<List<PostDTO>>(200, data, "Fetch successfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ApiResponse<List<PostDTO>> response = new ApiResponse<List<PostDTO>>(400, null, "Bad Request");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

        } catch (Exception e) {
            ApiResponse<List<PostDTO>> response = new ApiResponse<List<PostDTO>>(401, null, e.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @GetMapping("/getPostBy/user/{userId}")
    public ResponseEntity<ApiResponse<List<PostDTO>>> getPostByUser(@PathVariable Integer userId) {
        try {
            List<PostDTO> data = this.postService.getAllPostByUser(userId);

            if (data != null) {
                ApiResponse<List<PostDTO>> response = new ApiResponse<>(200, data, "Fetch successfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ApiResponse<List<PostDTO>> response = new ApiResponse<>(400, null, "Bad Request");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            ApiResponse<List<PostDTO>> response = new ApiResponse<>(500, null, e.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }
}
