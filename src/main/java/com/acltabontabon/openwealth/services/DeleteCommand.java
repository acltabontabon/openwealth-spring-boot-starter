package com.acltabontabon.openwealth.services;

import java.util.function.Consumer;
import lombok.Getter;

public abstract class DeleteCommand<T> extends AsyncCommand<T>  {

    public void submit() {
        execute();
    }

    public void submitAsync(Consumer<T> success, Consumer<Throwable> error) {
        executeAsync(success, error);
    }
}
