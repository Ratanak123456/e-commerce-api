package co.istad.productapi.dto;

public record ProductRequest(
        String name,
        String des,
        Float price
) {
}
