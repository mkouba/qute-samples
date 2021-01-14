package org.acme.qute.data;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private final BigDecimal price;
    private final String name;

    public Item(BigDecimal price, String name) {
        this.price = Objects.requireNonNull(price);
        this.name = Objects.requireNonNull(name);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item [price=" + price + ", name=" + name + "]";
    }
    
}