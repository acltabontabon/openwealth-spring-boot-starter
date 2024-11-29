package com.acltabontabon.openwealth.services;

import java.util.function.Consumer;

public abstract class CreateAsyncCommand<T> extends AsyncCommand<T> {

    public void submitAsync(Consumer<T> success, Consumer<Throwable> error) {
        executeAsync(success, error);
    }
}
