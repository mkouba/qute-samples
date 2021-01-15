package org.acme.qute.samples;

import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.Sample;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.ResourcePath;
import io.vertx.core.MultiMap;

@Singleton
public class HelloSample implements Sample {

    @ResourcePath("snippets/hello")
    Template hello;

    @Override
    public String getTitle() {
        return "Hello Elvis";
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        return hello.data("name", params.get("name"));
    }

    @Override
    public Iterable<String> getInfos() {
        return List.of(
                "Add the <code>name</code> query param to the URL to see the elvis operator in action, e.g. <code>/hello?name=Foo</code>.");
    }

    @Override
    public int getPriority() {
        return 10;
    }

}
