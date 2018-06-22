package de.juliushetzel.collectionx;

import java.util.Collection;

final class BinaryOperators {

    static <T, C extends Collection<T>> C collectionBinaryOperator(C left, C right) {
        left.addAll(right);
        return left;
    }
}
