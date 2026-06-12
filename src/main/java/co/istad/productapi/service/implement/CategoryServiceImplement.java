package co.istad.productapi.service.implement;

import co.istad.productapi.advisor.ResourceAlreadyExistException;
import co.istad.productapi.dto.category.request.CategoryRequest;
import co.istad.productapi.dto.category.request.UpdateCategoryRequest;
import co.istad.productapi.dto.category.response.CategoryResponse;
import co.istad.productapi.entity.Category;

import co.istad.productapi.mapper.CategoryMapper;
import co.istad.productapi.repository.CategoryRepositoryJPA;
import co.istad.productapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplement implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepositoryJPA categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        // map from request to entity
        Category category = categoryMapper.toEntity(request);
        // derived query
        if(categoryRepository.existsByNameAndIsDeletedFalse(request.name())){
            throw new ResourceAlreadyExistException("Category with name = "+request.name()+" already exists");
        }
        category.setIsDeleted(false);

        var newCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(newCategory);
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request) {
        var existingCategory = categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new NoSuchElementException("Category with ID = "+id+" not found"));

        if(request.name()!=null) {
            if(categoryRepository.existsByNameAndIsDeletedFalseAndIdNot(request.name(), id)){
                throw new ResourceAlreadyExistException("Category with name = "+request.name()+" already exists");
            }
            existingCategory.setName(request.name());
        }
        if(request.des()!=null)
            existingCategory.setDescription(request.des());

        categoryRepository.save(existingCategory);
        return categoryMapper.toResponse(existingCategory);
    }
// soft delete

    @Override
    public void deleteCategory(Integer id) {
        var existingCategory = categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new NoSuchElementException("Category with ID = "+id+" not found"));

        existingCategory.setIsDeleted(true);
        categoryRepository.save(existingCategory);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findByIsDeletedFalse()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public Page<CategoryResponse> findAll(Pageable pageable) {
        return categoryRepository.findByIsDeletedFalse(pageable)
                .map(categoryMapper::toResponse);
    }

    @Override
    public CategoryResponse findById(Integer id) {
        var category = categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new NoSuchElementException("Category with ID = "+id+" not found"));

        return categoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> findByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCaseAndIsDeletedFalse(name)
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }
}
