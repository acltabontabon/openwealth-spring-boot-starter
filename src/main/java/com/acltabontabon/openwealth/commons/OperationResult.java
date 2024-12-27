package com.acltabontabon.openwealth.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperationResult<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorCode;

    public OperationResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public OperationResult(boolean success, String message, String errorCode) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
    }

    public static <T> OperationResult<T> success(T data) {
        return new OperationResult<>(true, "Operation successful", data);
    }

    public static <T> OperationResult<T> failure(String message, String errorCode) {
        return new OperationResult<>(false, message, errorCode);
    }
}
