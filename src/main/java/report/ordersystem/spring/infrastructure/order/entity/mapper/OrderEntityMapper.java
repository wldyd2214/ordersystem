package report.ordersystem.spring.infrastructure.order.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.infrastructure.order.entity.OrderEntity;

@Mapper
public interface OrderEntityMapper {
    OrderEntityMapper INSTANCE = Mappers.getMapper(OrderEntityMapper.class);

    OrderEntity toOrderEntity(OrderInfo orderInfo);
}
