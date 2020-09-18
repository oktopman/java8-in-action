package me.oktop.java8inaction.modernjava;

import me.oktop.java8inaction.modernjava.order.Order;
import me.oktop.java8inaction.modernjava.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class StreamStudyTest {

    @Test
    @DisplayName("stream price가 2000 이상인 것 리스트로 변환 테스트")
    void stream_greater_than_price_2000_to_list_test() {
        //given
        final List<Product> products = Arrays.asList(
                Product.builder().id(1L).name("A").price(1000).build(),
                Product.builder().id(2L).name("B").price(2000).build(),
                Product.builder().id(3L).name("C").price(3000).build(),
                Product.builder().id(4L).name("D").price(4000).build()
        );
        //when
        List<Product> productList = products.stream()
                .filter(product -> product.getPrice().compareTo(2000) >= 0)
                .collect(toList());
        //then
        assertThat(productList.size(), is(3));
    }

    @Test
    @DisplayName("주문의 total price 구하기 테스트")
    void order_get_total_price_test() {
        //given
        Order order = new Order(
                1L,
                "order-1",
                Arrays.asList(
                        new Order.OrderItem(
                                1L,
                                Product.builder().id(1L).name("A").price(1000).build(),
                                3),
                        new Order.OrderItem(
                                2L,
                                Product.builder().id(2L).name("B").price(2500).build(),
                                2)));

        //when
        int filterSum = order.getItems().stream()
                .filter(product -> product.getProduct().getPrice() > 1000)
                .mapToInt(p -> p.getProduct().getPrice() * p.getQuantity())
                .sum();

        int sum = order.getItems().stream()
                .mapToInt(p -> p.getProduct().getPrice() * p.getQuantity())
                .sum();

        //then
        assertThat(filterSum, is(2500 * 2));
        assertThat(sum, is((1000 * 3) + (2500 * 2)));

    }

    @Test
    @DisplayName("stream 예제 테스트")
    void stream_example_test() {
        final List<Product> products = Arrays.asList(
                Product.builder().id(1L).name("A").price(1000).build(),
                Product.builder().id(2L).name("B").price(2000).build(),
                Product.builder().id(3L).name("C").price(3000).build(),
                Product.builder().id(4L).name("D").price(4000).build()
        );

        System.out.println(
                products.stream()
                        .filter(product -> product.getPrice().compareTo(2000) >= 0)
                        .map(product -> product.toString())
                        .collect(joining())
        );

        System.out.println(
                products.stream()
                        .mapToInt(p -> p.getPrice())
                        .sum()
        );
        System.out.println(
                products.stream()
                        .filter(product -> product.getPrice().compareTo(2000) >= 0)
                        .count()
        );
    }

}