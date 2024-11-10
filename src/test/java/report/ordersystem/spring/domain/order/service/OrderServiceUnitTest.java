package report.ordersystem.spring.domain.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import report.ordersystem.spring.common.order.type.OrderStatus;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.domain.order.repository.OrderRepository;
import report.ordersystem.spring.infrastructure.order.entity.OrderEntity;

@ExtendWith(MockitoExtension.class)
class OrderServiceUnitTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    @Test
    @DisplayName("주문 정보가 없는 경우 예외가 발생한다.")
    void getOrderDetailInfoOrElseThrow() {
        // given
        long orderId = 1;

        given(orderRepository.selectOneByOrderId(orderId)).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> orderService.getOrderDetailInfo(orderId))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("주문 정보 미존재");
    }

    @Test
    @DisplayName("주문 정보 조회에 성공한다.")
    void getOrderDetailInfo() {
        // given
        long orderId = 1;
        long userId = 1;
        LocalDateTime orderDate = LocalDateTime.now();
        OrderEntity entity = createOrderEntity(orderId, orderDate, userId);

        given(orderRepository.selectOneByOrderId(orderId)).willReturn(Optional.of(entity));

        // when, then
        OrderInfo orderInfo = orderService.getOrderDetailInfo(orderId);

        // then
        assertThat(orderInfo).isNotNull();
        assertThat(orderInfo).extracting("id", "status", "orderDate", "userId")
                             .contains(orderId, OrderStatus.INIT, orderDate, userId);
    }

    private OrderEntity createOrderEntity(long orderId, LocalDateTime orderDate, long userId) {
        return new OrderEntity(orderId, OrderStatus.INIT, orderDate, userId);
    }
}