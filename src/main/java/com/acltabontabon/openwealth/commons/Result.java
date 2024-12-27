package com.acltabontabon.openwealth.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorCode;

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Result(boolean success, String message, String errorCode) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, "Operation successful", data);
    }

    public static <T> Result<T> failure(String message, String errorCode) {
        return new Result<>(false, message, errorCode);
    }
}
