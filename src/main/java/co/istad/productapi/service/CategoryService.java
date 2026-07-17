package co.istad.productapi.service;

import co.istad.productapi.dto.CategoryRequest;
import co.istad.productapi.dto.CategoryResponse;
import co.istad.productapi.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(CategoryRequest request);
    void deleteCategory(Integer id);
    // get all with Pagination ( follow products sample )
    // soft delete category ( changing the value of isDeleted )

    List<CategoryResponse> findAll();
    CategoryResponse findById(Integer id);
    List<CategoryResponse> findByName(String name);


    // asc , desc
    List<CategoryResponse> findParentCategories(String sortDirection );

}
