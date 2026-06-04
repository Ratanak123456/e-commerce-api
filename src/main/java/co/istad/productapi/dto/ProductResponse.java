package co.istad.productapi.dto;

public record ProductResponse(
        Integer id,
        String name,
        String des,
        Float price
) {
}
