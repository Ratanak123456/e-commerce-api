package co.istad.productapi.restcontrollers;

import co.istad.productapi.dto.CategoryRequest;
import co.istad.productapi.dto.CategoryResponse;
import co.istad.productapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/parents")
    List<CategoryResponse> getParentCategories(@RequestParam("sort") String sortDirection) {
        return categoryService.findParentCategories(sortDirection);
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
