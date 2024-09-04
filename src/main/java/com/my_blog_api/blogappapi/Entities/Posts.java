package com.my_blog_api.blogappapi.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "postDate")
    private Date postDate;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "posts",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Comments> comments= new HashSet<>();
}
