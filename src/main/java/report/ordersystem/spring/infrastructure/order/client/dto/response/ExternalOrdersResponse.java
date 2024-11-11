package report.ordersystem.spring.infrastructure.order.client.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExternalOrdersResponse {
    private List<ExternalOrderResponse> orders;
}
