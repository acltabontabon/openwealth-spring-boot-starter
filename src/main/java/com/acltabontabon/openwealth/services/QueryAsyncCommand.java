package com.acltabontabon.openwealth.services;

import java.util.function.Consumer;

public interface QueryAsyncCommand<T> extends AsyncCommand<T> {

    default void fetchAsync(Consumer<T> success, Consumer<Throwable> error) {
        executeAsync(success, error);
    }
}
