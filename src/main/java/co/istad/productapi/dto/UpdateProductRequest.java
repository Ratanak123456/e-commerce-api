package co.istad.productapi.dto;

public record UpdateProductRequest(
        String name,
        String dec,
        Float price
) {
}
