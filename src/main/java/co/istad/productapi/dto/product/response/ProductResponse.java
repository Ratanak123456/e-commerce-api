package co.istad.productapi.dto.product.response;

import co.istad.productapi.dto.category.response.CategoryResponse;

public record ProductResponse(
        Integer id,
        String name,
        String des,
        Float price,
        CategoryResponse category
) {
}