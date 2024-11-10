package report.ordersystem.spring.presentation.temp.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import report.ordersystem.spring.domain.order.service.OrderService;
import report.ordersystem.spring.presentation.common.ApiResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/temp")
public class TempController {
    private final OrderService orderService;

    @Operation(
        summary = "주문 정보 저장 API (테스트용)",
        description = "주문 정보를 저장하는 API 입니다. (테스트용)"
    )
    @PostMapping("/orders")
    public ApiResponse<String> postOrders() {
        orderService.addOrderInfo();
        return ApiResponse.ok("요청성공");
    }
}
