package com.my_blog_api.blogappapi.Service;

import com.my_blog_api.blogappapi.Entities.Category;
import com.my_blog_api.blogappapi.Exaptions.UserNotFoundException;
import com.my_blog_api.blogappapi.Interface.CategoryInterface;
import com.my_blog_api.blogappapi.Models.CategoryDto;
import com.my_blog_api.blogappapi.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryService implements CategoryInterface {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = dtoToEntity(categoryDto);
        Category savedCategory = this.categoryRepository.save(category);
        return entityToDto(savedCategory);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Category", "id", id.toString()));

        if (category != null) {
            category.setTitle(categoryDto.getTitle());
            category.setDescription(categoryDto.getDescription());
            Category savedCategory = this.categoryRepository.save(category);
            return entityToDto(savedCategory);
        } else {
            return null;
        }
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Category", "id", id.toString()));
        if (category != null) {
            return entityToDto(category);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteCategoryById(Integer id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Category", "id", id.toString()));
        if (category != null) {
            this.categoryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private CategoryDto entityToDto(Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }

    private Category dtoToEntity(CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }
}
