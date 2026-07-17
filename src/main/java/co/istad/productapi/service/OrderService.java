package co.istad.productapi.service;

import co.istad.productapi.dto.order.CreateOrderRequest;
import co.istad.productapi.dto.order.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getAllOrdersByCustomerId(Integer customerId);

    // updateOrder
    // cancelOrder
    // deleteOrder

}
