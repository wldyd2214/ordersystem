package report.ordersystem.spring.infrastructure.order.entity;

import java.time.LocalDateTime;
import report.ordersystem.spring.common.order.type.OrderStatus;

public record OrderEntity(Long id, OrderStatus status, LocalDateTime orderDate, Long userId) {
}
