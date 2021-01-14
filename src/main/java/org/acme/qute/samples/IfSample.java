package org.acme.qute.samples;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.CheckedTemplates;
import org.acme.qute.Labels;
import org.acme.qute.Sample;
import org.acme.qute.data.Item;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.ResourcePath;
import io.vertx.core.MultiMap;

@Singleton
public class IfSample implements Sample {

    @Override
    public String getTitle() {
        return "Simple If";
    }

    @Override
    public String getDescription() {
        return "The <code>if</code> section represents a basic control flow section. The simplest possible version accepts a single parameter and renders the content if the condition is not <code>falsy</code>."
                + " However, you can also use operators: <code>{#if item.age > 10}</code>, multiple conditions: <code>{#if item.age > 10 && item.price > 500}</code> and precedence rules can be overridden by parentheses: <code>{#if (item.age > 10 || item.price > 500) && user.loggedIn}</code>.";
    }

    @Override
    public Iterable<String> getLabels() {
        return List.of(Labels.EXPRESSIONS, Labels.IF);
    }

    @Override
    public String getSnippetName() {
        return "simpleIf.html";
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        return CheckedTemplates.simpleIf(new Item(BigDecimal.TEN, "log"));
    }

}
