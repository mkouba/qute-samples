package org.acme.qute;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.List;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import io.quarkus.qute.HtmlEscaper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ValidatableResponse;

@QuarkusTest
public class SamplesTest {

    private static final Logger LOG = Logger.getLogger(SamplesTest.class);

    @Inject
    List<Sample> samples;

    @Test
    public void testIndex() {
        ValidatableResponse response = when().get("/").then().statusCode(200).body(containsString("Qute Samples - Index"),
                containsString("<h1>Samples</h1>"));
        for (Sample sample : samples) {
            response.body(containsString(sample.getTitle()));
        }
    }

    @Test
    public void testSamples() {
        HtmlEscaper escaper = new HtmlEscaper();
        for (Sample sample : samples) {
            String path = sample.getPath();
            LOG.info("GET " + path);
            when().get(path).then().statusCode(200)
                    .body(containsString(sample.getTitle()),
                            containsString(escaper.map(Samples.loadSource(sample.getSnippetName()), null)));
        }
    }
}
