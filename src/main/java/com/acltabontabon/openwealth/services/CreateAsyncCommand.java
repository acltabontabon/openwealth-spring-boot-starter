package com.acltabontabon.openwealth.services;

import java.util.function.Consumer;

public interface CreateAsyncCommand<T> extends AsyncCommand<T> {

    default void submitAsync(Consumer<T> success, Consumer<Throwable> error) {
        executeAsync(success, error);
    }
}
