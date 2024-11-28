package com.acltabontabon.openwealth.services;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface AsyncCommand<T> {

    default void executeAsync(Consumer<T> success, Consumer<Throwable> error) {
        CompletableFuture.supplyAsync(this::execute)
            .thenAcceptAsync(success)
            .exceptionally(ex -> {
                error.accept(ex);
                return null;
            });
    }

    T execute();
}
