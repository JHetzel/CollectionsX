package de.juliushetzel.collectionx.function;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public final class FunctionsX {

    public static <T> Function<T, Void> asFunction(Consumer<T> consumer) {
        return item -> {
            consumer.accept(item);
            return null;
        };
    }
}
