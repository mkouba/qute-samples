package org.acme.qute.samples;

import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.Sample;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

@Singleton
public class HelloSample implements Sample {

    @Location("snippets/hello")
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
                "You can add the <code>name</code> query param to the URL to see the elvis operator in action, e.g. <code>?name=Foo</code>.");
    }

    @Override
    public int getPriority() {
        return 10;
    }

}
