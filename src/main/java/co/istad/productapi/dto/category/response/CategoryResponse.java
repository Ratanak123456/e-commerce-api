package co.istad.productapi.dto.category.response;

public record CategoryResponse (
        Integer id,
        String name,
        String slug,
        String des,
        String icon,
        Boolean isDeleted
) {

}
