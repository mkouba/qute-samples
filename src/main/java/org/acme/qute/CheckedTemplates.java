package org.acme.qute;

import java.util.List;

import org.acme.qute.data.Item;
import org.acme.qute.data.Status;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.CheckedTemplate;

@CheckedTemplate(basePath = "snippets")
public class CheckedTemplates {

    public static native TemplateInstance whenEnum(Status status);
    
    public static native TemplateInstance switchString(String name);

    public static native TemplateInstance items(List<Item> items);

    public static native TemplateInstance greetings(List<String> greetings);
    
    public static native TemplateInstance simpleLoop(List<Item> items);
    
}
