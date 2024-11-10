package report.ordersystem.spring.common.order.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {
    INIT("생성"),
    ORDER_PROGRESS("처리중"),
    SHIPPING_PROGRESS("배송중"),
    CANCELED("취소"),
    COMPLETED("완료");

    private final String desc;
}
