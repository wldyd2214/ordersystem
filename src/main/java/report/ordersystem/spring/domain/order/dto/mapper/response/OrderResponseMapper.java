package report.ordersystem.spring.domain.order.dto.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.infrastructure.order.entity.OrderEntity;

@Mapper
public interface OrderResponseMapper {
    OrderResponseMapper INSTANCE = Mappers.getMapper(OrderResponseMapper.class);

    OrderInfo toOrderDetailInfo(OrderEntity entity);
}
