package org.acme.qute.usertags;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.acme.qute.CheckedTemplates;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.vertx.web.Route;

public class ItemRoutes {

    @ConfigProperty(name = "items.discount", defaultValue = "10")
    int discount;

    @ConfigProperty(name = "items.price-limit", defaultValue = "15")
    int discountPriceLimit;

    @Route(produces = "text/html")
    public String items() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(new BigDecimal(10), "Apple"));
        items.add(new Item(new BigDecimal(16), "Pear"));
        items.add(new Item(new BigDecimal(30), "Orange"));
        return CheckedTemplates.items(items).data("discount", (discount < 0 || discount > 100) ? 10 : discount)
                .data("discountLimit", discountPriceLimit).render();
    }

}