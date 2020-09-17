package me.oktop.java8inaction.modernjava.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.oktop.java8inaction.modernjava.product.Product;

import java.util.List;

@AllArgsConstructor
@Getter
public class Order {
    private Long id;
    private String orderNumber;
    private List<OrderItem> items;

    public Integer totalPrice() {
        return Product.total(items, item -> item.getItemTotal());
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
