package org.acme.qute.samples;

import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.CheckedTemplates;
import org.acme.qute.Labels;
import org.acme.qute.Sample;

import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

@Singleton
public class ExtensionMethodsSample implements Sample {

    @Override
    public String getTitle() {
        return "Extension Methods";
    }

    @Override
    public String getDescription() {
        return "In this sample, the virtual property <code>reversed</code> is implemented via an extension method: <code>org.acme.qute.TemplateExtensions#reversed()</code>.";
    }

    @Override
    public String getSnippetName() {
        return "greetings.html";
    }

    @Override
    public Iterable<String> getLabels() {
        return List.of(Labels.EXPRESSIONS, Labels.LOOP, Labels.LOOP);
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        return CheckedTemplates.greetings(Arrays.asList("Hello", "Tschüss", "Ahoj"));
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.ADVANCED;
    }

}
