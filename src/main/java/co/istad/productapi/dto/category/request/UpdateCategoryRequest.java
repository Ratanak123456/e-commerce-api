package co.istad.productapi.dto.category.request;

public record UpdateCategoryRequest(
        String name,
        String des,
        String icon
) {
}
