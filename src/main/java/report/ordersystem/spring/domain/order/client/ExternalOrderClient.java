package report.ordersystem.spring.domain.order.client;

import java.util.List;
import report.ordersystem.spring.domain.order.dto.OrderInfo;

public interface ExternalOrderClient {
    void sendOrderData(List<OrderInfo> orderInfos);
}
