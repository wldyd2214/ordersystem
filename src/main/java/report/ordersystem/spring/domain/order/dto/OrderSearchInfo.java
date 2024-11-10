package report.ordersystem.spring.domain.order.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import report.ordersystem.spring.infrastructure.order.entity.OrderEntity;

@Getter
@Builder
public class OrderSearchInfo {
    private List<OrderEntity> orders;
    private Long currentPage;
    private Integer totalCount;
}
