package com.my_blog_api.blogappapi.Repository;

import com.my_blog_api.blogappapi.Entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository  extends JpaRepository<Comments, Integer> {
}
