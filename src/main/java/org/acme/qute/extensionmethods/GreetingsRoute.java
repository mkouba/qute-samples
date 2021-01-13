package org.acme.qute.extensionmethods;

import java.util.Arrays;

import org.acme.qute.CheckedTemplates;

import io.quarkus.vertx.web.Route;

public class GreetingsRoute {

    @Route(produces = "text/html")
    public String greetings() {
        return CheckedTemplates.greetings(Arrays.asList("Hello", "Tschüss", "Ahoj")).render();
    }

}