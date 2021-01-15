package org.acme.qute;

import java.util.Collections;

import io.quarkus.arc.Arc;
import io.quarkus.qute.Engine;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

public interface Sample {

    String getTitle();

    default String getSnippetName() {
        return Samples.getSnippetName(getClass().getSimpleName());
    }

    default String getPath() {
        // hello.html -> /hello
        String snippet = getSnippetName();
        if (snippet.contains(".")) {
            snippet = snippet.substring(0, snippet.lastIndexOf("."));
        }
        return snippet.startsWith("/") ? snippet : "/" + snippet;
    }

    default TemplateInstance getSnippetInstance(MultiMap params) {
        Template template = Arc.container().instance(Engine.class).get().getTemplate("snippets/" + getSnippetName());
        return template.instance();
    }

    default Difficulty getDifficulty() {
        return Difficulty.BEGINNER;
    }

    default int getPriority() {
        return 1;
    }

    default Iterable<String> getInfos() {
        return Collections.emptyList();
    }

    enum Difficulty {

        BEGINNER,
        ADVANCED,
        EXPERT;

    }

}