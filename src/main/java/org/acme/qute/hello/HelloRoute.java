package org.acme.qute.hello;

import javax.inject.Inject;

import io.quarkus.qute.Template;
import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;

public class HelloRoute {

    @Inject
    Template hello;

    @Route(path = "/")
    @Route
    String hello(@Param String name) {
        return hello.data("name", name).render();
    }

}
