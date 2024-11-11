package report.ordersystem.spring.domain.order.dto.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import report.ordersystem.spring.domain.order.client.dto.request.ExternalOrderData;
import report.ordersystem.spring.domain.order.client.dto.request.ExternalOrderSend;
import report.ordersystem.spring.domain.order.dto.OrderInfo;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface OrderRequestMapper {
    OrderRequestMapper INSTANCE = Mappers.getMapper(OrderRequestMapper.class);

    // OrderInfo 객체를 ExternalOrderData로 매핑하는 메서드
    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "status", target = "orderStatus")
    @Mapping(source = "orderDate", target = "orderDate")
    @Mapping(source = "userId", target = "userId")
    ExternalOrderData toExternalOrderData(OrderInfo orderInfo);

    // List<OrderInfo> -> ExternalOrderSend로 매핑하는 메서드
    default ExternalOrderSend toExternalOrderSend(List<OrderInfo> orderInfos) {
        List<ExternalOrderData> externalOrders = orderInfos.stream()
                                                           .map(this::toExternalOrderData)
                                                           .collect(Collectors.toList());
        return ExternalOrderSend.builder()
                                .orders(externalOrders)
                                .build();
    }
}
