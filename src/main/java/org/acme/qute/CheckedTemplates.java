package org.acme.qute;

import java.util.List;

import org.acme.qute.data.Item;
import org.acme.qute.data.Status;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.CheckedTemplate;

@CheckedTemplate(basePath = "snippets")
public class CheckedTemplates {

    public static native TemplateInstance whenEnum(Status status);

    public static native TemplateInstance whenSimple(String name);

    public static native TemplateInstance userTag(List<Item> items);

    public static native TemplateInstance extensionMethods(List<String> greetings);

    public static native TemplateInstance loopSimple(List<Item> items);

    public static native TemplateInstance ifSimple(Item item);

}
