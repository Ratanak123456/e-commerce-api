package co.istad.productapi.dto.product.request;

public record UpdateProductRequest(
        String name,
        String dec,
        Float price
) {
}
