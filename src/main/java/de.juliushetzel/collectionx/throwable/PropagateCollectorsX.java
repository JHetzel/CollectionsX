package de.juliushetzel.collectionx.throwable;

import de.juliushetzel.collectionx.CollectorsX;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public final class PropagateCollectorsX {

    private PropagateCollectorsX() {}

    public static <T, C extends Collection<T>, R, E extends RuntimeException> Collector<T, C, R> mapCollector(Function<Exception, E> runtimeException, ThrowingFunction<C, R> mapper, Supplier<C> supplier) {
        return CollectorsX.mapCollector(ThrowablesX.propagateFunction(runtimeException, mapper), supplier);
    }

    public static <T, C extends Collection<T>, R> Collector<T, C, R> mapCollector(ThrowingFunction<C, R> mapper, Supplier<C> supplier) {
        return mapCollector(RuntimeException::new, mapper, supplier);
    }

    public static <T, R, E extends RuntimeException> Collector<T, List<T>, R> mapListCollector(Function<Exception, E> runtimeException, ThrowingFunction<List<T>, R> mapper) {
        return mapCollector(runtimeException, mapper, ArrayList::new);
    }

    public static <T, R> Collector<T, List<T>, R> mapListCollector(ThrowingFunction<List<T>, R> mapper) {
        return mapCollector(RuntimeException::new, mapper, ArrayList::new);
    }

    public static <T, C extends Collection<T>, E extends RuntimeException> Collector<T, C, Void> consumeCollector(Function<Exception, E> runtimeException, ThrowingConsumer<C> consumer, Supplier<C> supplier) {
        return CollectorsX.consumeCollector(ThrowablesX.propagateConsumer(runtimeException, consumer), supplier);
    }

    public static <T, C extends Collection<T>> Collector<T, C, Void> consumeCollector(ThrowingConsumer<C> consumer, Supplier<C> supplier) {
        return consumeCollector(RuntimeException::new, consumer, supplier);
    }

    public static <T, E extends RuntimeException> Collector<T, List<T>, Void> consumeListCollector(Function<Exception, E> runtimeException, ThrowingConsumer<List<T>> consumer) {
        return consumeCollector(runtimeException, consumer, ArrayList::new);
    }

    public static <T> Collector<T, List<T>, Void> consumeListCollector(ThrowingConsumer<List<T>> consumer) {
        return consumeCollector(RuntimeException::new, consumer, ArrayList::new);
    }
}
