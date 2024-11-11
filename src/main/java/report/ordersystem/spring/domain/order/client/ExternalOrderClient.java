package report.ordersystem.spring.domain.order.client;

import report.ordersystem.spring.domain.order.client.dto.request.ExternalOrderSend;

public interface ExternalOrderClient {
    void sendOrderData(ExternalOrderSend sendData);
}
