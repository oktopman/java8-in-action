package me.oktop.java8inaction.modernjava.product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DiscountProduct extends Product {

    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        list.forEach(e -> result.add(function.apply(e)));
        return result;
    }
}
