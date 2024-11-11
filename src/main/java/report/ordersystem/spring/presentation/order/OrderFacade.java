package report.ordersystem.spring.presentation.order;

import java.util.List;
import report.ordersystem.spring.domain.order.dto.OrderInfo;

public interface OrderFacade {
    void processOrderInfoExport(List<Long> orderIds);

    void processOrderInfoImport();
}
