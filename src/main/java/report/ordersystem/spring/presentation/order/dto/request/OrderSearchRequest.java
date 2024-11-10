package report.ordersystem.spring.presentation.order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearchRequest {
    @Min(1)
    private Long count;
    @Min(1)
    private Long page;
}
