package com.my_blog_api.blogappapi.Interface;
import com.my_blog_api.blogappapi.DTO.CategoryDto;
import java.util.List;

public interface CategoryInterface {

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

    List<CategoryDto> getAllCategory();

    CategoryDto getCategoryById(Integer id);

    boolean deleteCategoryById(Integer id);

}
