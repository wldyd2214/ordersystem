package report.ordersystem.spring.infrastructure.order.client.config;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // connectionTimeout과 readTimeout을 설정하여 RestTemplate 생성
        return builder
            .setConnectTimeout(Duration.ofMillis(5000))  // 연결 시간 초과 5초 설정
            .setReadTimeout(Duration.ofMillis(5000))     // 읽기 시간 초과 5초 설정
            .build();
    }
}
