package report.ordersystem.spring.infrastructure.order.client.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import report.ordersystem.spring.common.order.type.OrderStatus;

@Getter
@Builder
public class ExternalOrderResponse {
    private Long orderId;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;
    private Long userId;
    private String userName;
}
