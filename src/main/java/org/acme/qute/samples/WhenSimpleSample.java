package org.acme.qute.samples;

import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.CheckedTemplates;
import org.acme.qute.Sample;

import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

@Singleton
public class WhenSimpleSample implements Sample {

    @Override
    public String getTitle() {
        return "When";
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        return CheckedTemplates.whenSimple(params.get("name"));
    }

    @Override
    public Iterable<String> getInfos() {
        return List.of(
                "You can add the <code>name</code> query param to the URL to see the switch section in action, e.g. <code>?name=Foo</code>.");
    }

}
