package report.ordersystem.spring.domain.order.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import report.ordersystem.spring.common.order.type.OrderStatus;

@Getter
@Builder
public class OrderInfo {
    private Long id;
    private OrderStatus status;
    private LocalDateTime orderDate;

    // 사용자 정보
    private Long userId;
    private String userName;
}
