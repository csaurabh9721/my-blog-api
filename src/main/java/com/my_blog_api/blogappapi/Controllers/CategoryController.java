package com.my_blog_api.blogappapi.Controllers;
import com.my_blog_api.blogappapi.DTO.ApiResponse;
import com.my_blog_api.blogappapi.DTO.CategoryDto;
import com.my_blog_api.blogappapi.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllUser() {
        try{
            List<CategoryDto> entity = categoryService.getAllCategory();
            if (entity != null) {
                ApiResponse<List<CategoryDto>> response = new ApiResponse<>(200, entity, "Success");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ApiResponse<List<CategoryDto>> response = new ApiResponse<>(404, null, "Entity not found");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }

        } catch (Exception ex) {
            ApiResponse<List<CategoryDto>> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> getUserById(@PathVariable Integer id) {
        try{
            CategoryDto entity = categoryService.getCategoryById(id);
            ApiResponse<CategoryDto> response = new ApiResponse<>(200, entity, "Success");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception ex) {
            ApiResponse<CategoryDto> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<CategoryDto>> addUser(@Valid @RequestBody CategoryDto categoryDto) {
        try {
            CategoryDto entity = categoryService.addCategory(categoryDto);
            if (entity != null) {
                ApiResponse<CategoryDto> response = new ApiResponse<>(200, entity, "Category created successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                ApiResponse<CategoryDto> response = new ApiResponse<>(400, null, "Entity not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception ex) {
            ApiResponse<CategoryDto> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> updateUser(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        try {
            CategoryDto entity = categoryService.updateCategory(categoryDto,id);
            if (entity != null) {
                ApiResponse<CategoryDto> response = new ApiResponse<>(200, entity, "Category update successfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ApiResponse<CategoryDto> response = new ApiResponse<>(404, null, "Entity not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception ex) {
            ApiResponse<CategoryDto> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable Integer id) {
        try {
            boolean entity = categoryService.deleteCategoryById(id);
            ApiResponse<Boolean> response = new ApiResponse<>(200, entity, "Category deleted successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } catch (Exception ex) {
            ApiResponse<Boolean> response = new ApiResponse<>(501, null, ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }
}
