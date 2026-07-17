package co.istad.productapi.dto.order;

public record OrderItemRequest(
        Integer productId,
        Integer qty
) {
}
