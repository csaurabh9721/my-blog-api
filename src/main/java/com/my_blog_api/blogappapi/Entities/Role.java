package com.my_blog_api.blogappapi.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {
    @Id
    private  int id;
    private String name;
}
