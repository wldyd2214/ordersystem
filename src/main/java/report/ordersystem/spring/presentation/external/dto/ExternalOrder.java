package report.ordersystem.spring.presentation.external.dto;

import lombok.Builder;
import lombok.Getter;
import report.ordersystem.spring.common.order.type.OrderStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class ExternalOrder {
    private Long orderId;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;
    private Long userId;
    private String userName;
}
