package report.ordersystem.spring.presentation.order.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderListResponse {
    private List<OrderResponse> orders;
    private Long currentPage;
    private Integer totalCount;
}
