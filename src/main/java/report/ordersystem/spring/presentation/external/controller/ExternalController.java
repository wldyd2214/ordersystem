package report.ordersystem.spring.presentation.external.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import report.ordersystem.spring.common.order.type.OrderStatus;
import report.ordersystem.spring.presentation.common.ApiResponse;
import report.ordersystem.spring.presentation.external.dto.ExternalOrder;
import report.ordersystem.spring.presentation.external.dto.ExternalOrderList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/external")
public class ExternalController {

    @Operation(
            summary = "주문 정보 저장 API (외부 시스템)",
            description = "내부시스템에서 전송한 주문 정보를 전달 받습니다."
    )
    @PostMapping("/orders/import")
    public ApiResponse<ExternalOrderList> postExternalOrders(
            @RequestBody ExternalOrderList requestDto
    ) {
        return ApiResponse.ok(requestDto);
    }

    @Operation(
            summary = "주문 목록 조회 API (외부 시스템)",
            description = "외부시스템의 주문 정보를 전달 합니다."
    )
    @GetMapping("/orders/export")
    public ApiResponse<ExternalOrderList> getExternalOrders() {
        log.info("외부서비스 주문 목록 조회 API 요청 성공!");
        List<ExternalOrder> orders = createExternalOrders(15);
        return ApiResponse.ok(ExternalOrderList.builder().orders(orders).build());
    }

    private List<ExternalOrder> createExternalOrders(int count) {

        List<ExternalOrder> orders = new ArrayList<>();

        for(int i = 1; i <= count; i++) {
            orders.add(createExternalOrder(i, i));
        }

        return orders.stream()
                     .sorted(Comparator.comparing(ExternalOrder::getOrderId).reversed())
                     .collect(Collectors.toList());
    }

    private ExternalOrder createExternalOrder(long orderId, long userId) {
        return ExternalOrder.builder()
                            .orderId(orderId)
                            .orderStatus(OrderStatus.INIT)
                            .orderDate(LocalDateTime.now())
                            .userId(userId)
                            .userName("사용자" + userId)
                            .build();
    }
}
