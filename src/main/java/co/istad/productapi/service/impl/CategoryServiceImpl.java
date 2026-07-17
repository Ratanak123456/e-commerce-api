package co.istad.productapi.service.impl;

import co.istad.productapi.advisor.ResourceAlreadyExistException;
import co.istad.productapi.dto.CategoryRequest;
import co.istad.productapi.dto.CategoryResponse;
import co.istad.productapi.entity.Category;
import co.istad.productapi.mapper.CategoryMapper;
import co.istad.productapi.repository.CategoryRepository;
import co.istad.productapi.service.CategoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        // map from request to entity
        Category category = categoryMapper.toEntity(request);
        // if the parent_cateogry_id provided , we validate it first
        if(request.parentCategoryId()!=null ){
            // check if it exists
                var  parentCategory = categoryRepository
                        .findById(request.parentCategoryId())
                        .orElseThrow(
                                ()-> new NoSuchElementException("Parent category with id = "+request.parentCategoryId() + " doesn't exists ! ")
                        );
            // add parent category for the newly created category
            category.setParentCategory(parentCategory);
        }
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

    @Override
    public List<CategoryResponse> findParentCategories(String sortDirection) {
        // use "name" of the category to sort
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        if("desc".equalsIgnoreCase(sortDirection)){
            sort = sort.descending();
        }else sort = sort.ascending();

        return categoryRepository.findByParentCategoryIsNull(sort)
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }
}
