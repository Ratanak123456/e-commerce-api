package co.istad.productapi.rescontroller;

import co.istad.productapi.dto.category.request.CategoryRequest;
import co.istad.productapi.dto.category.request.UpdateCategoryRequest;
import co.istad.productapi.dto.category.request.CategoryDeleteRequest;
import co.istad.productapi.dto.category.response.CategoryResponse;
import co.istad.productapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping
    Page<CategoryResponse> getCategories(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest request){
        return categoryService.createCategory(request);
    }

    @PatchMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Integer id, @RequestBody UpdateCategoryRequest request){
        return categoryService.updateCategory(id, request);
    }

    @PatchMapping("/delete/{id}")
    public CategoryResponse deleteCategory(@PathVariable Integer id, @RequestBody CategoryDeleteRequest request){
        return categoryService.deleteCategory(id, request);
    }
}
