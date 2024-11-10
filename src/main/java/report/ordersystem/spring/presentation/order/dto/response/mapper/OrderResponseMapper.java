package report.ordersystem.spring.presentation.order.dto.response.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.domain.order.dto.OrderSearchInfo;
import report.ordersystem.spring.presentation.order.dto.response.OrderListResponse;
import report.ordersystem.spring.presentation.order.dto.response.OrderResponse;

@Mapper
public interface OrderResponseMapper {
    OrderResponseMapper INSTANCE = Mappers.getMapper(OrderResponseMapper.class);

    OrderResponse toOrderDetailResponse(OrderInfo info);

    OrderListResponse toOrderListResponse(OrderSearchInfo info);
}
