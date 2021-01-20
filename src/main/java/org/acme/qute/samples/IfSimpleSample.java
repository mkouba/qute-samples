package org.acme.qute.samples;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.CheckedTemplates;
import org.acme.qute.Sample;
import org.acme.qute.data.Item;

import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

@Singleton
public class IfSimpleSample implements Sample {

    @Override
    public String getTitle() {
        return "If-Else";
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        String name = "Log";
        String price = params.get("price");
        if (price == null) {
            price = "15";
        }
        Item item = new Item(new BigDecimal(price), name);
        return CheckedTemplates.ifSimple(item);
    }

    @Override
    public Iterable<String> getInfos() {
        return List.of(
                "You can add the <code>price</code> query param to the URL to override the price, e.g. <code>?price=20</code>.");
    }

    @Override
    public int getPriority() {
        return 9;
    }

}
