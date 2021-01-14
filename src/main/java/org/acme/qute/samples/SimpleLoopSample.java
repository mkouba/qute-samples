package org.acme.qute.samples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.CheckedTemplates;
import org.acme.qute.Labels;
import org.acme.qute.Sample;
import org.acme.qute.data.Item;

import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

@Singleton
public class SimpleLoopSample implements Sample {

    @Override
    public String getTitle() {
        return "Simple Loop";
    }

    @Override
    public String getDescription() {
        return "The loop section makes it possible to iterate over an instance of <code>Iterable</code>, <code>Map</code>'s entry set, <code>Stream</code> and an <code>Integer</code>.  It has two flavors. In this sample, we use the <code>for</code>-style loop.";
    }

    @Override
    public String getSnippetName() {
        return "simpleLoop.html";
    }

    @Override
    public Iterable<String> getLabels() {
        return List.of(Labels.EXPRESSIONS, Labels.LOOP);
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(new BigDecimal(10), "Apple"));
        items.add(new Item(new BigDecimal(20), "Pear"));
        items.add(new Item(new BigDecimal(30), "Orange"));
        return CheckedTemplates.simpleLoop(items);
    }

}
