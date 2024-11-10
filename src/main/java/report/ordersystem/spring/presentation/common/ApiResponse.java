package report.ordersystem.spring.presentation.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    @Schema(description = "응답 코드", example = "200")
    private int code;
    @Schema(description = "HttpStatus", example = "OK")
    private HttpStatus status;
    @Schema(description = "HttpMessage", example = "OK")
    private String message;
    private T data;

    public ApiResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(HttpStatus httpStatus, String message, T data) {
        return new ApiResponse<>(httpStatus, message, data);
    }

    // 펙토리 메서드
    public static <T> ApiResponse<T> of(HttpStatus httpStatus, T data) {
        return of(httpStatus, httpStatus.name(), data);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return of(HttpStatus.OK, HttpStatus.OK.name(), data);
    }
}
