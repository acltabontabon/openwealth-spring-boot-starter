package com.acltabontabon.openwealth.services;

import java.util.function.Consumer;

public abstract class UpdateCommand extends AsyncCommand<Void>  {

    public void submit() {
        execute();
    }

    public void submitAsync(Consumer<Void> success, Consumer<Throwable> error) {
        executeAsync(success, error);
    }
}
