package co.istad.productapi.service;

import co.istad.productapi.dto.category.request.CategoryRequest;
import co.istad.productapi.dto.category.request.UpdateCategoryRequest;
import co.istad.productapi.dto.category.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request);
    void deleteCategory(Integer id);
    // get all with Pagination ( follow products sample )
    // soft delete category ( changing the value of isDeleted )

    List<CategoryResponse> findAll();
    Page<CategoryResponse> findAll(Pageable pageable);
    CategoryResponse findById(Integer id);
    List<CategoryResponse> findByName(String name);

}
