package co.istad.productapi.service.implement;

import co.istad.productapi.advisor.ResourceAlreadyExistException;
import co.istad.productapi.dto.category.request.CategoryRequest;
import co.istad.productapi.dto.category.response.CategoryResponse;
import co.istad.productapi.entity.Category;

import co.istad.productapi.mapper.CategoryMapper;
import co.istad.productapi.repository.CategoryRepositoryJPA;
import co.istad.productapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
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
        if(categoryRepository.existsByName(request.name())){
            throw new ResourceAlreadyExistException("Category with name = "+request.name()+" already exists");
        }

        var newCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(newCategory);
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest request) {
        // Partial updates
        return null;
    }
// soft delete

    @Override
    public void deleteCategory(Integer id) {
        if(!categoryRepository.existsById(id)) {
            throw new NoSuchElementException("Category with id = " + id + " does not exist");
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