package org.acme.qute.extensionmethods;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.qute.CheckedTemplates;

import io.quarkus.qute.TemplateInstance;

@Path("greetings")
public class GreetingsResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return CheckedTemplates.greetings(Arrays.asList("Hello", "Tschüss", "Ahoj"));
    }

}