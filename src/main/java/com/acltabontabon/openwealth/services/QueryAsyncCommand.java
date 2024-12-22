package com.acltabontabon.openwealth.services;

import java.util.function.Consumer;

public abstract class QueryAsyncCommand<T> extends AsyncCommand<T> {

    public T fetch() {
        return execute();
    }

    public void fetchAsync(Consumer<T> success, Consumer<Throwable> error) {
        executeAsync(success, error);
    }
}
