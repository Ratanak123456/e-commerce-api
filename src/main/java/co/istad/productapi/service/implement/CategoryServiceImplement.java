package co.istad.productapi.service.implement;

import co.istad.productapi.dto.category.request.CategoryRequest;
import co.istad.productapi.dto.category.request.UpdateCategoryRequest;
import co.istad.productapi.dto.category.response.CategoryResponse;
import co.istad.productapi.entity.Category;
import co.istad.productapi.rescontroller.repository.CategoryRepository;

import co.istad.productapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImplement implements CategoryService {

    private final CategoryRepository categoryRepository;
    private Integer nextInt = 1011;

    private CategoryResponse mapToResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getDes(),
                category.getIcon(),
                category.getIsDeleted()
        );
    }

    private Category mapToEntity(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        category.setSlug(generateSlug(categoryRequest.name()));
        category.setDes(categoryRequest.des());
        category.setIcon(categoryRequest.icon());
        category.setIsDeleted(false);
        return category;
    }

    private String generateSlug(String name) {
        return name.trim()
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        var newCategory = mapToEntity(categoryRequest);
        newCategory.setId(nextInt++);
        return mapToResponse(categoryRepository.createCategory(newCategory));
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.getAllCategories().stream()
                .filter(category -> !Boolean.TRUE.equals(category.getIsDeleted()))
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        var category = categoryRepository.findCategoryById(id);
        if (Boolean.TRUE.equals(category.getIsDeleted())) {
            throw new NoSuchElementException("Category with ID: " + id + " Not Found");
        }

        return mapToResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest updateCategoryRequest) {
        var existingCategory = categoryRepository.findCategoryById(id);
        if (Boolean.TRUE.equals(existingCategory.getIsDeleted())) {
            throw new NoSuchElementException("Category with ID: " + id + " Not Found");
        }

        if (updateCategoryRequest.name() != null) {
            existingCategory.setName(updateCategoryRequest.name());
            existingCategory.setSlug(generateSlug(updateCategoryRequest.name()));
        }

        if (updateCategoryRequest.des() != null) {
            existingCategory.setDes(updateCategoryRequest.des());
        }

        if (updateCategoryRequest.icon() != null) {
            existingCategory.setIcon(updateCategoryRequest.icon());
        }

        categoryRepository.updateCategory(existingCategory);
        return mapToResponse(existingCategory);
    }

    @Override
    public boolean deleteCategory(Integer id) {
        var category = categoryRepository.findCategoryById(id);
        if (Boolean.TRUE.equals(category.getIsDeleted())) {
            throw new NoSuchElementException("Category with ID: " + id + " Not Found");
        }

        return categoryRepository.deleteCategoryById(id);
    }
}
