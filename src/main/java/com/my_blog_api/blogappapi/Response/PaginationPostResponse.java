package com.my_blog_api.blogappapi.Response;
import com.my_blog_api.blogappapi.DTO.CommentsDto;
import com.my_blog_api.blogappapi.DTO.PostDTO;
import com.my_blog_api.blogappapi.Entities.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
public class PaginationPostResponse {
    private List<PostDTO> posts;
    private int pageNumber;
    private int size;
    private long totalElements;
    private int totalPage;
    private boolean lastPage;
    private Set<CommentsDto> comments = new HashSet<>();
}
