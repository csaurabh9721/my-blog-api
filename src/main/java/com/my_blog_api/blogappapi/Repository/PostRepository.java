package com.my_blog_api.blogappapi.Repository;

import com.my_blog_api.blogappapi.Entities.Category;
import com.my_blog_api.blogappapi.Entities.Posts;
import com.my_blog_api.blogappapi.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Posts, Integer> {

    List<Posts> findByUser(User user);

    List<Posts> findByCategory(Category category);
}
