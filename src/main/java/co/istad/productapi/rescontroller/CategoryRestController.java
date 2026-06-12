package co.istad.productapi.rescontroller;

import co.istad.productapi.dto.category.request.CategoryRequest;
import co.istad.productapi.dto.category.response.CategoryResponse;
import co.istad.productapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping
    List<CategoryResponse> getCategories() {
        return categoryService.findAll();
    }

    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest request){
        return categoryService.createCategory(request);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
         categoryService.deleteCategory(id);

    }
}