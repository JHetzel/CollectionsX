package de.juliushetzel.collectionx;

import de.juliushetzel.collectionx.function.FunctionsX;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.Collector;

public final class CollectorsX {

    private CollectorsX() {}

    public static <T, C extends Collection<T>, R> Collector<T, C, R> mapCollector(Function<C, R> mapper, Supplier<C> supplier) {
        return Collector.of(supplier, Collection::add, BinaryOperators::collectionBinaryOperator, mapper);
    }

    public static <T, R> Collector<T, List<T>, R> mapListCollector(Function<List<T>, R> mapper) {
        return mapCollector(mapper, ArrayList::new);
    }

    public static <T, C extends Collection<T>> Collector<T, C, Void> consumeCollector(Consumer<C> consumer, Supplier<C> supplier) {
        return Collector.of(supplier, Collection::add, BinaryOperators::collectionBinaryOperator, FunctionsX.asFunction(consumer));
    }

    public static <T> Collector<T, List<T>, Void> consumeListCollector(Consumer<List<T>> consumer) {
        return consumeCollector(consumer, ArrayList::new);
    }

    public static <T, C extends Collection<T>> Collector<T, C, C> collectInto(Supplier<C> supplier) {
        return Collector.of(supplier, Collection::add, BinaryOperators::collectionBinaryOperator);
    }

    public static Collector<Integer, AtomicInteger, Integer> sumCollect() {
        return Collector.of(AtomicInteger::new, AtomicInteger::addAndGet, (atomicInteger, atomicInteger2) -> {
            atomicInteger.addAndGet(atomicInteger2.get());
            return atomicInteger;
        }, AtomicInteger::get);
    }

}
