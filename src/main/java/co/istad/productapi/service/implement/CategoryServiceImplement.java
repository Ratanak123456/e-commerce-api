package co.istad.productapi.service.implement;

import co.istad.productapi.advisor.ResourceAlreadyExistException;
import co.istad.productapi.dto.category.request.CategoryRequest;
import co.istad.productapi.dto.category.request.UpdateCategoryRequest;
import co.istad.productapi.dto.category.response.CategoryResponse;
import co.istad.productapi.entity.Category;

import co.istad.productapi.mapper.CategoryMapper;
import co.istad.productapi.rescontroller.repository.CategoryRepositoryJPA;
import co.istad.productapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplement implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepositoryJPA categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        // TODO: check if the name already exist
        if(categoryRepository.existsByName(request.name())){
            // throw exception handler
            throw new ResourceAlreadyExistException("category already exists");
        }

        var newCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(newCategory);
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest request) {
        return null;
    }


    @Override
    public void deleteCategory(Integer id) {
        if(!categoryRepository.existsById(id)) {
            throw new NoSuchElementException("Category with id = " + id +" Does not exist ");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse findById(Integer id) {
        return null;
    }

    @Override
    public List<CategoryResponse> findByName(String name) {
        return List.of();
    }
}