package com.my_blog_api.blogappapi;

import com.my_blog_api.blogappapi.Entities.Role;
import com.my_blog_api.blogappapi.Repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
         SpringApplication.run(BlogAppApiApplication.class, args);
        //ApplicationContext context = SpringApplication.run(BlogAppApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) {
        try {
            Role role1 = new Role();
            role1.setId(1);
            role1.setName("ADMIN");
            this.roleRepo.save(role1);
            Role role2 = new Role();
            role2.setId(2);
            role2.setName("USER");
            this.roleRepo.save(role2);
            Role role3 = new Role();
            role3.setId(3);
            role3.setName("OTHER");
            this.roleRepo.save(role3);
        } catch (Exception ignored) {
        }
    }
}
