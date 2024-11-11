package report.ordersystem.spring.domain.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import report.ordersystem.spring.common.order.type.OrderStatus;
import report.ordersystem.spring.domain.order.client.ExternalOrderClient;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.domain.order.dto.OrderSearchInfo;
import report.ordersystem.spring.domain.order.dto.mapper.request.OrderRequestMapper;
import report.ordersystem.spring.domain.order.dto.mapper.response.OrderResponseMapper;
import report.ordersystem.spring.domain.order.repository.OrderRepository;
import report.ordersystem.spring.infrastructure.order.entity.OrderEntity;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ExternalOrderClient externalOrderClient;

    public OrderInfo getOrderDetailInfo(Long orderId) {
        OrderEntity entity =
            orderRepository.selectOneByOrderId(orderId)
                           .orElseThrow(() -> new IllegalArgumentException("주문 정보 미존재"));

        return OrderResponseMapper.INSTANCE.toOrderDetailInfo(entity);
    }

    public OrderSearchInfo getOrderInfoList(Long count, Long page) {

        List<OrderEntity> entities =
            orderRepository.selectAllByOrderSearch(count, page);

        Integer totalCount = orderRepository.selectTotalCount();

        return OrderSearchInfo.builder()
                              .orders(entities)
                              .currentPage(page)
                              .totalCount(totalCount)
                              .build();
    }

    public List<OrderInfo> getOrderInfoByOrderIds(List<Long> orderIds) {
        List<OrderEntity> entities = orderRepository.selectAllByOrderIds(orderIds);

        return entities.stream()
                       .map(entity -> OrderResponseMapper.INSTANCE.toOrderDetailInfo(entity))
                       .collect(Collectors.toList());
    }

    public void sendOrder(List<OrderInfo> orderInfos) {

        if (orderInfos.size() == 0) {
            return;
        }

        ;

        externalOrderClient.sendOrderData(OrderRequestMapper.INSTANCE.toExternalOrderSend(orderInfos));
    }

    public void addOrderInfo() {
        orderRepository.insert(OrderStatus.INIT, LocalDateTime.now(), 1L);
    }
}
