package org.acme.qute;

import java.util.List;

import org.acme.qute.usertags.Item;
import org.acme.qute.whensection.Status;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.CheckedTemplate;

@CheckedTemplate
public class CheckedTemplates {

    public static native TemplateInstance whenEnum(Status status);
    
    public static native TemplateInstance switchString(String name);

    public static native TemplateInstance items(List<Item> items);

    public static native TemplateInstance greetings(List<String> greetings);
    
}
