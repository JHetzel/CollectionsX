package de.juliushetzel.collectionx.throwable;

import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
public interface ThrowingPredicate<T> {

    boolean test(T t) throws Exception;


    default ThrowingPredicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }


    default ThrowingPredicate<T> negate() {
        return (t) -> !test(t);
    }


    default ThrowingPredicate<T> or(ThrowingPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    static <T> ThrowingPredicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : targetRef::equals;
    }
}
