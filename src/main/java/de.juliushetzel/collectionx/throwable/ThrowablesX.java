package de.juliushetzel.collectionx.throwable;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ThrowablesX {

    public static <T> Predicate<T> propagate(ThrowingPredicate<T> predicate) {
        return (t) -> {
            try {
                return predicate.test(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> Consumer<T> propagate(ThrowingConsumer<T> consumer) {
        return (t) -> {
            try {
                consumer.accept(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T, R> Function<T, R> propagate(ThrowingFunction<T, R> function) {
        return (t) -> {
            try {
                return function.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
