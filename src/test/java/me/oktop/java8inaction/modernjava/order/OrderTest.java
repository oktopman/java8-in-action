package me.oktop.java8inaction.modernjava.order;

import me.oktop.java8inaction.modernjava.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class OrderTest {

    @Test
    @DisplayName("주문의 total price 구하기 테스트")
    void order_get_total_price_test() {
        //given
        Order order = new Order(
                1L,
                "order-1",
                Arrays.asList(new Order.OrderItem(
                        1L, Product.builder().id(1L).name("A").price(1000).build(), 3),
                        new Order.OrderItem(
                                2L, Product.builder().id(2L).name("B").price(2500).build(), 2)));

        //when
        Integer totalPrice = order.totalPrice();
        //then
        assertThat(totalPrice, is(8000));
    }

}