package org.acme.qute;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;

import io.quarkus.qute.Engine;
import io.quarkus.qute.Template;
import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class SamplesProducer {

    @Named("samples")
    @Singleton
    @Produces
    List<Sample> samples(Instance<Sample> instance) {
        List<Sample> samples = new ArrayList<>();
        for (Sample sample : instance) {
            samples.add(sample);
        }
        // Highest priority, begginer first, sort by title
        samples.sort(Comparator.comparingInt(Sample::getPriority).reversed().thenComparing(Sample::getDifficulty)
                .thenComparing(Sample::getTitle));
        return List.copyOf(samples);
    }

    @Named("now")
    @Produces
    LocalDateTime now() {
        return LocalDateTime.now();
    }

    void registerRoutes(@Observes Router router, List<Sample> samples, Engine engine, Template sampleDetail) {
        int idx = 0;
        for (Sample sample : samples) {
            Handler<RoutingContext> handler = ctx -> {
                ctx.response().setStatusCode(200)
                        .end(sampleDetail
                                .data("sample", sample)
                                .data("description", engine.getTemplate("descriptions/" + sample.getSnippetName()).render())
                                .data("output", sample.getSnippetInstance(ctx.request().params()).render())
                                .data("source", Samples.loadSource(sample))
                                .render());
            };
            idx++;
            if (idx == 1) {
                // The sample with highest priority is also served from the root
                router.route("/").produces("text/html").handler(handler);
            }
            router.route(sample.getPath()).produces("text/html").handler(handler);
        }
    }

}
