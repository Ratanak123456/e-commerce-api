package co.istad.productapi.dto.category.request;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @Size(min = 1, max = 100)
        String name,
        @Size(min = 1, max = 255)
        String description,
        // updated fields
        String icon,
        Integer parentCategoryId // NULL


) {
}