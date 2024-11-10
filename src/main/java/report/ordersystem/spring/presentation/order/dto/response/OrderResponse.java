package report.ordersystem.spring.presentation.order.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import report.ordersystem.spring.common.order.type.OrderStatus;

@Getter
@Builder
public class OrderResponse {
    private Long id;
    private OrderStatus status;
    private LocalDateTime orderDate;

    // 사용자 정보
    private Long userId;
}
