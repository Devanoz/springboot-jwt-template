package com.devano.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GlobalResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> GlobalResponse<T> empty() {
        return success(null,null);
    }

    public static <T> GlobalResponse<T> success(String message,T data) {
        return GlobalResponse.<T>builder()
                .message(message)
                .data(data)
                .success(true)
                .build();
    }

    public static <T> GlobalResponse<T> error(String message) {
        return GlobalResponse.<T>builder()
                .message(message)
                .success(false)
                .build();
    }
}