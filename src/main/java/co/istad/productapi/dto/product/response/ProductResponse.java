package co.istad.productapi.dto.product.response;

public record ProductResponse(
        Integer id,
        String name,
        String des,
        Float price
) {
}
