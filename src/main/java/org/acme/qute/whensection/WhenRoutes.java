package org.acme.qute.whensection;

import org.acme.qute.CheckedTemplates;

import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;

public class WhenRoutes {

    @Route(path = "when-enum", produces = "text/html")
    String whenEnum() {
        return CheckedTemplates.whenEnum(Status.ON).render();
    }

    @Route(path = "switch-str", produces = "text/html")
    String switchStr(@Param String name) {
        return CheckedTemplates.switchString(name).render();
    }

}
