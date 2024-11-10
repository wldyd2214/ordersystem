package report.ordersystem.spring.infrastructure.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import report.ordersystem.spring.common.order.type.OrderStatus;
import report.ordersystem.spring.domain.order.repository.OrderRepository;
import report.ordersystem.spring.infrastructure.order.entity.OrderEntity;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    private final List<OrderEntity> table = new ArrayList<>();
    private long cursor = 1;

    @Override
    public OrderEntity insert(OrderStatus status, LocalDateTime orderDate, Long userId) {
        OrderEntity pointHistory = new OrderEntity(cursor++, status, orderDate, userId);
        table.add(pointHistory);
        return pointHistory;
    }

    @Override
    public List<OrderEntity> selectAllByOrderSearch(Long count, Long page) {
        // 1. id를 기준으로 내림차순 정렬
        table.sort(Comparator.comparing(OrderEntity::id).reversed());

        // 2. 페이지 계산: 시작 인덱스와 끝 인덱스를 구해 리스트의 서브리스트 추출
        int fromIndex = (page.intValue() - 1) * count.intValue();
        int toIndex = Math.min(fromIndex + count.intValue(), table.size());

        // 3. 페이지 범위가 유효하면 해당 구간을 리턴, 유효하지 않으면 빈 리스트를 리턴
        if (fromIndex >= table.size()) {
            return new ArrayList<>();  // 페이지 범위를 벗어났을 경우 빈 리스트 반환
        }

        return table.subList(fromIndex, toIndex);
    }

    @Override
    public Integer selectTotalCount() {
        return table.size();
    }

    @Override
    public Optional<OrderEntity> selectOneByOrderId(long orderId) {
        try {
            return Optional.of(table.stream().filter(order -> order.id() == orderId).findFirst().get());
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<OrderEntity> selectAllByOrderIds(List<Long> orderIds) {
        return table.stream()
                    .filter(order -> orderIds.contains(order.id()))
                    .collect(Collectors.toList());
    }
}
