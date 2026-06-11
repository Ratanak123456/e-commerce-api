package co.istad.productapi.dto.category.response;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer id,
        String name,
        String description
) {
}