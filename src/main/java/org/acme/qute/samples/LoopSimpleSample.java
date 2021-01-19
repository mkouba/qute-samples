package org.acme.qute.samples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.CheckedTemplates;
import org.acme.qute.Sample;
import org.acme.qute.data.Item;

import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

@Singleton
public class LoopSimpleSample implements Sample {

    @Override
    public String getTitle() {
        return "For-Each Loop";
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(new BigDecimal(10), "Apple"));
        items.add(new Item(new BigDecimal(20), "Pear"));
        items.add(new Item(new BigDecimal(30), "Orange"));
        return CheckedTemplates.loopSimple(items);
    }

}
