package co.istad.productapi.dto;

import java.time.LocalDateTime;

public record ErrorResponse<T>(
        LocalDateTime timeStamp,
        String message,
        T error,
        Integer status
) {
}
