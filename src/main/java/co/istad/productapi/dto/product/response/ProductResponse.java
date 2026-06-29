package co.istad.productapi.dto.product.response;

import co.istad.productapi.dto.category.response.CategoryResponse;
import co.istad.productapi.dto.tag.response.TagResponse;

import java.util.Set;

public record ProductResponse(
        Integer id ,
        String name,
        String description,
        Float price,
        CategoryResponse category,
        Set<TagResponse> tags
        // Set<String> tags
) {
}