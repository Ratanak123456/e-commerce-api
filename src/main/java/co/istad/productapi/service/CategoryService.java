package co.istad.productapi.service;

import co.istad.productapi.dto.category.request.CategoryRequest;
import co.istad.productapi.dto.category.request.UpdateCategoryRequest;
import co.istad.productapi.dto.category.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(CategoryRequest request);
    void deleteCategory(Integer id);
    List<CategoryResponse> findAll();
    CategoryResponse findById(Integer id);
    List<CategoryResponse> findByName(String name);

}