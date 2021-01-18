package org.acme.qute.samples;

import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.Sample;

@Singleton
public class TemplateInheritanceSample implements Sample {

    @Override
    public String getTitle() {
        return "Template Inheritance";
    }

    @Override
    public List<String> getAdditionalSnippetNames() {
        return Collections.singletonList("base.html");
    }

    public Difficulty getDifficulty() {
        return Difficulty.ADVANCED;
    }

    @Override
    public int getPriority() {
        // this sample should go after include
        return -1;
    }

}
