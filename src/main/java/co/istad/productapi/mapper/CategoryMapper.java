package co.istad.productapi.mapper;

import co.istad.productapi.dto.CategoryRequest;
import co.istad.productapi.dto.CategoryResponse;
import co.istad.productapi.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);
    Category toEntity(CategoryRequest request);
}
