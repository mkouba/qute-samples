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
        return "Simple If";
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        Item item;
        String price = params.get("price");
        if (price != null) {
            item = new Item(new BigDecimal(price), "log");
        } else {
            item = new Item(BigDecimal.TEN, "log");
        }
        return CheckedTemplates.ifSimple(item);
    }

    @Override
    public Iterable<String> getInfos() {
        return List.of(
                "Add the <code>price</code> query param to the URL to override the price, e.g. <code>?price=20</code>.");
    }

}
