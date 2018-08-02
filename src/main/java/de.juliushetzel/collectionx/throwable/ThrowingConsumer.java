package de.juliushetzel.collectionx.throwable;

import java.util.Objects;

@FunctionalInterface
public interface ThrowingConsumer<T> {

    void accept(T t) throws Exception;

    default ThrowingConsumer<T> andThen(ThrowingConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}
