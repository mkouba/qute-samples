package org.acme.qute.samples;

import java.util.Arrays;

import javax.inject.Singleton;

import org.acme.qute.CheckedTemplates;
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
    public TemplateInstance getSnippetInstance(MultiMap params) {
        return CheckedTemplates.extensionMethods(Arrays.asList("Hello", "Tschüss", "Ahoj"));
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.ADVANCED;
    }

}
