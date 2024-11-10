package report.ordersystem.spring.domain.order.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.infrastructure.order.entity.OrderEntity;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderInfo toOrderDetailInfo(OrderEntity entity);
}
