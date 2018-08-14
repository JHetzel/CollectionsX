package de.juliushetzel.collectionx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CollectionsX {

    private CollectionsX() {}

    public static String toString(Collection<?> collection) {
        StringBuilder result = new StringBuilder("[");
        for (Object object : collection) {
            result.append(object.toString())
                    .append(",");
        }
        return result.deleteCharAt(result.length() - 1)
                .append("]")
                .toString();
    }

    public static <E> List<E> toList(Iterable<E> iterable) {
        if(iterable instanceof List) {
            return (List<E>) iterable;
        }
        ArrayList<E> list = new ArrayList<E>();
        if(iterable != null) {
            for(E e: iterable) {
                list.add(e);
            }
        }
        return list;
    }

}
