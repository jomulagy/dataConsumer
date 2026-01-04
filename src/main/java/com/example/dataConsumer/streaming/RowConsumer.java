package com.example.dataConsumer.streaming;

@FunctionalInterface
public interface RowConsumer<T> {
    void accept(T row) throws Exception;

    static <T> RowConsumer<T> noop() {
        return row -> {
        };
    }
}
