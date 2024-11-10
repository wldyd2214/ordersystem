package report.ordersystem.spring.infrastructure.order.client;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import report.ordersystem.spring.domain.order.client.ExternalOrderClient;
import report.ordersystem.spring.domain.order.dto.OrderInfo;
import report.ordersystem.spring.presentation.exception.CustomNetworkException;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExternalOrderClientImpl implements ExternalOrderClient {

    private final RestTemplate restTemplate;

    // 외부 시스템의 엔드포인트 URL
    private static final String EXTERNAL_ORDER_API_URL = "http://localhost:8080/orders";

    @Override
    public void sendOrderData(List<OrderInfo> orderInfos) {

        String reqIds = orderInfos.stream()
                                  .map(orderInfo -> String.valueOf(orderInfo.getId()))
                                  .collect(Collectors.joining(", "));

        try {
            // 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // 요청 바디 설정
            HttpEntity<List<OrderInfo>> requestEntity = new HttpEntity<>(orderInfos, headers);

            // 외부 시스템으로 요청 전송
            ResponseEntity<String> response = restTemplate.exchange(
                EXTERNAL_ORDER_API_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
            );

            // 응답 처리 (필요 시)
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("orderIds: {} 주문 데이터 전송 성공", reqIds);
            } else {
                log.error("orderIds: {} 주문 데이터 전송 실패: {}", reqIds, response.getStatusCode());
                throw new IllegalArgumentException("주문 데이터 전송 실패: " + response.getStatusCode());
            }
        } catch (Exception e) {
            // 예외 처리 (네트워크 문제 또는 외부 시스템 에러 등)
            log.error("orderIds: {} 주문 데이터 전송 실패: {}", reqIds, e.getMessage());
            throw new CustomNetworkException("주문 데이터 전송 실패: ", e);
        }
    }
}
