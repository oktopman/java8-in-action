package me.oktop.java8inaction.modernjava.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@ToString
@NoArgsConstructor
@Getter
public class Product {
    private Long id;
    private String name;
    private Integer price;

    @Builder
    public Product(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // filtering 하고 싶은 금액이 계속 변경 될 수 있기 때문에 원하는 필터링기준을 Predicate 로 넘김
    public static <T> List<T> filter(List<T> products, Predicate<? super T> predicate) {
        final List<T> result = new ArrayList<>();
        products.forEach(e -> {
            if (predicate.test(e)) {
                result.add(e);
            }
        });
        return result;
    }

    public static <T> Integer total(List<T> list, Function<T, Integer> function) {
        int total = 0;
        for (T t : list) {
            total += function.apply(t);
        }
        return total;
    }

}
