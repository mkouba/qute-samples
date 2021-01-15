package org.acme.qute.samples;

import javax.inject.Singleton;

import org.acme.qute.CheckedTemplates;
import org.acme.qute.Sample;
import org.acme.qute.data.Status;

import io.quarkus.qute.TemplateInstance;
import io.vertx.core.MultiMap;

@Singleton
public class WhenEnumSample implements Sample {

    @Override
    public String getTitle() {
        return "When with Enum";
    }

    @Override
    public TemplateInstance getSnippetInstance(MultiMap params) {
        return CheckedTemplates.whenEnum(Status.ON);
    }

}
