package report.ordersystem.spring.presentation.order;

import java.util.List;

public interface OrderFacade {
    void processOrderInfoExport(List<Long> orderIds);
}
