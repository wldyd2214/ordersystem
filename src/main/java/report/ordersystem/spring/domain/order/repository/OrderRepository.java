package report.ordersystem.spring.domain.order.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import report.ordersystem.spring.common.order.type.OrderStatus;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.infrastructure.order.entity.OrderEntity;

public interface OrderRepository {

    OrderEntity insert(OrderStatus status, LocalDateTime orderDate, Long userId, String userName);

    List<OrderEntity> selectAllByOrderSearch(Long count, Long page);

    Integer selectTotalCount();

    Optional<OrderEntity> selectOneByOrderId(long orderId);

    List<OrderEntity> selectAllByOrderIds(List<Long> orderIds);

    void saveAll(List<OrderInfo> orderInfos);
}
