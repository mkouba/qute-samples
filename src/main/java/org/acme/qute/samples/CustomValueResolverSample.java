package org.acme.qute.samples;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.acme.qute.Sample;

import io.quarkus.qute.Engine;
import io.quarkus.qute.EngineBuilder;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.ValueResolver;
import io.vertx.core.MultiMap;

@Singleton
public class CustomValueResolverSample implements Sample {

    @Inject
    Engine engine;

    @Override
    public String getTitle() {
        return "Custom Value Resolver";
    }

    void registerResolver(@Observes EngineBuilder builder) {
        builder.addValueResolver(ValueResolver.builder()
                .appliesTo(ec -> ec.getBase() instanceof Long && ec.getName().equals("tenTimes"))
                .resolveSync(ec -> (Long) ec.getBase() * 10)
                .build());
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        String number = params.get("number");
        return engine.getTemplate("snippets/" + getSnippetName()).data("number", number != null ? Long.valueOf(number) : null);
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.EXPERT;
    }
    
    @Override
    public Iterable<String> getInfos() {
        return List.of(
                "Add the <code>number</code> query param to the URL to specify the number value, e.g. <code>?number=20</code>.");
    }

}
