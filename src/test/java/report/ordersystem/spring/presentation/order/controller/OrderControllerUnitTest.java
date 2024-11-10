package report.ordersystem.spring.presentation.order.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import report.ordersystem.spring.common.order.type.OrderStatus;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.domain.order.service.OrderService;

@WebMvcTest(OrderController.class)
class OrderControllerUnitTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    OrderService orderService;

    @Test
    @DisplayName("주문 상세 조회 요청 실패 한다.")
    void positiveGetOrderDetail() throws Exception {
        // given
        long orderId = -1;

        // when // then
        mockMvc.perform(
                   get(String.format("/orders/%d", orderId)))
               .andDo(print())
               .andExpectAll(
                   status().isBadRequest(),
                   jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                   jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()),
                   jsonPath("$.message").value("getOrderDetail.orderId: orderId는 0보다 커야 합니다.")
               );
    }

    @Test
    @DisplayName("주문 상세 조회 요청에 성공한다.")
    void getOrderDetail() throws Exception {
        // given
        long orderId = 1;
        LocalDateTime orderDate = LocalDateTime.now();
        long userId = 1;

        OrderInfo orderInfo = createOrderInfo(orderId, orderDate, userId);
        given(orderService.getOrderDetailInfo(any())).willReturn(orderInfo);

        // when // then
        mockMvc.perform(
                   get(String.format("/orders/%d", orderId)))
               .andDo(print())
               .andExpectAll(
                   status().isOk(),
                   jsonPath("$.code").value(HttpStatus.OK.value()),
                   jsonPath("$.status").value(HttpStatus.OK.name()),
                   jsonPath("$.message").value(HttpStatus.OK.name()),
                   jsonPath("$.data.id").value(orderId),
                   jsonPath("$.data.status").value(OrderStatus.INIT.name()),
                   jsonPath("$.data.orderDate").value(orderDate.toString()),
                   jsonPath("$.data.userId").value(userId)
               );
    }

    private OrderInfo createOrderInfo(long orderId, LocalDateTime orderDate, long userId) {
        return OrderInfo.builder()
                        .id(orderId)
                        .status(OrderStatus.INIT)
                        .orderDate(orderDate)
                        .userId(userId)
                        .build();
    }
}