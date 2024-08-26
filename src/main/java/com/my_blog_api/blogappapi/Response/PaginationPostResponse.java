package com.my_blog_api.blogappapi.Response;
import com.my_blog_api.blogappapi.DTO.PostDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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
}
