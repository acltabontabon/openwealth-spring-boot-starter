package com.acltabontabon.openwealth.services;

import java.util.function.Consumer;
import lombok.Getter;

public abstract class DeleteCommand extends AsyncCommand<Void>  {

    public void submit() {
        execute();
    }

    public void submitAsync(Consumer<Void> success, Consumer<Throwable> error) {
        executeAsync(success, error);
    }
}
