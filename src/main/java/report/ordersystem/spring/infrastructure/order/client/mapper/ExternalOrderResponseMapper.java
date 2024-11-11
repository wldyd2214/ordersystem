package report.ordersystem.spring.infrastructure.order.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.infrastructure.order.client.dto.response.ExternalOrderResponse;
import report.ordersystem.spring.infrastructure.order.client.dto.response.ExternalOrdersResponse;

@Mapper
public interface ExternalOrderResponseMapper {
    ExternalOrderResponseMapper INSTANCE = Mappers.getMapper(ExternalOrderResponseMapper.class);

    @Mapping(source = "orderId", target = "id")
    @Mapping(source = "orderStatus", target = "status")
    @Mapping(source = "orderDate", target = "orderDate")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "userName", target = "userName")
    OrderInfo toOrderInfo(ExternalOrderResponse response);
}
