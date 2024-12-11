package com.izzuddinm.career_core_backend.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private String status; // "success", "error", "fail"
    private String message; // Descriptive message
    private T data; // Generic type for the payload
    private String timestamp; // Time when the response is generated

    // Factory method for success response
    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>("success", message, data, Instant.now().toString());
    }

    // Factory method for error response
    public static <T> BaseResponse<T> error(String message, T data) {
        return new BaseResponse<>("error", message, data, Instant.now().toString());
    }

    // Factory method for fail response
    public static <T> BaseResponse<T> fail(String message, T data) {
        return new BaseResponse<>("fail", message, data, Instant.now().toString());
    }
}
