package org.acme.qute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.quarkus.qute.Template;
import io.quarkus.qute.runtime.EngineProducer;
import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class SamplesProducer {

    @Inject
    Template sampleDetail;

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

    void registerRoutes(@Observes Router router, List<Sample> samples) {
        int idx = 0;
        for (Sample sample : samples) {
            Handler<RoutingContext> handler = ctx -> {
                ctx.response().setStatusCode(200)
                        .end(sampleDetail
                                .data("sample", sample)
                                .data("output", sample.getSnippetInstance(ctx.request().params()).render())
                                .data("source", loadSource(sample))
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

    private static String loadSource(Sample sample) {
        URL resource = null;
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = EngineProducer.class.getClassLoader();
        }
        resource = cl.getResource("templates/snippets/" + sample.getSnippetName());
        if (resource == null) {
            throw new IllegalStateException("Snippet source not found: " + sample.getSnippetName());
        }

        final char[] buffer = new char[1024 * 8];
        int n = 0;
        final StringWriter out = new StringWriter();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(resource.openStream(), Charset.forName("utf-8")))) {
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return out.toString();

    }

}
