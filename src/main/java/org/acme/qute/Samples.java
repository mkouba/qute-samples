package org.acme.qute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;

import io.quarkus.qute.runtime.EngineProducer;

final class Samples {

    static String getSnippetName(String sampleClazzName) {
        // e.g. ExtensionMethodsSample -> /extensionMethods.html
        if (sampleClazzName.contains("Sample")) {
            sampleClazzName = sampleClazzName.substring(0, sampleClazzName.indexOf("Sample"));
        }
        if (Character.isUpperCase(sampleClazzName.charAt(0))) {
            sampleClazzName = Character.toLowerCase(sampleClazzName.charAt(0)) + sampleClazzName.substring(1);
        }
        return sampleClazzName + ".html";
    }

    static String loadSource(Sample sample) {
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
