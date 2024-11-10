package report.ordersystem.spring.presentation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomNetworkException extends RuntimeException {

    public CustomNetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
