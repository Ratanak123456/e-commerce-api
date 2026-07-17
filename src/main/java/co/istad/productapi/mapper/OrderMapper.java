package co.istad.productapi.mapper;

import co.istad.productapi.dto.order.CreateOrderRequest;
import co.istad.productapi.dto.order.OrderItemResponse;
import co.istad.productapi.dto.order.OrderResponse;
import co.istad.productapi.entity.Order;
import co.istad.productapi.entity.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerName", source = "customer.email")
    @Mapping(target = "total", expression = "java(calculateTotal(order))")
    @Mapping(target = "subTotal", expression = "java(calculateSubTotal(order))")
    OrderResponse toOrderResponse(Order order);


    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "thumbnail", source = "product.thumbnail")
    @Mapping(target = "lineTotal", expression = "java(orderLine.getUnitPrice().multiply(java.math.BigDecimal.valueOf(orderLine.getQty())))")
    OrderItemResponse toOrderItemResponse(OrderLine orderLine);

    default BigDecimal calculateSubTotal(Order order) {
            if (order.getItems() == null) {
                return BigDecimal.ZERO;
            }

        return order.getItems().stream()
          // qty x unitPrice
          .map(line-> line.getUnitPrice().multiply(BigDecimal.valueOf(line.getQty())) )
          .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    default BigDecimal calculateTotal(Order order) {
       // check discount if it's null return zero
        BigDecimal discount = order.getDiscount() == null ?
                    BigDecimal.ZERO
                    : order.getDiscount();
       return calculateSubTotal(order).subtract(discount);
    }


    Order toOrderEntity(CreateOrderRequest request);
}
