package de.juliushetzel.collectionx.throwable;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ThrowablesX {

    public static <T> Predicate<T> propagatePredicate(ThrowingPredicate<T> predicate) {
        return (t) -> {
            try {
                return predicate.test(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T, E extends RuntimeException> Predicate<T> propagatePredicate(Function<Exception, E> runtimeException, ThrowingPredicate<T> predicate) {
        return (t) -> {
            try {
                return predicate.test(t);
            } catch (Exception e) {
                throw runtimeException.apply(e);
            }
        };
    }

    public static <T> Consumer<T> propagateConsumer(ThrowingConsumer<T> consumer) {
        return (t) -> {
            try {
                consumer.accept(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T, E extends RuntimeException> Consumer<T> propagateConsumer(Function<Exception, E> runtimeException, ThrowingConsumer<T> consumer) {
        return (t) -> {
            try {
                consumer.accept(t);
            } catch (Exception e) {
                throw runtimeException.apply(e);
            }
        };
    }

    public static <T, R, E extends RuntimeException> Function<T, R> propagateFunction(Function<Exception, E> runtimeException, ThrowingFunction<T, R> function) {
        return (t) -> {
            try {
                return function.apply(t);
            } catch (Exception e) {
                throw runtimeException.apply(e);
            }
        };
    }
}
