package me.oktop.java8inaction.modernjava.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ProductTest {

    @Test
    @DisplayName("특정금액 이상되는 상품 출력 테스트")
    void greater_than_price_product_print_test() {
        //given
        final List<Product> products = Arrays.asList(
                Product.builder().id(1L).name("A").price(1000).build(),
                Product.builder().id(2L).name("B").price(2000).build(),
                Product.builder().id(3L).name("C").price(3000).build(),
                Product.builder().id(4L).name("D").price(4000).build());

        int standard = 1000;
        //when
        List<Product> filteringProducts = Product.filter(products, p -> p.getPrice().compareTo(standard) > 0);
        //then
        assertThat(filteringProducts.size(), is(3));
    }

    @Test
    @DisplayName("특정금액 이 상품을 찾아 1/2 할인 테스트")
    void less_than_product_discount_test() {
        //given
        final List<Product> products = Arrays.asList(
                Product.builder().id(1L).name("A").price(1000).build(),
                Product.builder().id(2L).name("B").price(2500).build(),
                Product.builder().id(3L).name("C").price(2510).build(),
                Product.builder().id(3L).name("D").price(3100).build(),
                Product.builder().id(4L).name("F").price(4000).build());
        int standard = 2500;
        //when
        List<Product> filteringProducts = Product.filter(products,
                p -> p.getPrice().compareTo(standard) > 0);

        List<Product> discountProducts = DiscountProduct.map(filteringProducts,
                p -> DiscountProduct.builder().id(p.getId()).name(p.getName()).price(p.getPrice() / 2).build());
        //then
        assertThat(filteringProducts.size(), is(3));
        assertThat(discountProducts.get(0).getPrice(), is(2510 / 2));
    }

    @Test
    @DisplayName("상품들의 total 가격 구하는 테스트")
    void products_get_total_price_test() {
        //given
        final List<Product> products = Arrays.asList(
                Product.builder().id(1L).name("A").price(1000).build(),
                Product.builder().id(2L).name("B").price(2500).build(),
                Product.builder().id(3L).name("C").price(2510).build(),
                Product.builder().id(3L).name("D").price(3100).build(),
                Product.builder().id(4L).name("F").price(4000).build());
        //when
        Integer total = Product.total(products, product -> product.getPrice());
        //then
        assertThat(total, is(13110));
    }

}