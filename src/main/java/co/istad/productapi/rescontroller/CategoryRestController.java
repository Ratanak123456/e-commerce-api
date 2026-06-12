package co.istad.productapi.rescontroller;

import co.istad.productapi.dto.category.request.CategoryRequest;
import co.istad.productapi.dto.category.response.CategoryResponse;
import co.istad.productapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest request){
        return categoryService.createCategory(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);

    }
}