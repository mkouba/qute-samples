package org.acme.qute.samples;

import java.util.List;
import java.util.Locale;

import javax.inject.Singleton;

import org.acme.qute.Sample;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.i18n.MessageBundles;
import io.vertx.core.MultiMap;

@Singleton
public class MessagesSample implements Sample {

    @Override
    public String getTitle() {
        return "Message Bundles";
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        String locale = params.get("locale");
        return Sample.super.getSnippetInstance(params)
                .setAttribute(MessageBundles.ATTRIBUTE_LOCALE, locale != null ? locale : Locale.ENGLISH)
                .data("name", params.get("name"));
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.ADVANCED;
    }

    @Override
    public int getPriority() {
        // always last
        return Integer.MIN_VALUE;
    }

    @Override
    public Iterable<String> getInfos() {
        return List.of(
                "You can add the <code>name</code> and <code>locale</code> query params to the URL to see the message bundle in action, e.g. <code>?name=Ferdinand&locale=cs</code>.");
    }

}
