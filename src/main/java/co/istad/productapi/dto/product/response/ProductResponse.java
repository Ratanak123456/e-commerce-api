package co.istad.productapi.dto.product.response;

import co.istad.productapi.dto.category.response.CategoryResponse;
import co.istad.productapi.dto.tag.response.TagResponse;

import java.math.BigDecimal;
import java.util.Set;

public record ProductResponse(
        Integer id ,
        String name,
        String description,
        BigDecimal price,
        String slug,
        String thumbnail,
        Integer qty ,
        CategoryResponse category,
        // Set<TagResponse> tags
        Set<String> tags
) {
}