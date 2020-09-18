package me.oktop.java8inaction.modernjava.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.oktop.java8inaction.modernjava.product.Product;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Getter
public class Order {
    private Long id;
    private String orderNumber;
    private List<OrderItem> items;

    public Integer totalPrice() {
        final AtomicInteger total = new AtomicInteger();
        items.forEach(item -> total.getAndAdd(item.getItemTotal()));
        return total.get();
//        return Product.total(items, item -> item.getItemTotal());
    }

    @Getter
    @AllArgsConstructor
    public static class OrderItem {
        private Long id;
        private Product product;
        private Integer quantity;

        public Integer getItemTotal() {
            return product.getPrice() * quantity;
        }

    }
}
