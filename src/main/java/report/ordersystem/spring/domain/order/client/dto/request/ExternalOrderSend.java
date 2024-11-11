package report.ordersystem.spring.domain.order.client.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ExternalOrderSend {
    private List<ExternalOrderData> orders;
}
