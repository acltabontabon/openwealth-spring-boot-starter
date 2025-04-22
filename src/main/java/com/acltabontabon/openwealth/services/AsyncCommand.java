package com.acltabontabon.openwealth.services;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import org.springframework.core.task.TaskExecutor;

public abstract class AsyncCommand<T> {

    protected void executeAsync(Consumer<T> success, Consumer<Throwable> error) {
        CompletableFuture.supplyAsync(this::execute, asyncExecutor())
            .thenAcceptAsync(success)
            .exceptionally(ex -> {
                error.accept(ex);
                return null;
            });
    }

    protected abstract T execute();

    protected abstract TaskExecutor asyncExecutor();
}
