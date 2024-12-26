package com.acltabontabon.openwealth.services;

import java.util.function.Consumer;

public abstract class CreateCommand<T> extends AsyncCommand<T> {

    public T submit() {
        return execute();
    }

    public void submitAsync(Consumer<T> success, Consumer<Throwable> error) {
        executeAsync(success, error);
    }
}
