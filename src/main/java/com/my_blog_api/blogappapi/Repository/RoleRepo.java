package com.my_blog_api.blogappapi.Repository;

import com.my_blog_api.blogappapi.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
}
