package report.ordersystem.spring.application.order;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.domain.order.service.OrderService;
import report.ordersystem.spring.presentation.order.OrderFacade;

@RequiredArgsConstructor
@Service
public class OrderFacadeImpl implements OrderFacade {
    private final OrderService orderService;

    @Override
    public void processOrderInfoExport(List<Long> orderIds) {

        // 1. 주문 정보 조회
        List<OrderInfo> orderInfos = orderService.getOrderInfoByOrderIds(orderIds);

        // 2. 주문 정보 전송
        orderService.sendOrder(orderInfos);
    }
}
