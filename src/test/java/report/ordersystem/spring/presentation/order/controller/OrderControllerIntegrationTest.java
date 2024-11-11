package report.ordersystem.spring.presentation.order.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import report.ordersystem.spring.common.order.type.OrderStatus;
import report.ordersystem.spring.infrastructure.order.OrderRepositoryImpl;
import report.ordersystem.spring.infrastructure.order.entity.OrderEntity;
import report.ordersystem.spring.presentation.common.ApiResponse;
import report.ordersystem.spring.presentation.order.dto.response.OrderResponse;

@SpringBootTest
class OrderControllerIntegrationTest {
    @Autowired
    OrderController orderController;

    @Autowired
    OrderRepositoryImpl orderRepository;

    @Test
    @DisplayName("주문 상세 조회 요청에 성공한다.")
    void getOrderDetail() {
        // given
        OrderStatus status = OrderStatus.INIT;
        LocalDateTime orderDate = LocalDateTime.now();
        long userId = 1;
        String userName = "사용자";

        OrderEntity entity = orderRepository.insert(status, orderDate, userId, userName);

        // when
        ApiResponse<OrderResponse> response = orderController.getOrderDetail(entity.id());

        // when, then
        assertThat(response).isNotNull();
        assertThat(response.getData()).extracting("id", "status", "orderDate", "userId")
                                      .contains(entity.id(), status, orderDate, userId);
    }
}