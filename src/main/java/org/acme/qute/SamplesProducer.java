package org.acme.qute;

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
import io.quarkus.qute.TemplateExtension;
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
        // Begginer first, lowest priority first, sort by title
        samples.sort(Comparator.comparing(Sample::getDifficulty)
                .thenComparing((s1, s2) -> Integer.compare(s2.getPriority(), s1.getPriority()))
                .thenComparing(Sample::getTitle));
        return List.copyOf(samples);
    }
    
    void registerRoutes(@Observes Router router, List<Sample> samples, Engine engine, Template sampleDetail) {
        router.route("/").produces("text/html").handler(ctx -> {
            Template index = engine.getTemplate("samples");
            ctx.response().setStatusCode(200)
                    .end(index.render());
        });
        for (Sample sample : samples) {
            Handler<RoutingContext> handler = ctx -> {
                Template descriptionTemplate = engine.getTemplate("descriptions/" + sample.getSnippetName());
                ctx.response().setStatusCode(200)
                        .end(sampleDetail
                                .data("sample", sample)
                                .data("description", descriptionTemplate != null ? descriptionTemplate.render() : "")
                                .data("output", sample.getSnippetInstance(ctx.request().params()).render())
                                .render());
            };
            router.route(sample.getPath()).produces("text/html").handler(handler);
        }
    }
    
    @TemplateExtension(matchName = "source")
    static String getSource(Sample sample) {
        return Samples.loadSource(sample.getSnippetName());
    }
    
    @TemplateExtension
    static String getSource(Sample sample, String snippetName) {
        return Samples.loadSource(snippetName);
    }

}
