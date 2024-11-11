package report.ordersystem.spring.presentation.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.domain.order.dto.OrderSearchInfo;
import report.ordersystem.spring.domain.order.service.OrderService;
import report.ordersystem.spring.presentation.common.ApiResponse;
import report.ordersystem.spring.presentation.order.OrderFacade;
import report.ordersystem.spring.presentation.order.dto.request.OrderSearchRequest;
import report.ordersystem.spring.presentation.order.dto.response.OrderListResponse;
import report.ordersystem.spring.presentation.order.dto.response.OrderResponse;
import report.ordersystem.spring.presentation.order.dto.response.mapper.OrderResponseMapper;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderFacade orderFacade;
    private final OrderService orderService;

    @Operation(
        summary = "주문 정보 저장 API",
        description = "외부 시스템 요청하여 전달된 주문 정보를 저장하는 API 입니다."
    )
    @PostMapping("")
    public ApiResponse<OrderResponse> postOrders() {
        orderFacade.processOrderInfoImport();
        return ApiResponse.ok(null);
    }

    @Operation(
        summary = "주문 상세 정보 조회 API",
        description = "주문 상세 정보를 제공하는 API 입니다."
    )
    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrderDetail(
        @PathVariable
        @Positive(message = "orderId는 0보다 커야 합니다.")
        Long orderId) {
        OrderInfo orderInfo = orderService.getOrderDetailInfo(orderId);
        return ApiResponse.ok(OrderResponseMapper.INSTANCE.toOrderDetailResponse(orderInfo));
    }

    @Operation(
        summary = "주문 목록 정보 조회 API",
        description = "주문 목록 정보를 제공하는 API 입니다."
    )
    @GetMapping("")
    public ApiResponse<OrderListResponse> getOrders(@Valid @ModelAttribute OrderSearchRequest request) {
        OrderSearchInfo orderInfo = orderService.getOrderInfoList(request.getCount(), request.getPage());
        return ApiResponse.ok(OrderResponseMapper.INSTANCE.toOrderListResponse(orderInfo));
    }

    @Operation(
        summary = "주문 정보 전송 API",
        description = "내부 시스템에 저장된 주문 정보를 외부 시스템으로 전송하는 API 입니다."
    )
    @PostMapping("/{orderIds}/export")
    public ApiResponse<Void> postOrdersExport(
        @PathVariable
        @NotNull
        @Valid
        List<@Min(1) Long> orderIds) {
        orderFacade.processOrderInfoExport(orderIds);
        return ApiResponse.ok(null);
    }
}
