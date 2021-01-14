package org.acme.qute.samples;

import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.CheckedTemplates;
import org.acme.qute.Labels;
import org.acme.qute.Sample;
import org.acme.qute.data.Status;

import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

@Singleton
public class WhenEnumSample implements Sample {

    @Override
    public String getTitle() {
        return "When Section with Enum";
    }

    @Override
    public String getDescription() {
        return "The <code>when</code> section matches a tested value against all blocks sequentially until a condition is satisfied. The first matching block is executed. All other blocks are ignored. A tested value that resolves to an enum is handled and validated specifically.";
    }

    @Override
    public String getSnippetName() {
        return "whenEnum.html";
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        return CheckedTemplates.whenEnum(Status.ON);
    }

    @Override
    public Iterable<String> getLabels() {
        return List.of(Labels.EXPRESSIONS, Labels.WHEN);
    }

}
