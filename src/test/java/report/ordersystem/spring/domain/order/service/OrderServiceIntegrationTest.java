package report.ordersystem.spring.domain.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import report.ordersystem.spring.common.order.type.OrderStatus;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.infrastructure.order.OrderRepositoryImpl;
import report.ordersystem.spring.infrastructure.order.entity.OrderEntity;

@SpringBootTest
class OrderServiceIntegrationTest {

    @Autowired
    OrderRepositoryImpl orderRepository;

    @Autowired
    OrderService orderService;

    @Test
    @DisplayName("주문 정보가 없는 경우 예외가 발생한다.")
    void getOrderDetailInfoOrElseThrow() {
        // given
        long orderId = 9999999;

        // when, then
        assertThatThrownBy(() -> orderService.getOrderDetailInfo(orderId))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("주문 정보 미존재");
    }

    @Test
    @DisplayName("주문 정보 조회에 성공한다.")
    void getOrderDetailInfo() {
        // given
        OrderStatus status = OrderStatus.INIT;
        LocalDateTime orderDate = LocalDateTime.now();
        long userId = 1;
        String userName = "사용자";

        OrderEntity entity = orderRepository.insert(status, orderDate, userId, userName);

        OrderInfo orderInfo = orderService.getOrderDetailInfo(entity.id());

        // when, then
        assertThat(orderInfo).isNotNull();
        assertThat(orderInfo).extracting("id", "status", "orderDate", "userId", "userName")
                             .contains(entity.id(), status, orderDate, userId, userName);
    }
}