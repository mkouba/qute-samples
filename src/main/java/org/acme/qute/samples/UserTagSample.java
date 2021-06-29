package org.acme.qute.samples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.CheckedTemplates;
import org.acme.qute.Sample;
import org.acme.qute.data.Item;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

@Singleton
public class UserTagSample implements Sample {

    @ConfigProperty(name = "items.discount", defaultValue = "10")
    int discount;

    @ConfigProperty(name = "items.price-limit", defaultValue = "15")
    int discountPriceLimit;

    @Override
    public String getTitle() {
        return "User-defined Tags";
    }

    @Override
    public List<String> getAdditionalSnippetNames() {
        return Collections.singletonList("tags/itemDetail.html");
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(new BigDecimal(10), "Apple"));
        items.add(new Item(new BigDecimal(16), "Pear"));
        items.add(new Item(new BigDecimal(30), "Orange"));
        return CheckedTemplates.userTag(items, (discount < 0 || discount > 100) ? 10 : discount, discountPriceLimit);
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.ADVANCED;
    }

}
