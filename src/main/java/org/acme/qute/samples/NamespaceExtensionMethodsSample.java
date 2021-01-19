package org.acme.qute.samples;

import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.Sample;

import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

@Singleton
public class NamespaceExtensionMethodsSample implements Sample {

    @Override
    public String getTitle() {
        return "Namespace Extension Methods";
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        String name = params.get("name");
        return Sample.super.getSnippetInstance(params)
                .data("name", name != null ? name : "Graham");
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.EXPERT;
    }
    
    @Override
    public Iterable<String> getInfos() {
        return List.of(
                "You can add the <code>name</code> query param to the URL to specify the name used in the template, e.g. <code>?name=Mat</code>.");
    }

}
