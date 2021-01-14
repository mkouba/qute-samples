package org.acme.qute.samples;

import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.CheckedTemplates;
import org.acme.qute.Labels;
import org.acme.qute.Sample;

import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

@Singleton
public class WhenStringSample implements Sample {

    @Override
    public String getTitle() {
        return "When with String";
    }

    @Override
    public String getDescription() {
        return "<code>switch</code> is an alias for the <code>when</code> section that matches a tested value against all blocks sequentially until a condition is satisfied. The first matching block is executed. All other blocks are ignored.";
    }

    @Override
    public String getSnippetName() {
        return "switchString.html";
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        return CheckedTemplates.switchString(params.get("name"));
    }

    @Override
    public Iterable<String> getLabels() {
        return List.of(Labels.EXPRESSIONS, Labels.WHEN);
    }

    @Override
    public Iterable<String> getInfos() {
        return List.of(
                "Add the <code>name</code> query param to the URL to see the switch section in action, e.g. <code>/switch-str?name=Foo</code>.");
    }

}
