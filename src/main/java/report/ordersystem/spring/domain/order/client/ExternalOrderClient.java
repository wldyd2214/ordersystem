package report.ordersystem.spring.domain.order.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import report.ordersystem.spring.domain.order.client.dto.request.ExternalOrderSend;
import report.ordersystem.spring.domain.order.dto.OrderInfo;

public interface ExternalOrderClient {
    void sendOrderData(ExternalOrderSend sendData);

    List<OrderInfo> fetchOrdersData();
}
